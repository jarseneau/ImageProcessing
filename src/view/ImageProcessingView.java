package view;

/**
 * An interface that represents the view for the image processing application.
 * This view only handles saving the image to the computer.
 */
public interface ImageProcessingView {

  /**
   * Saves the model given to the view to the file type specific to the
   * view.
   *
   * @param filename the name of the destination file
   */
  public void save(String filename);
}
