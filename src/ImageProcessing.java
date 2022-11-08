import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.ImageProcessingController;
import view.ImageProcessingView;
import view.PPMImageView;

/**
 * A main class to run this program.
 */
public class ImageProcessing {

  /**
   * a Main method.
   * @param args args.
   */
  public static void main(String[] args) {

    ImageProcessingView view = new PPMImageView();
    Readable input = new InputStreamReader(System.in);
    ImageProcessingController controller = new ConsoleController(input, view);
    controller.control();
  }
}
