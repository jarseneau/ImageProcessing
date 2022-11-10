package viewtests;

import org.junit.Test;

import model.EditorImageProcessingModel;
import model.IPixel;
import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import model.RGBPixel;
import view.ImageIOView;
import view.ImageProcessingView;
import view.PPMImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class ImageIOViewTest {

  @Test
  public void testSave() {
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

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);

    ImageProcessingView v = new ImageIOView();

    v.save("tests/viewTests/9pixels.png", m);

    ImageProcessingModel m2 = new ImageIOProcessingModel("tests/viewTests/9pixels.png");

    String[] channels = new String[6];

    channels[0] = "red";
    channels[1] = "green";
    channels[2] = "blue";
    channels[3] = "luma";
    channels[4] = "value";
    channels[5] = "intensity";

    for (int row = 0; row < m.getImageWidth(); row++ ) {
      for (int col = 0; col < m.getImageWidth(); col++ ) {
        for (String channel: channels) {
          assertEquals(m.getPixelAt(row, col).getChannel(channel),
                  m2.getPixelAt(row, col).getChannel(channel));
        }
      }
    }
  }

  @Test
  public void testSaveEdgeCases() {
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

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);

    ImageProcessingView v = new ImageIOView();

    v.save("tests/viewTests/fgdaklgadklsg", m);

    try {
      ImageProcessingModel m2 =
              new ImageIOProcessingModel("tests/viewTests/fgdaklgadklsg.jpg");
      assertEquals(m2.getMaxValue(), 255);
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testSaveFail() {
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

    ImageProcessingModel m = new EditorImageProcessingModel(pixels, 255);
    try {
      ImageProcessingView v = new ImageIOView();
      v.save(null, m);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }

    try {
      ImageProcessingView v = new ImageIOView();
      v.save(".", m);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid filename.", e.getMessage());
    }

    try {
      ImageProcessingView v = new ImageIOView();
      v.save("tests/viewTests/", m);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid filename.", e.getMessage());
    }

    try {
      ImageProcessingView v = new ImageIOView();
      v.save("tests/viewTests/.ppm", m);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid filename.", e.getMessage());
    }

    try {
      ImageProcessingView v = new ImageIOView();
      v.save(".ppm", m);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid filename.", e.getMessage());
    }
  }

  @Test
  public void testRenderMessage() {
    Appendable output = new StringBuilder();
    ImageProcessingView v = new ImageIOView(output);
    v.renderMessage("hello");
    assertEquals("hello\n", output.toString());
  }

  @Test
  public void testNullRenderMessage() {
    try {
      ImageProcessingView v = new ImageIOView();
      v.renderMessage(null);
      fail();
    } catch (NullPointerException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testRenderMessageIO() {
    try {
      Appendable o = new MockAppendable();
      ImageProcessingView v = new ImageIOView(o);
      v.renderMessage("uh oh!");
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Something went wrong while writing to appendable.", e.getMessage());
    }
  }

}