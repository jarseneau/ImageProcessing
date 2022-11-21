package model;

import java.util.Objects;

/**
 * A class representing a mock model image processing model.
 * Uses a log to record interactions with the controller.
 */
public class MockModel implements ImageProcessingModel {
  final StringBuilder log;

  /**
   * a constructor for MockModel.
   * @param log the stringBuilder to be used.
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public int getImageWidth() {
    this.log.append("getting Image Width\n");
    return 0;
  }

  @Override
  public int getImageHeight() {
    this.log.append("getting Image Height\n");
    return 0;
  }

  @Override
  public int getMaxValue() {
    this.log.append("getting max value\n");
    return 0;
  }

  @Override
  public IPixel getPixelAt(int row, int col) throws IllegalArgumentException {
    this.log.append("getting pixel at: (" + row + "," + col + ")\n");
    return null;
  }

  @Override
  public ImageProcessingModel apply(IImageAdjustor adjustor) throws NullPointerException {
    log.append(adjustor.getClass().getName() + " is being called\n");
    return this;
  }

  @Override
  public int[][] histogram() {
    return new int[0][];
  }

  /**
   * A method to observe the log of this mock model.
   * @return this log
   */
  public StringBuilder getLog() {
    return this.log;
  }
}
