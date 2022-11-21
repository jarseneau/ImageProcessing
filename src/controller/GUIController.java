package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.BlurFilteringAdjustor;
import model.BrightenAdjustor;
import model.FlipHorizontalAdjustor;
import model.FlipVerticalAdjustor;
import model.GrayscaleAdjustor;
import model.ImageIOProcessingModel;
import model.ImageProcessingModel;
import model.PPMProcessingModel;
import model.SepiaTransformation;
import model.SharpenFilteringAdjustor;
import view.ImageIOView;
import view.ImageProcessingRenderedView;
import view.ImageProcessingView;

public class GUIController implements ImageProcessingFeatures {

  private ImageProcessingModel model;
  private final ImageProcessingRenderedView view;

  public GUIController(ImageProcessingModel m, ImageProcessingRenderedView v) {
    this.model = m;
    this.view = v;
    configureButtonListener();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Open file", new LoadButtonAction());
    buttonClickedMap.put("Save file", new SaveButtonAction());
    buttonClickedMap.put("Close", new CloseAction());

    Map<String, Runnable> filterButtonClickedMap = new HashMap<>();

    filterButtonClickedMap.put("Blur", new BlurAction());
    filterButtonClickedMap.put("Sharpen", new SharpenAction());
    filterButtonClickedMap.put("Horizontal Flip", new HorizontalFlipAction());
    filterButtonClickedMap.put("Vertical Flip", new VerticalFlipAction());
    filterButtonClickedMap.put("Brighten", new BrightenAction());
    filterButtonClickedMap.put("Grayscale", new GrayscaleAction());
    filterButtonClickedMap.put("Sepia", new SepiaAction());

    Set<String> keys = filterButtonClickedMap.keySet();
    view.addCommands(keys.toArray(new String[0]));

    buttonClickedMap.putAll(filterButtonClickedMap);

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  class LoadButtonAction implements Runnable {
    public void run() {
      try {
        String path = view.loadFile();
        if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
          model = new PPMProcessingModel(path);
        } else {
          model = new ImageIOProcessingModel(path);
        }
        view.updateImage(model);
      } catch (IllegalStateException e) {
        // do nothing
      }
    }
  }

  class SaveButtonAction implements Runnable {
    public void run() {
      try {
        String path = view.saveFile();
        ImageProcessingView saveView = new ImageIOView();
        saveView.save(path, model);
      } catch (IllegalStateException e) {
        // do nothing
      }
    }
  }

  class BlurAction implements Runnable {
    public void run() {
      model = model.apply(new BlurFilteringAdjustor());
      view.updateImage(model);
    }
  }

  class SharpenAction implements Runnable {
    public void run() {
      model = model.apply(new SharpenFilteringAdjustor());
      view.updateImage(model);
    }
  }

  class HorizontalFlipAction implements Runnable {
    public void run() {
      model = model.apply(new FlipHorizontalAdjustor());
      view.updateImage(model);
    }
  }

  class VerticalFlipAction implements Runnable {
    public void run() {
      model = model.apply(new FlipVerticalAdjustor());
      view.updateImage(model);
    }
  }

  class BrightenAction implements Runnable {
    public void run() {
      try {
        int scalar = Integer.parseInt(
                view.queryUser("How much would you like to brighten by? " +
                        "(Negative values will darken the image)"));
        model = model.apply(new BrightenAdjustor(scalar));
        view.updateImage(model);
      } catch (NumberFormatException e) {
        view.sendError("Scalar must be an integer.");
      }
    }
  }

  class GrayscaleAction implements Runnable {
    public void run() {
      try {
        String component = view.queryUser("What component would you like to grayscale by?");
        model = model.apply(new GrayscaleAdjustor(component));
        view.updateImage(model);
      } catch (IllegalArgumentException e) {
        view.sendError(e.getMessage());
      }
    }
  }

  class SepiaAction implements Runnable {
    public void run() {
      model = model.apply(new SepiaTransformation());
      view.updateImage(model);
    }
  }

  static class CloseAction implements Runnable {
    public void run() {
      System.exit(0);
    }
  }
}
