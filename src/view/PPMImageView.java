package view;

import java.io.FileWriter;
import java.io.IOException;

import model.ImageProcessingModel;

public class PPMImageView implements ImageProcessingView {

  ImageProcessingModel model;

  public PPMImageView(ImageProcessingModel m) {
    this.model = m;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("P3\n");
    builder.append(this.model.getImageWidth() + " " + this.model.getImageHeight() + "\n");
    builder.append(this.model.getMaxValue() + "\n");
    for (int row = 0; row < this.model.getImageHeight(); row++ ) {
      for (int col = 0; col < this.model.getImageWidth(); col++ ) {
        builder.append(this.model.getPixelAt(row, col).getChannel("red") + "\n");
        builder.append(this.model.getPixelAt(row, col).getChannel("green") + "\n");
        builder.append(this.model.getPixelAt(row, col).getChannel("blue") + "\n");
      }
    }
    return builder.toString();
  }

  /**
   * Saves the model to a PPM file.
   *
   * @param filename the name of the destination file
   */
  @Override
  public void save(String filename) {
    try {
      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(this.toString());
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong writing to the file.");
    }
  }
}
