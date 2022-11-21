package view;

import java.awt.*;

import javax.swing.*;

public class BarPanel extends JPanel {
  Color c;
  int height;

  public BarPanel(Color c, int height) {
    super();
    this.setPreferredSize(new Dimension(1, height));
    this.setBackground(c);
  }
}