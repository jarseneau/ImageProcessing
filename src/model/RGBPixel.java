package model;

import java.util.HashMap;

/**
 * This class represents a pixel with 3 channels, one for red
 * one for green, and one for blue.
 */
public class RGBPixel implements IPixel {

  private final HashMap<String, Integer> channels;

  /**
   * Constructs a new RGBPixel and places red, green, and blue values into
   * a hashmap formatted as String -> Integer.
   *
   * <p>Example: {"red": 255, "green": 255, "blue": 255}</p>
   *
   * @param red the value of the red channel
   * @param green the value of the green channel
   * @param blue the value of the blue channel
   * @throws IllegalArgumentException if the red, green, or blue values are invalid
   */
  public RGBPixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0
            || red > maxValue || green > maxValue || blue > maxValue) {
      throw new IllegalArgumentException("Invalid pixel channel values.");
    }
    this.channels = new HashMap<>();
    this.channels.put("red", red);
    this.channels.put("green", green);
    this.channels.put("blue", blue);
  }

  @Override
  public HashMap<String, Integer> getChannels() {
    return new HashMap<>(this.channels);
  }
}
