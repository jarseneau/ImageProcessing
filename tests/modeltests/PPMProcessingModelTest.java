package modeltests;

import org.junit.Test;

import model.ImageProcessingModel;
import model.PPMProcessingModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * To represent tests on the PPMProcessingModel.
 */
public class PPMProcessingModelTest {

  @Test
  public void testConstructPPMImageModel() {
    // testFile is a ppm image with 4 pixels, black in top left corner, green in top right corner
    // red in bottom left corner, and blue in bottom right corner
    ImageProcessingModel m = new PPMProcessingModel("tests/modelTests/testFile.ppm");

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

  @Test
  public void testConstructPPMImageModelFail() {
    try {
      // badPPM is a ppm file with P4 as its header
      ImageProcessingModel m2 = new PPMProcessingModel("tests/modelTests/badPPM.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    }

    try {
      // badPPM2 is a ppm file with "hello" in front of its height
      ImageProcessingModel m2 = new PPMProcessingModel("tests/modelTests/badPPM2.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: there is text where there shouldn't be.",
              e.getMessage());
    }

    try {
      // badPPM3 is a ppm file with "uh-oh" in front of a pixel's rgb value
      ImageProcessingModel m2 = new PPMProcessingModel("tests/modelTests/badPPM3.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: there is text where there shouldn't be.",
              e.getMessage());
    }

    try {
      // badPPM4 is a ppm file with an image dimension of 0 by 0
      ImageProcessingModel m2 = new PPMProcessingModel("tests/modelTests/badPPM4.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided array cannot have a dimension of 0!", e.getMessage());
    }

    try {
      // unicorn.ppm does not exist
      ImageProcessingModel m2 = new PPMProcessingModel("tests/modelTests/unicorn.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("File tests/modelTests/unicorn.ppm not found!",
              e.getMessage());
    }
  }

}