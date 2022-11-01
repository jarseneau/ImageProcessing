package model;

/**
 * This class represents an adjustor on a PPM image that flips
 * the image horizontally, or across the y-axis.
 */
public class FlipVerticalAdjustor implements IAdjustor {
  /**
   * Creates a new ImageProcessingModel where each pixel from the given model
   * is flipped across the y-axis, creating a new image that is horizontally flipped
   * from the original.
   *
   * @param m the model of the image being operated on
   * @return the given image flipped across the y-axis
   */
  @Override
  public IPixel[][] adjust(ImageProcessingModel m) {
    IPixel[][] flipped = new IPixel[m.getImageHeight()][m.getImageWidth()];
    for (int row = 0; row < m.getImageHeight(); row++) {
      for (int col = 0; col < m.getImageWidth(); col++) {
        flipped[row][col] = m.getPixelAt(m.getImageHeight() - row - 1, col);
      }
    }
    return flipped;
  }
}