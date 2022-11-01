package model;

/**
 * This interface represents an image processing model which supports many operations
 * on an image.
 */
public interface ImageProcessingModel {

  /**
   * Return the name of this image.
   *
   * @return the name as a String
   */
  String getImageName();

  /**
   * Return the width of this image.
   *
   * @return the width as an integer
   */
  int getImageWidth();

  /**
   * Return the height of this image.
   *
   * @return the width as an integer
   */
  int getImageHeight();

  /**
   * Get the pixel at the coordinates specified.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the pixel at the coordinates
   * @throws IllegalArgumentException if the row or the column are beyond
   *         the size of the image
   */
  IPixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Changes the image with the specified image function.
   *
   * @param adjustor the alteration with which the image should be changed
   * @param newName the new name of the created model
   * @return the model created from adjusting the given image
   * @throws IllegalArgumentException if the alteration does not exist
   */
  ImageProcessingModel apply(String newName, IAdjustor adjustor);
}