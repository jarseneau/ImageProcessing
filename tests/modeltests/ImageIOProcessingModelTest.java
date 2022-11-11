package modeltests;

import org.junit.Test;

import model.ImageIOProcessingModel;
import model.ImageProcessingModel;

import static org.junit.Assert.assertEquals;

/**
 * To represent tests on the ImageIOProcessingModel class.
 */
public class ImageIOProcessingModelTest {

  @Test
  public void testConstructImageIOImageModel() {
    // testFile is a png image with 4 pixels, black in top left corner, green in top right corner
    // red in bottom left corner, and blue in bottom right corner
    ImageProcessingModel m = new ImageIOProcessingModel("tests/modelTests/testFile.png");

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