package model;

public abstract class AImageFromFileProcessing implements ImageProcessingModel {

  private final EditorImageProcessingModel editor;

  public AImageFromFileProcessing(String fileText) {
    editor = new EditorImageProcessingModel(this.loadFromFile(fileText),
            this.getMaxFromFile(fileText));
  }

  /**
   * Reads the text from a file and converts it into a 2D array of pixels.
   *
   * @param fileText the text from the file
   * @return the image represented by a 2D array of pixels
   * @throws IllegalArgumentException if the format of the file is not correct
   */
  abstract protected IPixel[][] loadFromFile(String fileText) throws IllegalArgumentException;

  /**
   * Reads the text from a file and finds the max value of the pixels inside the image.
   *
   * @param fileText the text from the file
   * @return the max value of all pixels
   * @throws IllegalArgumentException if the format of the file is not correct
   */
  abstract protected int getMaxFromFile(String fileText) throws IllegalArgumentException;


  @Override
  public int getImageWidth() {
    return editor.getImageWidth();
  }

  @Override
  public int getImageHeight() {
    return editor.getImageHeight();
  }

  @Override
  public int getMaxValue() {
    return editor.getMaxValue();
  }

  @Override
  public IPixel getPixelAt(int row, int col) throws IllegalArgumentException {
    return editor.getPixelAt(row, col);
  }

  @Override
  public ImageProcessingModel apply(IImageAdjustor adjustor) {
    return editor.apply(adjustor);
  }
}
