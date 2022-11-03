package model;

import java.util.Objects;

/**
 * This class represents an adjustor on an image that flips
 * the image horizontally, or across the y-axis.
 */
public class FlipVerticalAdjustor implements IImageAdjustor {

  /**
   * Creates a new ImageProcessingModel where each pixel from the given model
   * is flipped across the x-axis, or vertically flipped from the original.
   *
   * @param m the model of the image being operated on
   * @return the given image flipped across the x-axis
   * @throws NullPointerException if the model provided is null
   */

  @Override
  public ImageProcessingModel adjust(ImageProcessingModel m) throws NullPointerException {
    Objects.requireNonNull(m);
    IPixel[][] flipped = new IPixel[m.getImageHeight()][m.getImageWidth()];
    for (int row = 0; row < m.getImageHeight(); row++) {
      for (int col = 0; col < m.getImageWidth(); col++) {
        flipped[row][col] = m.getPixelAt(m.getImageHeight() - row - 1, col);
      }
    }
    return new EditorImageProcessingModel(flipped, m.getMaxValue());
  }
}