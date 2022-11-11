package controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageProcessingModel;
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

  private final Readable readable;
  private final Map<String, ImageProcessingModel> images;
  private final Map<String, Function<Scanner, ImageCommand>> knownCommands;
  private final ImageProcessingView view;

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
        try {
          Function<Scanner, ImageCommand> cmd =
                  knownCommands.getOrDefault(userInstruction, null);
          if (cmd == null) {
            writeMessage("Error: command " + userInstruction + " not found.");
          } else {
            c = cmd.apply(sc);
            c.go();
          }
        } catch (InputMismatchException e) {
          writeMessage("Incorrect arguments for given command found, try again.");
        }
      }
    }

    //after the user has quit, print farewell message
    this.farewellMessage();

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
    writeMessage("\tload file-path image-name");
    writeMessage("\tsave file-path image-name");
    writeMessage("\tred-component image-path image-name " +
            "(same for green, blue, value, luma and intensity)");
    writeMessage("\thorizontal-flip image-path image-name");
    writeMessage("\tvertical-flip image-path image-name");
    writeMessage("\tbrighten int-scalar image-path image-name");
    writeMessage("\tblur image-path image-name");
    writeMessage("\tsharpen image-path image-name");
    writeMessage("\tsepia image-path image-name");
    writeMessage("\tluma image-path image-name");
    writeMessage("Where image-path is the name of the image as its saved and" +
            " image-name is the new name of the result");
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
