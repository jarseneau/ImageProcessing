package model;

import java.util.HashMap;
import java.util.Objects;

public class AImageProcessingModel implements ImageProcessingModel {

  private final String imageName;
  private final IPixel[][] pixels;
  private final int height;
  private final int width;

  /**
   * Constructs an image processing model that takes in the name of the file.
   * @param imageName the name of the image to be created
   */
  public AImageProcessingModel(String imageName, int width, int height)
          throws IllegalArgumentException {
    this(imageName, new IPixel[height][width]);
  }

  /**
   * Constructs an image processing model that takes in an array of pixels as
   * its parameter.
   * @param imageName the name of the image to be created
   * @param pixels the given array of pixels to be used
   */
  public AImageProcessingModel(String imageName, IPixel[][] pixels) throws IllegalArgumentException {
    Objects.requireNonNull(imageName);
    Objects.requireNonNull(pixels);
    if (pixels.length <= 0 || pixels[0].length <= 0) {
      throw new IllegalArgumentException("Provided array cannot have a dimension of 0!");
    }
    this.imageName = imageName;
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
  public IPixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row > this.height || col > this.width || row < 0 || col < 0) {
      throw new IllegalArgumentException("Row and col must be within bounds of the image.");
    }
    return this.pixels[row][col];
  }

  @Override
  public ImageProcessingModel apply(IAdjustor adjustor) {
    return adjustor.apply(this);
  }
}
