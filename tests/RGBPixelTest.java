import org.junit.Test;

import java.util.HashMap;

import model.IPixel;
import model.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for the RGBPixel class.
 */
public class RGBPixelTest {

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
  public void testConstructPixel() {
    IPixel rgb = new RGBPixel(0, 0, 0, 255);
    assertEquals(0, rgb.getChannel("red"));
    assertEquals(0, rgb.getChannel("green"));
    assertEquals(0, rgb.getChannel("blue"));
    assertEquals(0, rgb.getChannel("value"));
    assertEquals(0, rgb.getChannel("intensity"));
    assertEquals(getLuma(0, 0, 0), rgb.getChannel("luma"));

    IPixel rgb2 = new RGBPixel(255, 255, 255, 255);
    assertEquals(255, rgb2.getChannel("red"));
    assertEquals(255, rgb2.getChannel("green"));
    assertEquals(255, rgb2.getChannel("blue"));
    assertEquals(255, rgb2.getChannel("value"));
    assertEquals(255, rgb2.getChannel("intensity"));
    assertEquals(getLuma(255, 255, 255), rgb2.getChannel("luma"));


    IPixel rgb3 = new RGBPixel(212, 23, 190, 255);
    HashMap<String, Integer> correctChannels3 = new HashMap<>();
    correctChannels3.put("red", 212);
    correctChannels3.put("green", 23);
    correctChannels3.put("blue", 190);
    correctChannels3.put("intensity", 142);
    correctChannels3.put("value", 212);
    correctChannels3.put("luma", 75);
    assertEquals(212, rgb3.getChannel("red"));
    assertEquals(23, rgb3.getChannel("green"));
    assertEquals(190, rgb3.getChannel("blue"));
    assertEquals(142, rgb3.getChannel("intensity"));
    assertEquals(212, rgb3.getChannel("value"));
    assertEquals(getLuma(212, 23, 190), rgb3.getChannel("luma"));
  }

  @Test
  public void testConstructIncorrectPixel() {
    try {
      IPixel rgb = new RGBPixel(-1, 0, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, -1, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 0, -1, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }

    try {
      IPixel rgb = new RGBPixel(257, 0, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 257, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 0, 257, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 0, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid max value or pixel channel values.", e.getMessage());
    }
  }

  @Test
  public void testGetChannels() {
    IPixel rgb = new RGBPixel(133, 245, 79, 255);
    assertEquals(133, rgb.getChannel("red"));
    assertEquals(245, rgb.getChannel("green"));
    assertEquals(79, rgb.getChannel("blue"));
    assertEquals(245, rgb.getChannel("value"));
    assertEquals(152, rgb.getChannel("intensity"));
    assertEquals(getLuma(133, 245, 79), rgb.getChannel("luma"));
  }

  @Test
  public void testModifyChannel() {
    IPixel rgb = new RGBPixel(100, 100, 100, 255);
    IPixel result = rgb.modifyChannel("red", 20);
    assertEquals(120, result.getChannel("red"));
    assertEquals(100, result.getChannel("green"));
    assertEquals(100, result.getChannel("blue"));
    assertEquals(120, result.getChannel("value"));
    assertEquals(107, result.getChannel("intensity"));
    assertEquals(getLuma(120, 100, 100), result.getChannel("luma"));

    IPixel result2 = rgb.modifyChannel("green", 30);
    assertEquals(100, result2.getChannel("red"));
    assertEquals(130, result2.getChannel("green"));
    assertEquals(100, result2.getChannel("blue"));
    assertEquals(130, result2.getChannel("value"));
    assertEquals(110, result2.getChannel("intensity"));
    assertEquals(getLuma(100, 130, 100), result2.getChannel("luma"));

    IPixel result3 = rgb.modifyChannel("blue", 50);
    assertEquals(100, result3.getChannel("red"));
    assertEquals(100, result3.getChannel("green"));
    assertEquals(150, result3.getChannel("blue"));
    assertEquals(150, result3.getChannel("value"));
    assertEquals(117, result3.getChannel("intensity"));
    assertEquals(getLuma(100, 100, 150), result3.getChannel("luma"));

    IPixel result4 = rgb.modifyChannel("red", -20);
    assertEquals(80, result4.getChannel("red"));
    assertEquals(100, result4.getChannel("green"));
    assertEquals(100, result4.getChannel("blue"));
    assertEquals(100, result4.getChannel("value"));
    assertEquals(93, result4.getChannel("intensity"));
    assertEquals(getLuma(80, 100, 100), result4.getChannel("luma"));

    IPixel result5 = rgb.modifyChannel("blue", 400);
    assertEquals(100, result5.getChannel("red"));
    assertEquals(100, result5.getChannel("green"));
    assertEquals(255, result5.getChannel("blue"));
    assertEquals(255, result5.getChannel("value"));
    assertEquals(152, result5.getChannel("intensity"));
    assertEquals(getLuma(100, 100, 255), result5.getChannel("luma"));

    IPixel result6 = rgb.modifyChannel("red", -400);
    assertEquals(0, result6.getChannel("red"));
    assertEquals(100, result6.getChannel("green"));
    assertEquals(100, result6.getChannel("blue"));
    assertEquals(100, result6.getChannel("value"));
    assertEquals(67, result6.getChannel("intensity"));
    assertEquals(getLuma(0, 100, 100), result6.getChannel("luma"));
  }

  @Test
  public void testModifyChannelFail() {
    IPixel rgb = new RGBPixel(100, 100, 100, 255);
    try {
      rgb.modifyChannel("alpha", 10);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("This channel does not exist in this pixel.", e.getMessage());
    }

    try {
      rgb.modifyChannel("luma", 20);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot modify stats about a pixel, only values.", e.getMessage());
    }
  }

  @Test
  public void testModifyAll() {
    IPixel rgb = new RGBPixel(200, 200, 200, 255);
    IPixel result = rgb.modifyAll(20);
    assertEquals(220, result.getChannel("red"));
    assertEquals(220, result.getChannel("green"));
    assertEquals(220, result.getChannel("blue"));
    assertEquals(220, result.getChannel("value"));
    assertEquals(220, result.getChannel("intensity"));
    assertEquals(getLuma(220, 220, 220), result.getChannel("luma"));

    IPixel rgb2 = new RGBPixel(100, 200, 75, 255);
    IPixel result2 = rgb2.modifyAll(20);
    assertEquals(120, result2.getChannel("red"));
    assertEquals(220, result2.getChannel("green"));
    assertEquals(95, result2.getChannel("blue"));
    assertEquals(220, result2.getChannel("value"));
    assertEquals(145, result2.getChannel("intensity"));
    assertEquals(getLuma(120, 220, 95), result2.getChannel("luma"));

    IPixel result3 = rgb2.modifyAll(70);
    assertEquals(170, result3.getChannel("red"));
    assertEquals(255, result3.getChannel("green"));
    assertEquals(145, result3.getChannel("blue"));
    assertEquals(255, result3.getChannel("value"));
    assertEquals(190, result3.getChannel("intensity"));
    assertEquals(getLuma(170, 255, 145), result3.getChannel("luma"));

    IPixel result4 = rgb2.modifyAll(-80);
    assertEquals(20, result4.getChannel("red"));
    assertEquals(120, result4.getChannel("green"));
    assertEquals(0, result4.getChannel("blue"));
    assertEquals(120, result4.getChannel("value"));
    assertEquals(47, result4.getChannel("intensity"));
    assertEquals(getLuma(20, 120, 0), result4.getChannel("luma"));
  }
}