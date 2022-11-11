package controller;

import java.util.Map;

import model.ImageProcessingModel;
import model.SepiaTransformation;

/**
 * A Command that uses a SepiaTransformation to filter the given image and
 * store it in the images map with the new name.
 */
public class Sepia extends AbstractImageCommand {

  /**
   * Constructor for Sepia, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  Sepia(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void execute() {
    try {
      c.writeMessage("Sepia filtering " + name1 + " and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new SepiaTransformation()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
