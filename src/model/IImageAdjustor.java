package model;

/**
 * This interface represents a macro that can be applied to an image.
 */
interface IImageAdjustor {
  /**
   * Applies this images macro function to a given model, and returns a
   * new model representing the result.
   *
   * @param m the model of the image being operated on
   * @return the adjusted array of pixels resulting from the adjustment
   * @throws NullPointerException if the model provided is null
   */
  ImageProcessingModel adjust(ImageProcessingModel m) throws NullPointerException;
}
