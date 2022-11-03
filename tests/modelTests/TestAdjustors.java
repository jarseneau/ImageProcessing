package modelTests;

import org.junit.Test;

import model.BrightenAdjustor;
import model.EditorImageProcessingModel;
import model.FlipHorizontalAdjustor;
import model.FlipVerticalAdjustor;
import model.GrayscaleAdjustor;
import model.IPixel;
import model.ImageProcessingModel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * To represent tests on a FlipHorizontalAdjustor
 */
public class TestAdjustors {

  /**
   * Helper method to get the luma of red green and blue
   * @param red the red value
   * @param green the green value
   * @param blue the blue value
   * @return the luma of these three values
   */
  public int getLuma(int red, int green, int blue) {
    return (int) Math.round(0.2126 * red + 0.7152 * green + 0.0722 * blue);
  }

  @Test
  public void testFlipHorizontalApply() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);

    ImageProcessingModel m2 = m.apply(new FlipHorizontalAdjustor());

    assertEquals(green, m2.getPixelAt(0, 0));
    assertEquals(black, m2.getPixelAt(0, 1));
    assertEquals(blue, m2.getPixelAt(1, 0));
    assertEquals(red, m2.getPixelAt(1, 1));

    ImageProcessingModel m3 = m2.apply(new FlipHorizontalAdjustor());

    assertEquals(black, m3.getPixelAt(0, 0));
    assertEquals(green, m3.getPixelAt(0, 1));
    assertEquals(red, m3.getPixelAt(1, 0));
    assertEquals(blue, m3.getPixelAt(1, 1));

    IPixel white = new RGBPixel(255, 255, 255, 255);
    IPixel purple = new RGBPixel(255, 0, 255, 255);
    IPixel yellow = new RGBPixel(255, 255, 0, 255);
    IPixel teal = new RGBPixel(0, 255, 255, 255);
    IPixel gray = new RGBPixel(100, 100, 100, 255);


    IPixel[][] pixels2 = new IPixel[3][3];
    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m4 = new EditorImageProcessingModel(pixels2, 255);

    ImageProcessingModel m5 = m4.apply(new FlipHorizontalAdjustor());

    assertEquals(white, m5.getPixelAt(0, 0));
    assertEquals(green, m5.getPixelAt(0, 1));
    assertEquals(black, m5.getPixelAt(0, 2));
    assertEquals(purple, m5.getPixelAt(1, 0));
    assertEquals(blue, m5.getPixelAt(1, 1));
    assertEquals(red, m5.getPixelAt(1, 2));
    assertEquals(gray, m5.getPixelAt(2, 0));
    assertEquals(teal, m5.getPixelAt(2, 1));
    assertEquals(yellow, m5.getPixelAt(2, 2));
  }

  @Test
  public void testFlipHorizontalNull() {
    try {
      new FlipHorizontalAdjustor().adjust(null);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testFlipVerticalApply() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);

    ImageProcessingModel m2 = m.apply(new FlipVerticalAdjustor());

    assertEquals(red, m2.getPixelAt(0, 0));
    assertEquals(blue, m2.getPixelAt(0, 1));
    assertEquals(black, m2.getPixelAt(1, 0));
    assertEquals(green, m2.getPixelAt(1, 1));

    ImageProcessingModel m3 = m2.apply(new FlipVerticalAdjustor());

    assertEquals(black, m3.getPixelAt(0, 0));
    assertEquals(green, m3.getPixelAt(0, 1));
    assertEquals(red, m3.getPixelAt(1, 0));
    assertEquals(blue, m3.getPixelAt(1, 1));

    IPixel white = new RGBPixel(255, 255, 255, 255);
    IPixel purple = new RGBPixel(255, 0, 255, 255);
    IPixel yellow = new RGBPixel(255, 255, 0, 255);
    IPixel teal = new RGBPixel(0, 255, 255, 255);
    IPixel gray = new RGBPixel(100, 100, 100, 255);


    IPixel[][] pixels2 = new IPixel[3][3];
    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m4 = new EditorImageProcessingModel(pixels2, 255);

    ImageProcessingModel m5 = m4.apply(new FlipVerticalAdjustor());

    assertEquals(yellow, m5.getPixelAt(0, 0));
    assertEquals(teal, m5.getPixelAt(0, 1));
    assertEquals(gray, m5.getPixelAt(0, 2));
    assertEquals(red, m5.getPixelAt(1, 0));
    assertEquals(blue, m5.getPixelAt(1, 1));
    assertEquals(purple, m5.getPixelAt(1, 2));
    assertEquals(black, m5.getPixelAt(2, 0));
    assertEquals(green, m5.getPixelAt(2, 1));
    assertEquals(white, m5.getPixelAt(2, 2));
  }

  @Test
  public void testFlipVerticalNull() {
    try {
      new FlipVerticalAdjustor().adjust(null);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testBrightenAdjustor() {
    IPixel gray = new RGBPixel(10, 10, 10, 255);
    IPixel green = new RGBPixel(0, 245, 0, 255);
    IPixel red = new RGBPixel(245, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 245, 255);

    IPixel[][] pixels = new IPixel[2][2];
    pixels[0][0] = gray;
    pixels[0][1] = green;
    pixels[1][0] = red;
    pixels[1][1] = blue;

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);
    ImageProcessingModel m2 = m.apply(new BrightenAdjustor(20));

    assertEquals(30, m2.getPixelAt(0, 0).getChannel("red"));
    assertEquals(30, m2.getPixelAt(0, 0).getChannel("green"));
    assertEquals(30, m2.getPixelAt(0, 0).getChannel("blue"));

    assertEquals(20, m2.getPixelAt(0, 1).getChannel("red"));
    assertEquals(255, m2.getPixelAt(0, 1).getChannel("green"));
    assertEquals(20, m2.getPixelAt(0, 1).getChannel("blue"));

    assertEquals(255, m2.getPixelAt(1, 0).getChannel("red"));
    assertEquals(20, m2.getPixelAt(1, 0).getChannel("green"));
    assertEquals(20, m2.getPixelAt(1, 0).getChannel("blue"));

    assertEquals(20, m2.getPixelAt(1, 1).getChannel("red"));
    assertEquals(20, m2.getPixelAt(1, 1).getChannel("green"));
    assertEquals(255, m2.getPixelAt(1, 1).getChannel("blue"));

    ImageProcessingModel m3 = m.apply(new BrightenAdjustor(-20));

    assertEquals(0, m3.getPixelAt(0, 0).getChannel("red"));
    assertEquals(0, m3.getPixelAt(0, 0).getChannel("green"));
    assertEquals(0, m3.getPixelAt(0, 0).getChannel("blue"));

    assertEquals(0, m3.getPixelAt(0, 1).getChannel("red"));
    assertEquals(225, m3.getPixelAt(0, 1).getChannel("green"));
    assertEquals(0, m3.getPixelAt(0, 1).getChannel("blue"));

    assertEquals(225, m3.getPixelAt(1, 0).getChannel("red"));
    assertEquals(0, m3.getPixelAt(1, 0).getChannel("green"));
    assertEquals(0, m3.getPixelAt(1, 0).getChannel("blue"));

    assertEquals(0, m3.getPixelAt(1, 1).getChannel("red"));
    assertEquals(0, m3.getPixelAt(1, 1).getChannel("green"));
    assertEquals(225, m3.getPixelAt(1, 1).getChannel("blue"));
  }

  @Test
  public void testBrightenerNull() {
    try {
      new BrightenAdjustor(10).adjust(null);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testGrayscaleAdjustor() {
    IPixel black = new RGBPixel(0, 0, 0, 255);
    IPixel green = new RGBPixel(0, 255, 0, 255);
    IPixel white = new RGBPixel(255, 255, 255, 255);
    IPixel red = new RGBPixel(255, 0, 0, 255);
    IPixel blue = new RGBPixel(0, 0, 255, 255);
    IPixel purple = new RGBPixel(255, 0, 255, 255);
    IPixel yellow = new RGBPixel(255, 255, 0, 255);
    IPixel teal = new RGBPixel(0, 255, 255, 255);
    IPixel gray = new RGBPixel(100, 100, 100, 255);


    IPixel[][] pixels = new IPixel[3][3];
    pixels[0][0] = black;
    pixels[0][1] = green;
    pixels[0][2] = white;
    pixels[1][0] = red;
    pixels[1][1] = blue;
    pixels[1][2] = purple;
    pixels[2][0] = yellow;
    pixels[2][1] = teal;
    pixels[2][2] = gray;

    black = new RGBPixel(0, 0, 0, 255);
    green = new RGBPixel(0, 0, 0, 255);
    white = new RGBPixel(255, 255, 255, 255);
    red = new RGBPixel(255, 255, 255, 255);
    blue = new RGBPixel(0, 0, 0, 255);
    purple = new RGBPixel(255, 255, 255, 255);
    yellow = new RGBPixel(255, 255, 255, 255);
    teal = new RGBPixel(0, 0, 0, 255);
    gray = new RGBPixel(100, 100, 100, 255);


    IPixel[][] pixels2 = new IPixel[3][3];
    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    String[] channels = new String[3];

    channels[0] = "red";
    channels[1] = "green";
    channels[2] = "blue";

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);

    ImageProcessingModel m1 = m.apply(new GrayscaleAdjustor("red"));

    for (int row = 0; row < m1.getImageHeight(); row++ ) {
      for (int col = 0; col < m1.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(pixels2[row][col].getChannel(channel),
                  m1.getPixelAt(row, col).getChannel(channel));
        }
      }
    }

    black = new RGBPixel(0, 0, 0, 255);
    green = new RGBPixel(255, 255, 255, 255);
    white = new RGBPixel(255, 255, 255, 255);
    red = new RGBPixel(0, 0, 0, 255);
    blue = new RGBPixel(0, 0, 0, 255);
    purple = new RGBPixel(0, 0, 0, 255);
    yellow = new RGBPixel(255, 255, 255, 255);
    teal = new RGBPixel(255, 255, 255, 255);
    gray = new RGBPixel(100, 100, 100, 255);

    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m2 = m.apply(new GrayscaleAdjustor("green"));

    for (int row = 0; row < m2.getImageHeight(); row++ ) {
      for (int col = 0; col < m2.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(pixels2[row][col].getChannel(channel),
                  m2.getPixelAt(row, col).getChannel(channel));
        }
      }
    }

    black = new RGBPixel(0, 0, 0, 255);
    green = new RGBPixel(0, 0, 0, 255);
    white = new RGBPixel(255, 255, 255, 255);
    red = new RGBPixel(0, 0, 0, 255);
    blue = new RGBPixel(255, 255, 255, 255);
    purple = new RGBPixel(255, 255, 255, 255);
    yellow = new RGBPixel(0, 0, 0, 255);
    teal = new RGBPixel(255, 255, 255, 255);
    gray = new RGBPixel(100, 100, 100, 255);

    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m3 = m.apply(new GrayscaleAdjustor("blue"));

    for (int row = 0; row < m3.getImageHeight(); row++ ) {
      for (int col = 0; col < m3.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(pixels2[row][col].getChannel(channel),
                  m3.getPixelAt(row, col).getChannel(channel));
        }
      }
    }

    black = new RGBPixel(0, 0, 0, 255);
    green = new RGBPixel(255, 255, 255, 255);
    white = new RGBPixel(255, 255, 255, 255);
    red = new RGBPixel(255, 255, 255, 255);
    blue = new RGBPixel(255, 255, 255, 255);
    purple = new RGBPixel(255, 255, 255, 255);
    yellow = new RGBPixel(255, 255, 255, 255);
    teal = new RGBPixel(255, 255, 255, 255);
    gray = new RGBPixel(100, 100, 100, 255);

    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m4 = m.apply(new GrayscaleAdjustor("value"));

    for (int row = 0; row < m4.getImageHeight(); row++ ) {
      for (int col = 0; col < m4.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(pixels2[row][col].getChannel(channel),
                  m4.getPixelAt(row, col).getChannel(channel));
        }
      }
    }

     black = new RGBPixel(0, 0, 0, 255);
     green = new RGBPixel(85, 85, 85, 255);
     white = new RGBPixel(255, 255, 255, 255);
     red = new RGBPixel(85, 85, 85, 255);
     blue = new RGBPixel(85, 85, 85, 255);
     purple = new RGBPixel(170, 170, 170, 255);
     yellow = new RGBPixel(170, 170, 170, 255);
     teal = new RGBPixel(170, 170, 170, 255);
     gray = new RGBPixel(100, 100, 100, 255);

    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m5 = m.apply(new GrayscaleAdjustor("intensity"));

    for (int row = 0; row < m5.getImageHeight(); row++ ) {
      for (int col = 0; col < m5.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(pixels2[row][col].getChannel(channel),
                  m5.getPixelAt(row, col).getChannel(channel));
        }
      }
    }

     black = new RGBPixel(this.getLuma(0, 0, 0),
             this.getLuma(0, 0, 0),
             this.getLuma(0, 0, 0),
             255);
     green = new RGBPixel(this.getLuma(0, 255, 0),
             this.getLuma(0, 255, 0),
             this.getLuma(0, 255, 0),
             255);
     white = new RGBPixel(255, 255, 255, 255);
     red = new RGBPixel(this.getLuma(255, 0, 0),
             this.getLuma(255, 0, 0),
             this.getLuma(255, 0, 0),
             255);
     blue = new RGBPixel(this.getLuma(0, 0, 255),
             this.getLuma(0, 0, 255),
             this.getLuma(0, 0, 255),
             255);
     purple = new RGBPixel(this.getLuma(255, 0, 255),
             this.getLuma(255, 0, 255),
             this.getLuma(255, 0, 255),
             255);
     yellow = new RGBPixel(this.getLuma(255, 255, 0),
             this.getLuma(255, 255, 0),
             this.getLuma(255, 255, 0),
             255);
     teal = new RGBPixel(this.getLuma(0, 255, 255),
             this.getLuma(0, 255, 255),
             this.getLuma(0, 255, 255),
             255);
     gray = new RGBPixel(this.getLuma(100, 100, 100),
             this.getLuma(100, 100, 100),
             this.getLuma(100, 100, 100),
             255);

    pixels2[0][0] = black;
    pixels2[0][1] = green;
    pixels2[0][2] = white;
    pixels2[1][0] = red;
    pixels2[1][1] = blue;
    pixels2[1][2] = purple;
    pixels2[2][0] = yellow;
    pixels2[2][1] = teal;
    pixels2[2][2] = gray;

    ImageProcessingModel m6 = m.apply(new GrayscaleAdjustor("luma"));

    for (int row = 0; row < m6.getImageHeight(); row++ ) {
      for (int col = 0; col < m6.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(pixels2[row][col].getChannel(channel),
                  m6.getPixelAt(row, col).getChannel(channel));
        }
      }
    }
  }

  @Test
  public void testGrayscaleConstructorFail() {
    try {
      IPixel black = new RGBPixel(0, 0, 0, 255);
      IPixel green = new RGBPixel(0, 255, 0, 255);
      IPixel red = new RGBPixel(255, 0, 0, 255);
      IPixel blue = new RGBPixel(0, 0, 255, 255);

      IPixel[][] pixels = new IPixel[2][2];
      pixels[0][0] = black;
      pixels[0][1] = green;
      pixels[1][0] = red;
      pixels[1][1] = blue;

      ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);
      ImageProcessingModel m2 = m.apply(new GrayscaleAdjustor(null));
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testGrayscaleChannelNotFound() {
    try {
      IPixel black = new RGBPixel(0, 0, 0, 255);
      IPixel green = new RGBPixel(0, 255, 0, 255);
      IPixel red = new RGBPixel(255, 0, 0, 255);
      IPixel blue = new RGBPixel(0, 0, 255, 255);

      IPixel[][] pixels = new IPixel[2][2];
      pixels[0][0] = black;
      pixels[0][1] = green;
      pixels[1][0] = red;
      pixels[1][1] = blue;
      ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);
      ImageProcessingModel m2 = m.apply(new GrayscaleAdjustor("alpha"));
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Channel specified does not exist in this pixel.", e.getMessage());
    }
  }

  @Test
  public void testGrayscaleNull() {
    try {
      new GrayscaleAdjustor("red").adjust(null);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }
  }
}