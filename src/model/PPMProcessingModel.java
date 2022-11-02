package model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * To represent an image processing model obtained from a PPM file.
 */
public class PPMProcessingModel extends AImageFromFileProcessing {

  /**
   * Constructs a new PPMProcessingModel from the text of a PPM file
   *
   * @param fileText the text of the PPM file passed from the controller
   * @throws NullPointerException if the filename is null
   */
  public PPMProcessingModel(String fileText) {
    super(fileText);
  }

  @Override
  protected IPixel[][] loadFromFile(String fileText) throws IllegalArgumentException {
    Scanner sc = new Scanner(fileText);

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    try {
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxValue = sc.nextInt();

      IPixel[][] image = new IPixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          image[i][j] = new RGBPixel(r, g, b, maxValue);
        }
      }
      return image;
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Invalid PPM file:" +
              " there is text where there shouldn't be.");
    }
  }

  @Override
  protected int getMaxFromFile(String fileText) {
    Scanner sc = new Scanner(fileText);

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    try {
      sc.nextInt();
      sc.nextInt();
      return sc.nextInt();
    } catch (InputMismatchException e) {
      throw new IllegalArgumentException("Invalid PPM file:" +
              " there is text where there shouldn't be.");
    }
  }


}
