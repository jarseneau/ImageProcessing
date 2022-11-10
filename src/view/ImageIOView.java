package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.ImageProcessingModel;

/**
 * A view to represent an image that is not in ppm format.
 */
public class ImageIOView implements ImageProcessingView {
  Appendable output;

  /**
   * Constructs a new ImageIOView with the default output as
   * System.out.
   */
  public ImageIOView() {
    this(System.out);
  }

  /**
   * Constructs a new ImageIOView with the output as
   * the controller specifies.
   * @param output the appendable to be written to
   * @throws NullPointerException if the appendable provided is null
   */
  public ImageIOView(Appendable output) throws NullPointerException {
    Objects.requireNonNull(output);
    this.output = output;
  }

  @Override
  public void save(String filename, ImageProcessingModel model)
          throws IllegalStateException, NullPointerException {

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
        ImageIO.write(image, this.getFileType(filename), new File(this.fixName(filename)));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * Renames the given file if user has inputted an invalid filename that cannot
   * be saved to an OS.
   * @param filename the name of the file that the user inputted
   * @return the modified filename in correct format
   */
  private String fixName(String filename) {
    if (filename.length() < 3
            || !this.getFileType(filename).equals(filename.substring(filename.length() - 3))) {
      filename = filename + ".jpg";
    }
    if (filename.equals(this.getFileType(filename)) ||
            filename.charAt(filename.lastIndexOf('/') + 1)  == '.') {
      throw new IllegalArgumentException("Invalid filename.");
    }
    return filename;
  }

  /**
   * Gets the file type inputted by the user, defaults to jpg if not specified.
   * @param filename the name of the file inputted by the user
   * @return the file type to be saved as
   */
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
