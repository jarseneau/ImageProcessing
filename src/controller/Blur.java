package controller;

import java.util.Map;

import model.BlurFilteringAdjustor;
import model.FlipVerticalAdjustor;
import model.ImageProcessingModel;

public class Blur extends AbstractImageCommand {
  Blur(String name1, String name2, Map<String, ImageProcessingModel> images, ConsoleController c) {
    super(name1, name2, images, c);
  }

  @Override
  public void go() {
    try {
      c.writeMessage("Blurring " + name1 + "and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new BlurFilteringAdjustor()));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
