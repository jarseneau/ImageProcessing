package model;

/**
 * This interface represents a macro that can be applied to an image.
 */
public interface IAdjustor {
  /**
   * Applies this images macro function to a given model, and returns a
   * new model representing the result.
   *
   * @param m the model of the image being operated on
   * @return a new image model representing the result of the operation
   */
  ImageProcessingModel apply(ImageProcessingModel m);
}
