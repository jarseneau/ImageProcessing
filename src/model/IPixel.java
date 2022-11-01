package model;

import java.util.HashMap;

/**
 * This interface represents a pixel of any kind.
 */
public interface IPixel {
  /**
   * Return the channels of this pixel.
   *
   * @return the channels of this pixel
   * as a hashmap
   */
  HashMap<String, Integer> getChannels();
}
