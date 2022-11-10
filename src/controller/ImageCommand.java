package controller;

/**
 * An interface to represent an operation that can be done onto an image model.
 */
public interface ImageCommand {

  /**
   * A method which runs this command.
   */
  void go();
}
