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

/**
 * To represent tests on a FlipHorizontalAdjustor
 */
public class TestAdjustors {
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

    IPixel black2 = new RGBPixel(0, 0, 0, 255);
    IPixel green2 = new RGBPixel(0, 0, 0, 255);
    IPixel white2 = new RGBPixel(255, 255, 255, 255);
    IPixel red2 = new RGBPixel(255, 255, 255, 255);
    IPixel blue2 = new RGBPixel(0, 0, 0, 255);
    IPixel purple2 = new RGBPixel(255, 255, 255, 255);
    IPixel yellow2 = new RGBPixel(255, 255, 255, 255);
    IPixel teal2 = new RGBPixel(0, 0, 0, 255);
    IPixel gray2 = new RGBPixel(100, 100, 100, 255);


    IPixel[][] pixels2 = new IPixel[3][3];
    pixels2[0][0] = black2;
    pixels2[0][1] = green2;
    pixels2[0][2] = white2;
    pixels2[1][0] = red2;
    pixels2[1][1] = blue2;
    pixels2[1][2] = purple2;
    pixels2[2][0] = yellow2;
    pixels2[2][1] = teal2;
    pixels2[2][2] = gray2;

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
  }
}