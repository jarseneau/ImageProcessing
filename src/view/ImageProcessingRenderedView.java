package view;

import model.ImageProcessingModel;

public interface ImageProcessingRenderedView extends ImageProcessingView {
  /**
   * Renders the current state of the image processor.
   */
  void render(ImageProcessingModel m);
}
