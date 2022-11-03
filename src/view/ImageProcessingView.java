package view;

import model.ImageProcessingModel;

/**
 * An interface that represents the view for the image processing application.
 * This view only handles saving the image to the computer.
 */
public interface ImageProcessingView {
  /**
   * Saves the given model to the file type specific to the
   * view.
   *
   * @param filename the name of the destination file
   * @param model the model that is to be saved
   * @throws IllegalStateException if writing to the file fails
   */
  void save(String filename, ImageProcessingModel model) throws IllegalStateException,
          NullPointerException;

  /**
   * Renders the given message to the view.
   *
   * @param message the message to render as a string
   * @throws IllegalStateException if writing to view fails
   */
  void renderMessage(String message) throws NullPointerException;
}
