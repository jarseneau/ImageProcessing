package controller;

import java.util.Map;

import model.ImageProcessingModel;

/**
 * An abstract class to represent an image command. Cleans up construction
 * because all image commands take in the same 4 things.
 */
public abstract class AbstractImageCommand implements ImageCommand {

  protected String name1;
  protected String name2;
  protected Map<String, ImageProcessingModel> images;
  protected ImageProcessingController c;

  /**
   * Constructor for AbstractImageCommand, handles field setting for the command.
   * @param name1 the first string value for this command
   * @param name2 the second string value for this command
   * @param images the map of images that the program is running on
   * @param c the controller to send information back to.
   */
  AbstractImageCommand(String name1, String name2, Map<String, ImageProcessingModel> images,
                       ImageProcessingController c) {
    this.name1 = name1;
    this.name2 = name2;
    this.images = images;
    this.c = c;
  }

  @Override
  public abstract void execute();
}
