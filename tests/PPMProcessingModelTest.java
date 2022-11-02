import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.ImageProcessingModel;
import model.PPMProcessingModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;


/**
 * To represent tests on the PPMProcessingModel
 */
public class PPMProcessingModelTest {

  @Test
  public void testConstructPPMImageModel() {
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

    assertEquals(0, m.getPixelAt(0, 0).getChannel("red"));
    assertEquals(0, m.getPixelAt(0, 0).getChannel("green"));
    assertEquals(0, m.getPixelAt(0, 0).getChannel("blue"));

    assertEquals(0, m.getPixelAt(0, 1).getChannel("red"));
    assertEquals(255, m.getPixelAt(0, 1).getChannel("green"));
    assertEquals(0, m.getPixelAt(0, 1).getChannel("blue"));

    assertEquals(255, m.getPixelAt(1, 0).getChannel("red"));
    assertEquals(0, m.getPixelAt(1, 0).getChannel("green"));
    assertEquals(0, m.getPixelAt(1, 0).getChannel("blue"));

    assertEquals(0, m.getPixelAt(1, 1).getChannel("red"));
    assertEquals(0, m.getPixelAt(1, 1).getChannel("green"));
    assertEquals(255, m.getPixelAt(1, 1).getChannel("blue"));
  }

}