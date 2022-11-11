import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.ImageProcessingController;
import view.ImageIOView;
import view.ImageProcessingView;

/**
 * A main class to run this program.
 */
public class ImageProcessing {

  /**
   * a Main method.
   * @param args args.
   */
  public static void main(String[] args) {
    ImageProcessingView v = new ImageIOView();
    ImageProcessingController controller =
            new ConsoleController(new InputStreamReader(System.in), v);
    controller.control();
  }
}
