package model;

/**
 * To represent a filter adjustor that blurs an image.
 */
public class BlurFilteringAdjustor extends AFilteringAdjustor {

  @Override
  protected Double[][] makeKernel() {
    return new Double[][]{{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
                          {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
                          {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}};
  }
}
