package controller;

/**
 * An Interface to represent the controller of the image processing application.
 */
public interface ImageProcessingController {

  /**
   * The main method that relinquishes control of the application to the controller.
   * @throws IllegalStateException if the controller is unable to transmit output
   */
  void control() throws IllegalStateException;

  /**
   * Writes the given message to the view.
   */
  void writeMessage(String message) throws IllegalStateException;

  /**
   * Tries to save the given image.
   */
  void trySave(String name1, String name2);
}
