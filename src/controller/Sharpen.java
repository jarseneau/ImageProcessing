package controller;

import java.util.Map;

import model.FlipVerticalAdjustor;
import model.ImageProcessingModel;
import model.SharpenFilteringAdjustor;

/**
 * A Command that uses a SharpenFilteringAdjustor to sharpen the given image and
 * store it in the images map with the new name.
 */
public class Sharpen extends AbstractImageCommand {

  /**
   * Constructor for Sharpen, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  Sharpen(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void go() {
    try {
      c.writeMessage("Sharpening " + name1 + "and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new SharpenFilteringAdjustor()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
