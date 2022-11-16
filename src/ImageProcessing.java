import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.ImageProcessingController;
import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageGUIView;
import view.ImageIOView;
import view.ImageProcessingRenderedView;
import view.ImageProcessingView;

/**
 * A main class to run this program.
 */
public class ImageProcessing {

  /**
   * a Main method.
   * @param args args.
   */
  public static void main(String[] args) throws FileNotFoundException {
    try {
      String[] comms = new String[5];
      comms[0] = "Blur";
      comms[1] = "Sepia";
      comms[2] = "Red";
      comms[3] = "apple";
      comms[4] = "milk";
      ImageProcessingRenderedView v = new ImageGUIView(comms);
    } catch (IOException e) {
      throw new IllegalArgumentException("hfjadf");
    }
    /*
    ImageProcessingView v = new ImageIOView();
    ImageProcessingController controller;
    if (args != null) {
      controller = new ConsoleController(new FileReader(args[0]), v);
    }
    else {
      controller = new ConsoleController(new InputStreamReader(System.in), v);
    }
    controller.control();
  }
}
