package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * To represent an image processing model obtained from a PPM file.
 */
public class PPMProcessingModel extends AImageFromFileProcessing {

  /**
   * Constructs a new PPMProcessingModel from the text of a PPM file.
   *
   * @param fileName the name of the ppm file passed from the controller
   * @throws NullPointerException if the filename is null
   */
  public PPMProcessingModel(String fileName) {
    super(fileName);
  }

  /**
   * Reads from the file and returns the raw text of the contents of the file.
   *
   * @param fileName the name of the file to be read from
   * @return the raw text of the file as a String
   * @throws IllegalArgumentException if the provided filename cannot be found
   */
  private String readFromFile(String fileName) throws IllegalArgumentException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileName));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + fileName + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder.toString();
  }

  @Override
  protected IPixel[][] loadFromFile(String fileName) throws IllegalArgumentException {
    Scanner sc = new Scanner(this.readFromFile(fileName));

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
  protected int getMaxFromFile(String fileName) {
    Scanner sc = new Scanner(this.readFromFile(fileName));

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
