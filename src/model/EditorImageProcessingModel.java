package model;

import java.util.Objects;

/**
 * To represent an image that has been created by editing an image
 * using the image processing program.
 */
public class EditorImageProcessingModel implements ImageProcessingModel {

  private final IPixel[][] pixels;
  private final int maxValue;
  private final int height;
  private final int width;

  /**
   * Constructs an image processing model that takes in an array of pixels as
   * its parameter.
   * @param pixels the given array of pixels to be used
   * @throws NullPointerException if pixels is null
   * @throws IllegalArgumentException if any dimension of pixels is 0
   */
  public EditorImageProcessingModel(IPixel[][] pixels, int maxValue)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(pixels);
    if (pixels.length == 0 || pixels[0].length == 0) {
      throw new IllegalArgumentException("Provided array cannot have a dimension of 0!");
    } else if (maxValue < 0) {
      throw new IllegalArgumentException("The provided max value cannot be negative.");
    }
    this.maxValue = maxValue;
    this.pixels = pixels;
    this.width = pixels[0].length;
    this.height = pixels.length;
  }

  @Override
  public int getImageWidth() {
    return this.width;
  }

  @Override
  public int getImageHeight() {
    return this.height;
  }

  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  @Override
  public IPixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row > this.height || col > this.width || row < 0 || col < 0) {
      throw new IllegalArgumentException("Row and col must be within bounds of the image.");
    }
    return this.pixels[row][col];
  }

  @Override
  public ImageProcessingModel apply(IImageAdjustor adjustor) throws IllegalArgumentException,
          NullPointerException {
    Objects.requireNonNull(adjustor);
    return adjustor.adjust(this);
  }

  @Override
  public int[][] histogram() {
    int[][] h = new int[4][256];

    for (IPixel[] row : pixels) {
      for (IPixel p : row) {
        h[0][p.getChannel("red")] += 1;
        h[1][p.getChannel("green")] += 1;
        h[2][p.getChannel("blue")] += 1;
        h[3][p.getChannel("intensity")] += 1;
      }
    }

    return h;
  }
}
