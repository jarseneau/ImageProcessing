package view;

import java.awt.event.ActionListener;

import controller.GUIController;
import controller.ImageProcessingFeatures;
import model.ImageProcessingModel;

public interface ImageProcessingRenderedView extends ImageProcessingView {
  /**
   * Renders the current state of the image processor.
   */
  void render(ImageProcessingModel m);

  /**
   * Adds the list of commands provided to the list of options.
   */
  void addCommands(String[] commands);

  void addFeatures(ImageProcessingFeatures features);

  void addActionListener(ActionListener listener);

  String loadFile();

  String saveFile();

  void updateImage(ImageProcessingModel m);
}
