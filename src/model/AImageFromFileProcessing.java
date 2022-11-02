package model;

/**
 * To represent any image that is loaded directly from a file.
 */
public abstract class AImageFromFileProcessing implements ImageProcessingModel {

  private final EditorImageProcessingModel editor;

  /**
   * Constructs a new ImageProcessor from a given filename.
   *
   * @param fileName the name of the file that should be loaded
   */
  public AImageFromFileProcessing(String fileName) {
    editor = new EditorImageProcessingModel(this.loadFromFile(fileName),
            this.getMaxFromFile(fileName));
  }

  /**
   * Reads the text from a given file name and converts it into a 2D array of pixels.
   *
   * @param fileName the name of the file being loaded
   * @return the image represented by a 2D array of pixels
   * @throws IllegalArgumentException if the format of the file is not correct
   */
  abstract protected IPixel[][] loadFromFile(String fileName) throws IllegalArgumentException;

  /**
   * Reads the text from a given file name and finds the max value of the pixels inside the image.
   *
   * @param fileName the name of the file being loaded
   * @return the max value of all pixels
   * @throws IllegalArgumentException if the format of the file is not correct
   */
  abstract protected int getMaxFromFile(String fileName) throws IllegalArgumentException;


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
