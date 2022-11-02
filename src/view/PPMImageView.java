package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import model.ImageProcessingModel;

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
   */
  private String ppmToString(ImageProcessingModel model) {
    StringBuilder builder = new StringBuilder();
    builder.append("P3\n");
    builder.append(model.getImageWidth() + " " + model.getImageHeight() + "\n");
    builder.append(model.getMaxValue() + "\n");
    for (int row = 0; row < model.getImageHeight(); row++ ) {
      for (int col = 0; col < model.getImageWidth(); col++ ) {
        builder.append(model.getPixelAt(row, col).getChannel("red") + "\n");
        builder.append(model.getPixelAt(row, col).getChannel("green") + "\n");
        builder.append(model.getPixelAt(row, col).getChannel("blue") + "\n");
      }
    }
    return builder.toString();
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
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(this.ppmToString(model));
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong writing to the file.");
    }
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong while writing to appendable.");
    }
  }
}
