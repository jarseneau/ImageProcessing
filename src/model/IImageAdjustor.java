package model;

/**
 * This interface represents a macro that can be applied to an image.
 */
public interface IImageAdjustor {
  /**
   * Applies this images macro function to a given model, and returns a
   * new model representing the result.
   *
   * @param m the model of the image being operated on
   * @return the adjusted array of pixels resulting from the adjustment
   */
  ImageProcessingModel adjust(ImageProcessingModel m);
}
