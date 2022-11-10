package controller;

import java.util.Map;

import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;

public class Load extends AbstractImageCommand {


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