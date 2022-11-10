package controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.BrightenAdjustor;
import model.FlipHorizontalAdjustor;
import model.FlipVerticalAdjustor;
import model.GrayscaleAdjustor;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;

/**
 * This class represents the controller of a simple interactive image processor
 * that works in the console. This controller offers a simple text interface in which
 * the user can type instructions to load, manipulate, and save images.
 *
 * <p>This controller works with images to read its pixels and create Image Processing Models
 * stored in a Map(String, Model) called "images".
 */
public class ConsoleController implements ImageProcessingController {

  private Readable readable;
  private Map<String, ImageProcessingModel> images;
  private Map<String, Function<Scanner, ImageCommand>> knownCommands;
  private ImageProcessingView view;

  /**
   * Create a controller with an empty map of models, readable (to take inputs)
   * and appendable (to transmit output). Constructs an empty hashmap to store
   * images in.
   * 
   * @param readable the Readable object for inputs.
   * @param view the View object to transmit outgoing messages to.
   */
  public ConsoleController(Readable readable, ImageProcessingView view) {
    this(readable, view, new HashMap<>());
  }

  /**
   * Create a controller with a given map of models, readable (to take inputs)
   * and appendable (to transmit output).
   *
   * @param readable the Readable object for inputs.
   * @param view the View object to transmit outgoing messages to.
   * @param map the Map of Strings to Models used to store values in the program.
   */
  public ConsoleController(Readable readable,
                           ImageProcessingView view,
                           Map<String, ImageProcessingModel> map) {
    if (readable == null || view == null) {
      throw new IllegalArgumentException("Readable or view is null");
    }

    this.readable = readable;
    this.images = map;
    this.view = view;



    this.knownCommands = new HashMap<>();
    knownCommands.put("load", s->new Load(s.next(), s.next(), images, this));
    knownCommands.put("save", s->new Save(s.next(), s.next(), images, this));
    knownCommands.put("red-component", s->new Component(s.next(), s.next(), images, this,
            "red"));
    knownCommands.put("blue-component", s->new Component(s.next(), s.next(), images, this,
            "blue"));
    knownCommands.put("green-component", s->new Component(s.next(), s.next(), images, this,
            "green"));
    knownCommands.put("value-component", s->new Component(s.next(), s.next(), images, this,
            "value"));
    knownCommands.put("luma-component", s->new Component(s.next(), s.next(), images, this,
            "luma"));
    knownCommands.put("intensity-component", s->new Component(s.next(), s.next(), images,
            this, "intensity"));

    knownCommands.put("horizontal-flip", s-> new HFlip(s.next(), s.next(), images, this));
    knownCommands.put("vertical-flip", s-> new VFlip(s.next(), s.next(), images, this));
    knownCommands.put("brighten", s-> new Brighten(s.nextInt(), s.next(), s.next(), images,
            this));

    knownCommands.put("blur", s-> new Blur(s.next(), s.next(), images, this));
    knownCommands.put("sharpen", s-> new Sharpen(s.next(), s.next(), images, this));

    knownCommands.put("luma", s-> new Luma(s.next(), s.next(), images, this));
    knownCommands.put("sepia", s-> new Sepia(s.next(), s.next(), images, this));

  }

  /**
   * The main method that relinquishes control of the application to the controller.
   * once in control, valid commands are
   * @throws IllegalStateException if the controller is unable to transmit output.
   */
  @Override
  public void control() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    ImageCommand c;
    boolean quit = false;

    //print the welcome message
    this.welcomeMessage();

    while (!quit && sc.hasNext()) {
      //writeMessage("Type intruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      } else if (userInstruction.equals("menu")) {
        printMenu();
      } else {
        Function<Scanner, ImageCommand> cmd =
                knownCommands.getOrDefault(userInstruction, null);
        if (cmd == null) {
          writeMessage("Error: command " + userInstruction + " not found.");
        }
        else {
          c = cmd.apply(sc);
          c.go();
        }
      }
    }

    //after the user has quit, print farewell message
    this.farewellMessage();

  }

  // processes the given image command. If the command is not supported
  private void processCommand(String userInstruction, Scanner sc) {
    String name1;
    String name2;
    String response = "Successful!";
    if (userInstruction.contains("component")) {
      String component = userInstruction.substring(0,userInstruction.indexOf("-"));
      name1 = sc.next(); //name1 = image-name
      name2 = sc.next(); //name2 = dest-image-name
      writeMessage("Gray-scaling " + name1 + " by its " + component + " component and storing as: "
              + name2);
      tryGrayscale(name1, name2, component);
    }

    else {
      switch (userInstruction) {
        case "load": //load given image path
          try {
            name1 = sc.next();
            name2 = sc.next();
            writeMessage("Loading image at " + name1 + " as " + name2);
            ImageProcessingModel model = new PPMProcessingModel(name1); //Too specific for future
            images.put(name2, model);
          } catch (IllegalArgumentException e) {
            response = "Error: " + e.getMessage();
          }
          break;

        case "save": // name1 = filepath, name2 = image-name
          try {
            name1 = sc.next();
            name2 = sc.next();
            try {
              writeMessage("Saving " + name2 + " to: " + name1);
              view.save(name1, images.get(name2));

            } catch (NullPointerException e) {
              response = "Error: image " + name1 + "not yet loaded";
            }
          } catch (IllegalArgumentException e) {
            response = "Error " + e.getMessage();
          }
          break;

        case "horizontal-flip":
          name1 = sc.next(); //name1 = image-name
          name2 = sc.next(); //name2 = dest-image-name
          try {
            writeMessage("Flipping " + name1 + " horizontally and storing as: " + name2);
            images.put(name2, images.get(name1).apply(new FlipHorizontalAdjustor()));
          } catch (NullPointerException e) {
            response = "Error: image " + name1 + " not yet loaded";
          }
          break;

        case "vertical-flip":
          name1 = sc.next(); //name1 = image-name
          name2 = sc.next(); //name2 = dest-image-name
          try {
            writeMessage("Flipping " + name1 + " vertically and storing as: " + name2);
            images.put(name2, images.get(name1).apply(new FlipVerticalAdjustor()));
          } catch (NullPointerException e) {
            response = "Error: image " + name1 + " not yet loaded";
          }
          break;

        case "brighten":
          int increment;
          try {
            increment = sc.nextInt();
            name1 = sc.next(); //name1 = image-name
            name2 = sc.next(); //name2 = dest-image-name
            try {
              writeMessage("Brightening " + name1 + " by "
                      + increment + " and storing as: " + name2);
              images.put(name2, images.get(name1).apply(new BrightenAdjustor(increment)));
            } catch (NullPointerException e) {
              response = "Error: image " + name1 + " not yet loaded";
            }
          }
          catch (InputMismatchException e) {
            response = "Error: brighten needs an integer increment";
            sc.next();
            sc.next();
          }
          break;
        default:
          response = "Error: command " + userInstruction + " not found.";
      }
    }
    writeMessage(response);
  }

  // a method to grayscale by a given component (red, green, blue, value, intensity, luma)
  private void tryGrayscale(String name1, String name2, String component) {
    try {
      //put new model into the images map.
      images.put(name2, images.get(name1).apply(new GrayscaleAdjustor(component)));
    }
    catch (NullPointerException e) {
      writeMessage("Image " + name1 + " not yet loaded");
    }
    catch (IllegalArgumentException e) {
      writeMessage("Error: " + e.getMessage());
    }
  }

  //outputs the farewell message to the view
  private void farewellMessage() {
    writeMessage("Have a nice day!");
  }

  //outputs the welcome message to the view
  private void welcomeMessage() {
    writeMessage("Welcome to the Image Processing program");
    printMenu();
  }

  // outputs the menu of all operations into the view
  private void printMenu() {
    writeMessage("Supported operations are:");
    writeMessage("\tload image-path image-name");
    writeMessage("\tsave image-path image-name");
    writeMessage("\tred-component image-path image-name " +
            "(same for green, blue, value, luma and intensity)");
    writeMessage("\thorizontal-flip image-path image-name");
    writeMessage("\tvertical-flip image-path image-name");
    writeMessage("\"q\" or \"quit\" to quit");
    writeMessage("\"menu\" to see this menu again");
  }

  // sends messages to view, handles errors.
  protected void writeMessage(String message) throws IllegalStateException {
    view.renderMessage(message);
  }

  protected void trySave(String name1, String name2) {
    this.view.save(name1, images.get(name2));
  }
}
