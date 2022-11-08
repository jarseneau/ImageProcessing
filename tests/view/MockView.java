package view;

import java.util.Objects;

import model.ImageProcessingModel;

/**
 * a Mock version of the view for testing only.
 */
public class MockView implements ImageProcessingView {
  final StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void save(String filename, ImageProcessingModel model) throws IllegalStateException,
          NullPointerException {
    log.append("Saving model to path: " + filename + "\n");
  }

  @Override
  public void renderMessage(String message) throws NullPointerException {
    // do nothing, mock!
  }

  /**
   * observes the log of this mock view.
   */
  public StringBuilder getLog() {
    return this.log;
  }
}
