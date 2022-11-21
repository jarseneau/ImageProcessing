import java.io.FileNotFoundException;
import java.io.IOException;

import controller.GUIController;
import controller.ImageProcessingController;
import controller.ImageProcessingFeatures;
import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import view.ImageGUIView;
import view.ImageProcessingRenderedView;

/**
 * A main class to run this program.
 */
public class ImageProcessing {

  /**
   * a Main method.
   * @param args args.
   */
  public static void main(String[] args) {
    try {
      ImageProcessingModel m = new ImageIOProcessingModel("res/lake.png");
      ImageProcessingRenderedView v = new ImageGUIView(m);
      ImageProcessingFeatures c = new GUIController(m, v);
    } catch (IOException e) {
      throw new IllegalStateException("uh oh");
    }
  }
}
