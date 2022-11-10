package controller;

import java.util.Map;

import model.ImageProcessingModel;

public abstract class AbstractImageCommand implements ImageCommand {

  String name1;
  String name2;
  Map<String, ImageProcessingModel> images;
  ConsoleController c;

  AbstractImageCommand(String name1, String name2, Map<String, ImageProcessingModel> images,
                       ConsoleController c) {
    this.name1 = name1;
    this.name2 = name2;
    this.images = images;
    this.c = c;
  }
  @Override
  public abstract void go();
}
