import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.ImageProcessingController;
import view.ImageProcessingView;
import view.PPMImageView;

public class ImageProcessing {
  public static void main(String[] args) {
    ImageProcessingView view = new PPMImageView();
    Readable input = new InputStreamReader(System.in);
    ImageProcessingController controller = new ConsoleController(input, view);
    controller.control();
  }
}
