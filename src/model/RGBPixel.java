package model;

import java.util.HashMap;
import java.util.Objects;

/**
 * This class represents a pixel with 3 channels, one for red
 * one for green, and one for blue.
 */
public class RGBPixel implements IPixel {

  private final int maxValue;
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
    if (maxValue <= 0
            ||red < 0 || green < 0 || blue < 0
            || red > maxValue || green > maxValue || blue > maxValue) {
      throw new IllegalArgumentException("Invalid max value or pixel channel values.");
    }
    this.maxValue = maxValue;
    this.channels = new HashMap<>();
    this.channels.put("red", red);
    this.channels.put("green", green);
    this.channels.put("blue", blue);
    this.channels.put("value", Math.max(Math.max(red, green), blue));
    this.channels.put("intensity", (int) Math.round((double) (red + green + blue) / 3));
    this.channels.put("luma", (int) Math.round((0.2126 * red + 0.7152 * green + 0.0722 * blue)));
  }

  @Override
  public int getChannel(String channel) throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(channel);
    if (!this.channels.containsKey(channel)) {
      throw new IllegalArgumentException("Channel specified does not exist in this pixel.");
    }
    return this.channels.get(channel);
  }

  @Override
  public IPixel modifyChannel(String channelName, int val)
          throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(channelName);
    if (!this.channels.containsKey(channelName)) {
      throw new IllegalArgumentException("This channel does not exist in this pixel.");
    } else if (channelName.equals("value")
            || channelName.equals("intensity")
            || channelName.equals("luma")) {
      throw new IllegalArgumentException("Cannot modify stats about a pixel, only values.");
    }
    HashMap<String, Integer> modified = new HashMap<>(this.channels);
    int newVal = Math.max(Math.min(this.channels.get(channelName) + val, this.maxValue), 0);
    modified.put(channelName, newVal);
    return new RGBPixel(modified.get("red"),
            modified.get("green"),
            modified.get("blue"),
            this.maxValue);
  }

  @Override
  public IPixel modifyAll(int val) {
    return this.modifyChannel("red", val)
            .modifyChannel("green", val)
            .modifyChannel("blue", val);
  }
}
