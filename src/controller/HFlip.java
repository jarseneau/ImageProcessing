package controller;

import java.util.Map;

import model.FlipHorizontalAdjustor;
import model.ImageProcessingModel;

/**
 * A Command that uses a FlipHorizontalAdjustor to flip the given image and
 * store it in the images map with the new name.
 */
public class HFlip extends AbstractImageCommand {

  /**
   * Constructor for HFlip, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  HFlip(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void execute() {
    try {
      c.writeMessage("Flipping " + name1 + " horizontally and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new FlipHorizontalAdjustor()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
