package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ButtonListener implements ActionListener {
  private Map<String, Runnable> buttonClickedActions;

  /**
   * Empty default constructor
   */
  public ButtonListener() {
  }

  /**
   * Set the map for button events.
   */

  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      if (buttonClickedActions.containsKey(e.getActionCommand())) {
        buttonClickedActions.get(e.getActionCommand()).run();
      }
    } catch (NullPointerException n) {
      // do nothing
    }
  }
}