import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.GrayscaleAdjustor;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageProcessingView;
import view.PPMImageView;


/**
 * To represent tests on the PPMImageView class.
 */
public class PPMImageViewTest {

  @Test
  public void testSave() {
    String testText = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "0\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n";

    ImageProcessingModel m = new PPMProcessingModel(testText);

    ImageProcessingView v = new PPMImageView(m);

    v.save("4pixels.ppm");
  }
}