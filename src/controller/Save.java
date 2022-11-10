package controller;

import java.util.Map;

import model.ImageProcessingModel;

/**
 * A command object that supports the saving of an image from the controller.
 */
public class Save extends AbstractImageCommand {

  /**
   * Constructor for HFlip, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  public Save(String name1, String name2, Map<String, ImageProcessingModel> images,
              ConsoleController c) {
    super(name1, name2, images, c);
  }
  @Override
  public void go() {
    try {
      c.writeMessage("Saving " + name2 + " to: " + name1);
      c.trySave(name1, name2);

    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + "not yet loaded");
    }
  }
}
