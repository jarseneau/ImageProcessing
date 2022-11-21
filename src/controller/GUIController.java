package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import view.ImageIOView;
import view.ImageProcessingRenderedView;
import view.ImageProcessingView;

public class GUIController implements ImageProcessingFeatures {

  private ImageProcessingModel model;
  private final ImageProcessingRenderedView view;
  private final Map<String, Function<Scanner, ImageCommand>> knownCommands;

  public GUIController(ImageProcessingModel m, ImageProcessingRenderedView v) {
    this.model = m;
    this.view = v;
    this.knownCommands = new HashMap<>();
    configureCommands();
    configureButtonListener();
    v.addFeatures(this);
  }

  private void configureCommands() {
    Set<String> keys = knownCommands.keySet();
    this.view.addCommands(keys.toArray(new String[0]));
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Open file", new LoadButtonAction());
    buttonClickedMap.put("Save file", new SaveButtonAction());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  class LoadButtonAction implements Runnable {
    public void run() {
      String path = view.loadFile();
      if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
        model = new PPMProcessingModel(path);
      }
      else {
        model = new ImageIOProcessingModel(path);
      }
      view.updateImage(model);
    }
  }

  class SaveButtonAction implements Runnable {
    public void run() {
      String path = view.saveFile();
      ImageProcessingView saveView = new ImageIOView();
      saveView.save(path, model);
    }
  }
}
