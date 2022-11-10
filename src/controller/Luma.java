package controller;

import java.util.Map;

import model.FlipVerticalAdjustor;
import model.GrayscaleLumaTransformation;
import model.ImageProcessingModel;

/**
 * A Command that uses a GrayscaleLumaTransformation to filter the given image and
 * store it in the images map with the new name.
 */
public class Luma extends AbstractImageCommand {

  /**
   * Constructor for Sepia, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  Luma(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void go() {
    try {
      c.writeMessage("Flipping " + name1 + " vertically and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new GrayscaleLumaTransformation()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
