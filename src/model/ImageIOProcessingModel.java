package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageIOProcessingModel extends AImageFromFileProcessing {

  private BufferedImage image;

  public ImageIOProcessingModel(String fileName) {
    super(fileName);
  }

  @Override
  protected IPixel[][] loadFromFile(String fileName) throws IllegalArgumentException {

    try {
      File inputFile = new File(fileName);
      image = ImageIO.read(inputFile);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    IPixel[][] pixels = new IPixel[image.getHeight()][image.getWidth()];
    Raster data = image.getData();

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        pixels[row][col] = new RGBPixel(
                data.getSample(col,row,0),
                data.getSample(col,row,1),
                data.getSample(col,row,2),
                255);
      }
    }

    return pixels;
  }

  @Override
  protected int getMaxFromFile(String fileName) throws IllegalArgumentException {
    return 255;
  }


}
