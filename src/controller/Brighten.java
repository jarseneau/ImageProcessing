package controller;

import java.util.Map;

import model.BrightenAdjustor;
import model.ImageProcessingModel;

/**
 * A Command that uses a BrightenAdjustor to filter the given image and
 * store it in the images map with the new name.
 */
public class Brighten extends AbstractImageCommand {
  int increment;

  /**
   * Constructor for Brighten, handles field setting.
   *
   * @param increment the amount by which the image is to be brightened.
   * @param name1 the first string value for this command.
   * @param name2 the second string value for this command.
   * @param images the map of images that the program is running on.
   * @param c the controller to send information back to.
   */
  Brighten(int increment, String name1, String name2, Map<String, ImageProcessingModel> images,
           ConsoleController c) {
    super(name1, name2, images, c);
    this.increment = increment;

  }

  @Override
  public void go() {
    try {
      c.writeMessage("Brightening " + name1 + " by "
              + increment + " and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new BrightenAdjustor(increment)));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
