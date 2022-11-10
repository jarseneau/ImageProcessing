package controller;

import java.util.InputMismatchException;
import java.util.Map;

import model.BrightenAdjustor;
import model.ImageProcessingModel;

public class Brighten extends AbstractImageCommand {
  int increment;

  Brighten(int increment, String name1, String name2, Map<String, ImageProcessingModel> images,
           ConsoleController c) {
    super(name1, name2, images, c);
    this.increment = increment;

  }

  @Override
  public void go() {
    try {
      c.writeMessage("Brightening " + name1 + " by "
              + increment + " and storing as: " + name2);
      images.put(name2, images.get(name1).apply(new BrightenAdjustor(increment)));
    } catch (NullPointerException e) {
      c.writeMessage("Error: image " + name1 + " not yet loaded");
    }
  }
}
