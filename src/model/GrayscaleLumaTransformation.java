package model;

/**
 * This class represents a grayscale operation based on the luma of the pixel
 * done with color transformation.
 */
public class GrayscaleLumaTransformation extends AColorTransformationAdjustor {

  @Override
  protected Double[][] makeColorScalars() {
    return new Double[][]{{0.2126, 0.7152, 0.0722},
                          {0.2126, 0.7152, 0.0722},
                          {0.2126, 0.7152, 0.0722}};
  }
}
