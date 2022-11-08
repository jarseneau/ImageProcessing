package model;

import java.util.HashMap;
import java.util.Objects;

public class AlphaPixel implements IPixel {
  protected final int maxValue;
  protected final HashMap<String, Integer> channels;

  /**
   * Constructs a new AlphaPixel and places red, green, blue, and alpha values into
   * a hashmap formatted as String -> Integer.
   *
   * <p>Example: {"red": 255, "green": 255, "blue": 255, "alpha": 255}</p>
   *
   * @param red the value of the red channel
   * @param green the value of the green channel
   * @param blue the value of the blue channel
   * @param alpha the value of the alpha channel
   * @throws IllegalArgumentException if the red, green, or blue values are invalid
   */
  public AlphaPixel(int red, int green, int blue, int alpha, int maxValue) throws IllegalArgumentException {
    if (maxValue <= 0
            || red < 0 || green < 0 || blue < 0 || alpha < 0
            || red > maxValue || green > maxValue || blue > maxValue || alpha > maxValue) {
      throw new IllegalArgumentException("Invalid max value or pixel channel values.");
    }
    this.maxValue = maxValue;
    this.channels = new HashMap<>();
    this.channels.put("red", red);
    this.channels.put("green", green);
    this.channels.put("blue", blue);
    this.channels.put("alpha", alpha);
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
  public String[] getMainChannels() {
    return new String[]{"red", "green", "blue", "alpha"};
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
    return new AlphaPixel(modified.get("red"),
            modified.get("green"),
            modified.get("blue"),
            modified.get("alpha"),
            this.maxValue);
  }

  @Override
  public IPixel addAll(int val) {
    return this.modifyChannel("red", val)
            .modifyChannel("green", val)
            .modifyChannel("blue", val)
            .modifyChannel("alpha", val);
  }
}
