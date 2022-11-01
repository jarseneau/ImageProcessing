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

  @Test
  public void testConstructPixel() {
    IPixel rgb = new RGBPixel(0, 0, 0, 255);
    HashMap<String, Integer> correctChannels = new HashMap<>();
    correctChannels.put("red", 0);
    correctChannels.put("green", 0);
    correctChannels.put("blue", 0);
    assertEquals(correctChannels, rgb.getChannels());

    IPixel rgb2 = new RGBPixel(255, 255, 255, 255);
    HashMap<String, Integer> correctChannels2 = new HashMap<>();
    correctChannels2.put("red", 255);
    correctChannels2.put("green", 255);
    correctChannels2.put("blue", 255);
    assertEquals(correctChannels2, rgb2.getChannels());
  }

  @Test
  public void testConstructIncorrectPixel() {
    try {
      IPixel rgb = new RGBPixel(-1, 0, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, -1, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 0, -1, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel channel values.", e.getMessage());
    }

    try {
      IPixel rgb = new RGBPixel(257, 0, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 257, 0, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel channel values.", e.getMessage());
    }
    try {
      IPixel rgb = new RGBPixel(0, 0, 257, 255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid pixel channel values.", e.getMessage());
    }
  }

  @Test
  public void testGetChannels() {
    IPixel rgb = new RGBPixel(133, 245, 79, 255);
    HashMap<String, Integer> channels = rgb.getChannels();
    fail();

  }
}