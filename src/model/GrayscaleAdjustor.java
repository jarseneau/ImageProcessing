package model;

import java.util.Objects;

/**
 * This class represents an adjustor on a PPM image that brightens every
 * pixel by a value given by the constructor.
 */

public class GrayscaleAdjustor implements IImageAdjustor {

  private final String component;

  /**
   * Creates a new GrayscaleAdjustor which remembers the component that is
   * to be grayscale for when the adjust method is called.
   *
   * @param component the component that will be used for the grayscale
   * @throws NullPointerException if the given component name is null
   */
  public GrayscaleAdjustor(String component) {
    Objects.requireNonNull(component);
    this.component = component;
  }

  /**
   * Creates a new ImageProcessingModel where each pixel from the given model
   * is represented as a grayscale pixel with its value corresponding to the
   * component selected.
   *
   * @param m the model of the image being operated on
   * @return a new ImageProcessingModel made from the grayscale pixels
   * @throws IllegalArgumentException if the component that is passed in is not contained
   *                                  within the pixel
   * @throws NullPointerException if the model provided is null
   */
  @Override
  public ImageProcessingModel adjust(ImageProcessingModel m) throws IllegalArgumentException,
          NullPointerException {
    Objects.requireNonNull(m);
    IPixel[][] grayscaled = new IPixel[m.getImageHeight()][m.getImageWidth()];
    for (int row = 0; row < m.getImageHeight(); row++ ) {
      for (int col = 0; col < m.getImageWidth(); col++ ) {
        int value = m.getPixelAt(row, col).getChannel(this.component);
        grayscaled[row][col] = new RGBPixel(value, value, value, m.getMaxValue());
      }
    }
    return new EditorImageProcessingModel(grayscaled, m.getMaxValue());
  }
}
