package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.ImageProcessingModel;

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
  private Appendable appendable;
  private Map<String, ImageProcessingModel> images;

  /**
   * Create a controller with an empty map of models, readable (to take inputs)
   * and appendable (to transmit output).
   * 
   * @param readable the Readable object for inputs
   * @param appendable the Appendable object to transmit output
   */
  public ConsoleController(Readable readable, Appendable appendable) {
    if ((readable == null) || (appendable == null)) {
      throw new IllegalArgumentException("Readable or appendable is null");
    }

    this.readable = readable;
    this.appendable = appendable;
    this.images = new HashMap<>();
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

  private void processCommand(String userInstruction, Scanner sc, Map<String, ImageProcessingModel> images) {
  }

  private void farewellMessage() {
  }

  private void welcomeMessage() {
  }

  protected void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
