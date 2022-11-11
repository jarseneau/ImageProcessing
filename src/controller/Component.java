package controller;

import java.util.Map;

import model.GrayscaleAdjustor;
import model.ImageProcessingModel;

/**
 * A Command that uses a GrayscaleAdjustor to take just one component of
 * the given image and store it in the images map with the new name.
 */
public class Component extends AbstractImageCommand {

  private String component;

  /**
   * Constructor for Component, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   * @param component the String component to grayscale by (eg. "red")
   */
  Component(String name1, String name2, Map<String, ImageProcessingModel> images,
            ConsoleController c, String component) {
    super(name1, name2, images, c);
    this.component = component;
  }

  @Override
  public void execute() {
    try {
      c.writeMessage("Gray-scaling " + name1 + " and storing as: " + name2);
      //put new model into the images map.
      images.put(name2, images.get(name1).apply(new GrayscaleAdjustor(this.component)));
    }
    catch (NullPointerException e) {
      c.writeMessage("Image " + name1 + " not yet loaded");
    }
    catch (IllegalArgumentException e) {
      c.writeMessage("Error: " + e.getMessage());
    }
  }
}
