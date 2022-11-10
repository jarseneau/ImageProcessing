package controller;

import java.util.Map;

import model.FlipHorizontalAdjustor;
import model.ImageProcessingModel;

public class HFlip extends AbstractImageCommand {
  HFlip(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void go() {
    try {
      c.writeMessage("Flipping " + name1 + " horizontally and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new FlipHorizontalAdjustor()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
