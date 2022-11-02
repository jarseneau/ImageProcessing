package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import model.BrightenAdjustor;
import model.FlipHorizontalAdjustor;
import model.FlipVerticalAdjustor;
import model.GrayscaleAdjustor;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;
import view.PPMImageView;

/**
 * This class represents the controller of a simple interactive image processor
 * that works in the console. This controller offers a simple text interface in which
 * the user can type instructions to load, manipulate, and save images.
 *
 * <p>This controller works with images to read its pixels and create Image Processing Models
 * stored in a Map<String, Model> called "images".
 */
public class ConsoleController implements ImageProcessingController{

  private Readable readable;
  private Map<String, ImageProcessingModel> images;

  private ImageProcessingView view;

  /**
   * Create a controller with an empty map of models, readable (to take inputs)
   * and appendable (to transmit output).
   * 
   * @param readable the Readable object for inputs.
   * @param view the View object to transmit outgoing messages to.
   */
  public ConsoleController(Readable readable, ImageProcessingView view) {
    if ((readable == null)) {
      throw new IllegalArgumentException("Readable or appendable is null");
    }

    this.readable = readable;
    this.images = new HashMap<>();
    this.view = view;

   }

  /**
   * The main method that relinquishes control of the application to the controller.
   * @throws IllegalStateException if the controller is unable to transmit output.
   */
  @Override
  public void control() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    boolean quit = false;
    
    //print the welcome message
    this.welcomeMessage();

    while (!quit && sc.hasNext()) {
      writeMessage("Type intruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      }
      else {
        processCommand(userInstruction, sc, images);
      }
    }

    //after the user has quit, print farewell message
    this.farewellMessage();

  }

  /**
   * load image-path image-name: Load an image from the specified path and refer it to henceforth in the program by the given image name.
   *
   * save image-path image-name: Save the image with the given name to the specified path which should include the name of the file.
   *
   * red-component image-name dest-image-name: Create a greyscale image with the red-component of the image with the given name, and refer to it henceforth in the program by the given destination name. Similar commands for green, blue, value, luma, intensity components should be supported.
   *
   * horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image, referred to henceforth by the given destination name.
   *
   * vertical-flip image-name dest-image-name: Flip an image vertically to create a new image, referred to henceforth by the given destination name.
   *
   * brighten increment image-name dest-image-name: brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening)
   * @param userInstruction
   * @param sc
   * @param images
   */

  private void processCommand(String userInstruction, Scanner sc, Map<String, ImageProcessingModel> images) {
    String name1;
    String name2;

    if (userInstruction.contains("component")) {
      String component = userInstruction.substring(0,userInstruction.indexOf("-"));
      name1 = sc.next(); //name1 = image-name
      name2 = sc.next(); //name2 = dest-image-name
      tryGrayscale(name1, name2, component);
    }

    switch (userInstruction) {
      case "load": //load given image path
        try {
          name1 = sc.next();
          name2 = sc.next();
          //writeMessage("Loading image at " + name1 + " as " + name2 + System.lineSeparator()); in view?
          ImageProcessingModel model = new PPMProcessingModel(name1);
          images.put(name2, model);
        }
        catch (IllegalArgumentException e) {
          writeMessage(e.getMessage());
        }
        break;

      case "save": // name1 = filepath, name2 = image-name
        try {
          name1 = sc.next();
          name2 = sc.next();
          try {
            view.save(name1, images.get(name2));
          }
          catch (NullPointerException e) {
            writeMessage("Image " + name1 + "not yet loaded");
          }
        }
        catch (IllegalArgumentException e) {
          writeMessage(e.getMessage());
        }
        break;

      case "horizontal-flip":
        name1 = sc.next(); //name1 = image-name
        name2 = sc.next(); //name2 = dest-image-name
        try {
          images.put(name2, new FlipHorizontalAdjustor().adjust(images.get(name1)));
        }
        catch (NullPointerException e) {
          writeMessage("Image " + name1 + " not yet loaded");
        }
        break;

      case "vertical-flip":
        name1 = sc.next(); //name1 = image-name
        name2 = sc.next(); //name2 = dest-image-name
        try {
          images.put(name2, new FlipVerticalAdjustor().adjust(images.get(name1)));
        }
        catch (NullPointerException e) {
          writeMessage("Image " + name1 + " not yet loaded");
        }
        break;

      case "brighten":
        int increment;
        try {
          increment = sc.nextInt();
        }
        catch (InputMismatchException e) {
          throw new IllegalArgumentException("Brighten needs and integer");
        }
        name1 = sc.next(); //name1 = image-name
        name2 = sc.next(); //name2 = dest-image-name
        try {
          images.put(name2, new BrightenAdjustor(increment).adjust(images.get(name1)));
        }
        catch (NullPointerException e) {
          writeMessage("Image " + name1 + " not yet loaded");
        }
      default:
        writeMessage("Command, " + userInstruction + " not found.");
    }
  }

  // a method to grayscale by a given component
  private void tryGrayscale(String name1, String name2, String component) {
    try {
      //put new model into the images map.
      images.put(name2, new GrayscaleAdjustor(component).adjust(images.get(name1)));
    }
    catch (NullPointerException e) {
      writeMessage("Image " + name1 + " not yet loaded");
    }
  }

  private void farewellMessage() {

  }

  private void welcomeMessage() {
    writeMessage("Welcome to ");
  }

  // sends messages to view, handles errors.
  protected void writeMessage(String message) throws IllegalStateException {
    view.renderMessage(message);
  }
}
