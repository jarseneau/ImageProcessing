package model;

/**
 * This class represents a pixel with 3 channels, one for red
 * one for green, and one for blue.
 */
public class RGBPixel extends AlphaPixel {
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
    super(red, green, blue, 255, maxValue);
  }

  @Override
  public String[] getMainChannels() {
    return new String[]{"red", "green", "blue"};
  }

  @Override
  public IPixel addAll(int val) {
    return this.modifyChannel("red", val)
            .modifyChannel("green", val)
            .modifyChannel("blue", val);
  }
}
