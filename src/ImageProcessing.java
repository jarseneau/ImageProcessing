import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.ImageProcessingController;
import model.BlurFilteringAdjustor;
import model.GrayscaleLumaTransformation;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import model.SepiaTransformation;
import model.SharpenFilteringAdjustor;
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

    ImageProcessingModel m = new PPMProcessingModel("Koala.ppm");
    ImageProcessingView v = new PPMImageView();
    v.save("gray.ppm", m.apply(new SepiaTransformation()));
  }
}
