package model;

import java.util.Objects;

/**
 * To represent any adjustor that uses a color transformation matrix
 * to modify a pixel colors.
 */
public abstract class AColorTransformationAdjustor implements IImageAdjustor{

  Double[][] colorScalars;

  /**
   * Constructs a color transformation adjustor with the
   * scalars being determined by a subclass.
   */
  public AColorTransformationAdjustor() {
    colorScalars = this.makeColorScalars();
  }

  protected abstract Double[][] makeColorScalars();

  /**
   * Adjusts the image by the color transformation matrix.
   * @param m the model of the image being operated on
   * @return the given image adjusted by the color transformation matrix
   * @throws NullPointerException if the given model is null
   * @throws IllegalArgumentException if the provided image is made of pixels
   * without rgb values
   */
  @Override
  public ImageProcessingModel adjust(ImageProcessingModel m)
          throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(m);
    IPixel[][] transformed = new IPixel[m.getImageHeight()][m.getImageWidth()];
    String[] channels = new String[]{"red", "green", "blue"};
    for (int row = 0; row < m.getImageHeight(); row++) {
      for (int col = 0; col < m.getImageWidth(); col++) {
        transformed[row][col] = m.getPixelAt(0, 0).addAll(- (m.getMaxValue() + 1));
        for (int channel = 0; channel < channels.length; channel++ ) {
          transformed[row][col] = transformed[row][col].modifyChannel(channels[channel],
                  this.transformColors(m.getPixelAt(row, col), channel));
        }
      }
    }
    return new EditorImageProcessingModel(transformed, m.getMaxValue());
  }

  private int transformColors(IPixel pixel, int row) {
    return (int) Math.round(this.colorScalars[row][0] * pixel.getChannel("red")
            + this.colorScalars[row][1] * pixel.getChannel("green")
            + this.colorScalars[row][2] * pixel.getChannel("blue"));
  }
}
