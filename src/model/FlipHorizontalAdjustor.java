package model;

/**
 * This class represents an adjustor on a PPM image that flips
 * the image horizontally, or across the y-axis.
 */
public class FlipHorizontalAdjustor implements IAdjustor {

  private final String name;

  /**
   * Constructs this flip horizontal macro.
   * @param name the name of the resulting image
   */
  public FlipHorizontalAdjustor(String name) {
    this.name = name;
  }

  /**
   * Creates a new ImageProcessingModel where each pixel from the given model
   * is flipped across the y-axis, creating a new image that is horizontally flipped
   * from the original.
   *
   * @param m the model of the image being operated on
   * @return the given image flipped across the y-axis
   */
  @Override
  public ImageProcessingModel apply(ImageProcessingModel m) {
    IPixel[][] flipped = new IPixel[m.getImageHeight()][m.getImageWidth()];
    for (int row = 0; row < m.getImageHeight(); row++) {
      for (int col = 0; col < m.getImageWidth(); col++) {
        flipped[row][col] = m.getPixelAt(row, m.getImageWidth() - col);
      }
    }
    return new AImageProcessingModel(this.name, flipped);
  }
}
