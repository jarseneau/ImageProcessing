import org.junit.Test;

import java.util.HashMap;

import model.AImageProcessingModel;
import model.IPixel;
import model.ImageProcessingModel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * To represent tests on an AImageProcessingModel.
 */
public class AImageProcessingModelTest {

  @Test
  public void testImageModelConstructor() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;

    ImageProcessingModel m = new AImageProcessingModel("temp", pixels);
    assertEquals(2, m.getImageWidth());
    assertEquals(2, m.getImageHeight());
    assertEquals(black, m.getPixelAt(0, 0));
    assertEquals(green, m.getPixelAt(0, 1));
    assertEquals(red, m.getPixelAt(1, 0));
    assertEquals(blue, m.getPixelAt(1, 1));

    IPixel[][] pixels2 = new IPixel[2][1];
    pixels[0][0] = black;
    pixels[1][0] = blue;

    ImageProcessingModel m2 = new AImageProcessingModel("temp2", pixels2);
    assertEquals(1, m2.getImageWidth());
    assertEquals(2, m2.getImageHeight());
    assertEquals(black, m.getPixelAt(0, 0));
    assertEquals(blue, m.getPixelAt(1, 0));
  }

  @Test
  public void testImageModelConstructorFail() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;
    try {
      ImageProcessingModel m = new AImageProcessingModel(null, pixels);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }

    try {
      ImageProcessingModel m = new AImageProcessingModel("hello", null);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }

    try {
      ImageProcessingModel m = new AImageProcessingModel("hello", new IPixel[1][0]);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided array cannot have a dimension of 0!", e.getMessage());
    }

    try {
      ImageProcessingModel m = new AImageProcessingModel("hello", new IPixel[0][1]);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided array cannot have a dimension of 0!", e.getMessage());
    }

    try {
      ImageProcessingModel m = new AImageProcessingModel("hello", new IPixel[0][0]);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Provided array cannot have a dimension of 0!", e.getMessage());
    }

  }

  @Test
  public void testGetHeight() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;

    ImageProcessingModel m = new AImageProcessingModel("image", pixels);

    assertEquals(2, m.getImageHeight());

    IPixel[][] pixels2 = new IPixel[14][2];

    ImageProcessingModel m2 = new AImageProcessingModel("image2", pixels2);

    assertEquals(14, m2.getImageHeight());
  }

  @Test
  public void testGetWidth() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;

    ImageProcessingModel m = new AImageProcessingModel("image", pixels);

    assertEquals(2, m.getImageHeight());

    IPixel[][] pixels2 = new IPixel[14][2];

    ImageProcessingModel m2 = new AImageProcessingModel("image2", pixels2);

    assertEquals(14, m2.getImageHeight());
  }
}