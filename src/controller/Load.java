package controller;

import java.util.Map;

import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;

/**
 * A Command that Loads in an image with a given filepath and
 * stores it in the program with its given name.
 */
public class Load extends AbstractImageCommand {


  /**
   * Constructor for HFlip, handles field setting.
   *
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  public Load(String name1, String name2, Map<String, ImageProcessingModel> images,
              ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void go() {
    try {
      ImageProcessingModel model;
      c.writeMessage("Loading image at " + name1 + " as " + name2);
      if (name1.substring(name1.lastIndexOf(".") + 1).equals("ppm")) {
        model = new PPMProcessingModel(name1);
      }
      else {
        model = new ImageIOProcessingModel(name1);
      }
      images.put(name2, model);
    } catch (IllegalArgumentException e) {
      c.writeMessage("Error: " + e.getMessage());
    }
  }
}