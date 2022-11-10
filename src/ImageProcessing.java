import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.ImageProcessingController;
import model.BlurFilteringAdjustor;
import model.GrayscaleLumaTransformation;
import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import model.SepiaTransformation;
import model.SharpenFilteringAdjustor;
import view.ImageIOView;
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

    ImageProcessingModel m = new ImageIOProcessingModel("manhattan-small.png");
    ImageProcessingView v = new ImageIOView();
    v.save("sharp.jpg", m.apply(new SharpenFilteringAdjustor()).apply(new SharpenFilteringAdjustor()));
  }
}
