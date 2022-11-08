package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import model.ImageProcessingModel;

/**
 * Represents a PPM image view and supports saving images as .ppm files.
 */
public class PPMImageView implements ImageProcessingView {

  Appendable output;

  /**
   * Constructs a new PPMImageView from a given output.
   *
   * @param output the appendable where the view will render messages to
   * @throws NullPointerException if the output given is null
   */
  public PPMImageView(Appendable output) throws NullPointerException {
    Objects.requireNonNull(output);
    this.output = output;
  }

  /**
   * Constructs a new PPMImageView with its default output
   * being System.out.
   */
  public PPMImageView() {
    this(System.out);
  }

  /**
   * Converts a given model to a PPM text format.
   *
   * @param model the model which should be converted
   * @return the PPM text as a string
   * @throws IllegalArgumentException if the pixels in the model do not have the correct
   *                                  channel
   */
  private String ppmToString(ImageProcessingModel model) throws IllegalArgumentException {
    StringBuilder builder = new StringBuilder();
    builder.append("P3" + System.lineSeparator());
    builder.append(model.getImageWidth() + " " + model.getImageHeight() +
            System.lineSeparator());
    builder.append(model.getMaxValue() +
            System.lineSeparator());
    try {
      for (int row = 0; row < model.getImageHeight(); row++) {
        for (int col = 0; col < model.getImageWidth(); col++) {
          builder.append(model.getPixelAt(row, col).getChannel("red") +
                  System.lineSeparator());
          builder.append(model.getPixelAt(row, col).getChannel("green") +
                  System.lineSeparator());
          builder.append(model.getPixelAt(row, col).getChannel("blue") +
                  System.lineSeparator());
        }
      }
      return builder.toString();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
              "You cannot save to a PPM file if your pixels are not RGB!");
    }
  }

  /**
   * Saves the model to a PPM file.
   *
   * @param filename the name of the destination file
   * @param model the model that is to be saved
   * @throws IllegalStateException if writing to the file fails
   */
  @Override
  public void save(String filename, ImageProcessingModel model) throws IllegalStateException {
    Objects.requireNonNull(filename);
    try {
      if (filename.length() < 3
              || !"ppm".equals(filename.substring(filename.length() - 3))) {
        filename = filename + ".ppm";
      }
      if (filename.equals(".ppm") ||
              filename.charAt(filename.lastIndexOf('/') + 1)  == '.') {
        throw new IllegalArgumentException("Invalid filename.");
      }
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(this.ppmToString(model));
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong writing to the file.");
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
