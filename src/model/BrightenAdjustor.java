package model;

import java.util.Objects;

/**
 * This class represents an adjustor on an image that brightens every
 * pixel by a value given by the constructor.
 */
public class BrightenAdjustor implements IImageAdjustor {

  private final int scalar;

  /**
   * Creates a new BrightenAdjustor which remembers the provided scalar for when the adjust
   * method is called.
   *
   * @param scalar the value by which the pixels should be brightened/darkened
   */
  public BrightenAdjustor(int scalar) {
    this.scalar = scalar;
  }

  /**
   * Creates a new ImageProcessingModel where each pixel from the given model
   * is brightened by the given scalar. If scalar is negative, pixels are darkened.
   *
   * @param m the model of the image being operated on
   * @return the given image brightened or darkened by the specified amount.
   * @throws NullPointerException if the model provided is null
   */
  @Override
  public ImageProcessingModel adjust(ImageProcessingModel m) throws NullPointerException {
    Objects.requireNonNull(m);
    IPixel[][] brightened = new IPixel[m.getImageHeight()][m.getImageWidth()];
    for (int row = 0; row < m.getImageHeight(); row++ ) {
      for (int col = 0; col < m.getImageWidth(); col++ ) {
        brightened[row][col] = m.getPixelAt(row, col).addAll(this.scalar);
      }
    }
    return new EditorImageProcessingModel(brightened, m.getMaxValue());
  }
}
