package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;

public class ImageIOView implements ImageProcessingView {
  Appendable output;

  public ImageIOView() {
    this(System.out);
  }

  public ImageIOView(Appendable output) {
    Objects.requireNonNull(output);
    this.output = output;
  }

  @Override
  public void save(String filename, ImageProcessingModel model) throws IllegalStateException, NullPointerException {

    // delegate to ppmView for ppms, otherwise use ImageIO
    if (this.getFileType(filename).equals("ppm")) {
      new PPMImageView(output).save(filename, model);
    }

    else {
      BufferedImage image = new BufferedImage(model.getImageWidth(), model.getImageHeight(),
              BufferedImage.TYPE_INT_RGB);
      Color c;
      for (int row = 0; row < model.getImageHeight(); row++) {
        for (int col = 0; col < model.getImageWidth(); col++) {
          c = new Color(model.getPixelAt(row, col).getChannel("red"),
                  model.getPixelAt(row, col).getChannel("green"),
                  model.getPixelAt(row, col).getChannel("blue"));
          image.setRGB(col, row, c.getRGB());
        }
      }

      try {
        ImageIO.write(image, this.getFileType(filename), new File(filename));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private String getFileType(String filename) {
    String type = filename.substring(filename.lastIndexOf(".") + 1);
    if (type.equals("png") || type.equals("bpm") || type.equals("ppm")) {
      return type;
    }
    else {
      return "jpg";
    }
  }

  @Override
  public void renderMessage(String message) throws NullPointerException {
    Objects.requireNonNull(message);
    try {
      this.output.append(message + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong while writing to appendable.");
    }
  }
}
