package model;

/**
 * This interface represents a pixel of any kind.
 */
public interface IPixel {
  /**
   * Return the value of the specified channel.
   *
   * @return the value of the specified channel
   * @throws IllegalArgumentException if the channel does not exist
   */
  int getChannel(String channel);

  /**
   * Returns a new pixel with the specified value added to the
   * given pixel channel.
   *
   * @param channelName the name of the channel to be changed
   * @param val the name of the pixel to be changed
   * @return the new pixel resulting from the modification
   * @throws IllegalArgumentException if the given name is not a channel on this pixel
   *                                  or a user is trying to edit statistic about a pixel
   *                                  and not channels
   */
  IPixel modifyChannel(String channelName, int val) throws IllegalArgumentException;

  /**
   * Returns a list of the channels of a pixel that are not statistics.
   *
   * @return the main channels of the pixel as an array of strings
   */
  String[] getMainChannels();

  /**
   * Returns a new pixel with the specified value added to every
   * pixel channel that isn't a statistic.
   *
   * @param val the value to be added to all channels
   * @return the new pixel resulting from the modification
   */
  IPixel addAll(int val);
}
