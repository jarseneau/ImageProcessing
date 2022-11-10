package controller;

import java.util.Map;

import model.FlipVerticalAdjustor;
import model.ImageProcessingModel;

public class VFlip extends AbstractImageCommand {
  VFlip(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void go() {
    try {
      c.writeMessage("Flipping " + name1 + " vertically and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new FlipVerticalAdjustor()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
