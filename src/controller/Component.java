package controller;

import java.util.Map;

import model.GrayscaleAdjustor;
import model.ImageProcessingModel;

public class Component extends AbstractImageCommand {

  String component;
  Component(String name1, String name2, Map<String, ImageProcessingModel> images,
            ConsoleController c, String component) {
    super(name1, name2, images, c);
    this.component = component;
  }

  @Override
  public void go() {
    try {
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
