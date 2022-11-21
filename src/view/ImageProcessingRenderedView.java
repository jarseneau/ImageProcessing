package view;

import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;

import controller.GUIController;
import controller.ImageProcessingFeatures;
import model.ImageProcessingModel;

public interface ImageProcessingRenderedView extends ImageProcessingView {
  /**
   * Adds the list of commands provided to the list of options.
   */
  void addCommands(String[] commands);

  /**
   * Adds action listener to the view for the buttons.
   * @param listener the listener to be added
   */
  void addActionListener(ActionListener listener);

  /**
   * Adds list listener to the view for the selection list.
   * @param listener the listener to be added
   */
  void addListListener(ListSelectionListener listener);

  /**
   * Presents the user with the load file dialog and
   * returns the absolute path to the file the user selected.
   *
   * @return the filepath that the user specified
   */
  String loadFile();

  /**
   * Presents the user with the save file dialog
   * and returns the new file path the user selected.
   *
   * @return the filepath that the user specified
   */
  String saveFile();

  /**
   * Updates the image currently displayed by the view and makes
   * it the given model.
   *
   * @param m the model of the image to be shown
   */
  void updateImage(ImageProcessingModel m);

  /**
   * Asks the user the given query.
   *
   * @param message the query text to be asked
   */
  String queryUser(String message);

  /**
   * Shows an error message to the user.
   *
   * @param errorMessage the message that is sent to the user
   */
  void sendError(String errorMessage);
}
