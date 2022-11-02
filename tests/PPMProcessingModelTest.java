import org.junit.Test;

import model.ImageProcessingModel;
import model.PPMProcessingModel;

import static org.junit.Assert.assertEquals;


/**
 * To represent tests on the PPMProcessingModel
 */
public class PPMProcessingModelTest {

  @Test
  public void testConstructPPMImageModel() {
    ImageProcessingModel m = new PPMProcessingModel("tests/testFile.txt");

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