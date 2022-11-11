package model;

import java.util.Objects;

/**
 * To represent any adjustor that uses a filtering operation on the pixels
 * of an image.
 */

public abstract class AFilteringAdjustor implements IImageAdjustor {

  protected final Double[][] kernel;

  /**
   * Constructs a filtering adjustor by making a kernel specified
   * by the subclass.
   */
  AFilteringAdjustor() {
    this.kernel = this.makeKernel();
  }

  /**
   * Makes a kernel of scalars by which to multiply pixel values of an image. The
   * exact scalars of this kernel are specific to each subclass.
   * @return the kernel as a 2D array of doubles
   */
  protected abstract Double[][] makeKernel();

  /**
   * Creates a new ImageProcessingModel where each pixel from the given model
   * have an image filtering process run on it and the surrounding pixels based
   * on the kernel this adjustor creates.
   *
   * @param m the model of the image being operated on
   * @return the given image adjusted by the kernel matrix.
   * @throws NullPointerException if the model provided is null
   */
  @Override
  public ImageProcessingModel adjust(ImageProcessingModel m) throws NullPointerException {
    Objects.requireNonNull(m);
    IPixel[][] filtered = new IPixel[m.getImageHeight()][m.getImageWidth()];
    for (int row = 0; row < m.getImageHeight(); row++) {
      for (int col = 0; col < m.getImageWidth(); col++) {
        filtered[row][col] = m.getPixelAt(0, 0).addAll(- (m.getMaxValue() + 1));
        for (String channel: m.getPixelAt(row, col).getMainChannels()) {
          filtered[row][col] = filtered[row][col]
                  .modifyChannel(channel, this.newChannelVal(m, channel, row, col));
        }
      }
    }
    return new EditorImageProcessingModel(filtered, m.getMaxValue());
  }

  /**
   * Returns the new channel value based on the surrounding pixels in the image
   * and the kernel values of the kernel matrix.
   */
  private int newChannelVal(ImageProcessingModel m, String channel, int row, int col) {
    double runningTotal = 0;
    for (int kRow = 0; kRow < this.kernel.length; kRow++) {
      for (int kCol = 0; kCol < this.kernel[0].length; kCol++) {
        int curPixelRow = row - (this.kernel.length / 2) + kRow;
        int curPixelCol = col - (this.kernel.length / 2) + kCol;
        if (curPixelRow >= 0 && curPixelRow < m.getImageHeight()
                && curPixelCol >= 0 && curPixelCol < m.getImageWidth()) {
          runningTotal += m.getPixelAt(curPixelRow, curPixelCol).getChannel(channel)
                  * this.kernel[kRow][kCol];
        }
      }
    }
    return (int) Math.round(runningTotal);
  }
}

