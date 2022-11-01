import org.junit.Test;

import java.util.HashMap;

import model.AImageProcessingModel;
import model.FlipHorizontalAdjustor;
import model.FlipVerticalAdjustor;
import model.IAdjustor;
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
    assertEquals("temp", m.getImageName());

    IPixel[][] pixels2 = new IPixel[2][1];
    pixels2[0][0] = black;
    pixels2[1][0] = blue;

    ImageProcessingModel m2 = new AImageProcessingModel("temp2", pixels2);
    assertEquals(1, m2.getImageWidth());
    assertEquals(2, m2.getImageHeight());
    assertEquals(black, m2.getPixelAt(0, 0));
    assertEquals(blue, m2.getPixelAt(1, 0));
    assertEquals("temp2", m2.getImageName());

    ImageProcessingModel m3 = new AImageProcessingModel("temp3", 4, 4);
    assertEquals(4, m3.getImageHeight());
    assertEquals(4, m3.getImageWidth());
    assertEquals("temp3", m3.getImageName());
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
      ImageProcessingModel m = new AImageProcessingModel(null, 1, 2);
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

    assertEquals(2, m.getImageWidth());

    IPixel[][] pixels2 = new IPixel[1][19];

    ImageProcessingModel m2 = new AImageProcessingModel("image2", pixels2);

    assertEquals(19, m2.getImageWidth());
  }

  @Test
  public void testGetPixelAt() {
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

    assertEquals(black, m.getPixelAt(0, 0));
    assertEquals(green, m.getPixelAt(0, 1));
    assertEquals(red, m.getPixelAt(1, 0));
    assertEquals(blue, m.getPixelAt(1, 1));
  }

  @Test
  public void testGetPixelAtFail() {
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

    try {
      m.getPixelAt(-1, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Row and col must be within bounds of the image.", e.getMessage());
    }

    try {
      m.getPixelAt(4, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Row and col must be within bounds of the image.", e.getMessage());
    }

    try {
      m.getPixelAt(6, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Row and col must be within bounds of the image.", e.getMessage());
    }

    try {
      m.getPixelAt(2, 7);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Row and col must be within bounds of the image.", e.getMessage());
    }
  }

  @Test
  public void testApply() {
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

    ImageProcessingModel m2 = m.apply("flipped_m_horizontal", new FlipHorizontalAdjustor());

    assertEquals(green, m2.getPixelAt(0, 0));
    assertEquals(black, m2.getPixelAt(0, 1));
    assertEquals(blue, m2.getPixelAt(1, 0));
    assertEquals(red, m2.getPixelAt(1, 1));

    ImageProcessingModel m4 = m.apply("flipped_m_vertical", new FlipVerticalAdjustor());

    assertEquals(red, m4.getPixelAt(0, 0));
    assertEquals(blue, m4.getPixelAt(0, 1));
    assertEquals(black, m4.getPixelAt(1, 0));
    assertEquals(green, m4.getPixelAt(1, 1));
  }
}