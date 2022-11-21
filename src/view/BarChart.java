package view;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class BarChart extends JPanel {

  int[] values;
  Color color;

  public BarChart(int[] values, Color color) {
    this.values = values;
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    int max = -1;
    for (int i : values) {
      max = Math.max(max, i);
    }

    int width = 1;
    int x = 1;

    for (int i : values) {
      int height = (int) ((getHeight() - 5) * ((double)i / max));
      g.setColor(color);
      g.fillRect(x, getHeight() - height, width, height);
      x += 1;
    }

  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(256, 100);
  }

}
