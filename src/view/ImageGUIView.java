package view;

import controller.ImageProcessingFeatures;
import model.ImageProcessingModel;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGUIView extends JFrame implements ImageProcessingRenderedView {
  private final JScrollPane scrollPane;
  private final JLabel imageLabel;
  private final JPanel mainPanel;
  private final JPanel columnPanel;
  private final JPanel imageColPanel;
  private final JPanel filterColPanel;
  private final JPanel histogramColPanel;
  private final JButton fileOpenButton;
  private final JButton fileSaveButton;
  private JButton[] filters;
  private JList<String> listOfStrings;

  public ImageGUIView() throws IOException {
    super();
    setTitle("GUI VIEW");
    setPreferredSize(new Dimension(1400, 1000));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    this.mainPanel = new JPanel();
    add(mainPanel);

    this.columnPanel = new JPanel();
    columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.X_AXIS));
    mainPanel.add(columnPanel);

    // add filter buttons column
    this.filterColPanel = new JPanel();
    filterColPanel.setLayout(new BoxLayout(filterColPanel, BoxLayout.Y_AXIS));
    columnPanel.add(filterColPanel);

    // add image and saving column
    this.imageColPanel = new JPanel();
    imageColPanel.setLayout(new BoxLayout(imageColPanel, BoxLayout.Y_AXIS));
    columnPanel.add(imageColPanel);

    this.histogramColPanel = new JPanel();
    histogramColPanel.setLayout(new BoxLayout(histogramColPanel, BoxLayout.Y_AXIS));
    //columnPanel.add(histogramColPanel);

    imageLabel = new JLabel();
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(960, 800));
    imageColPanel.add(scrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Load and Save"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.setPreferredSize(new Dimension(960, 200));
    imageColPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);

    // file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);

    pack();
    setVisible(true);
  }

  @Override
  public void save(String filename, ImageProcessingModel model) throws IllegalStateException,
          NullPointerException {

  }

  @Override
  public void renderMessage(String message) throws NullPointerException {
    // something to do with sending a warning message -> only need messages for errors now
  }

  @Override
  public void addCommands(String[] commands) {
    JPanel filterButtonPanel = new JPanel();
    filterButtonPanel.setBorder(BorderFactory.createTitledBorder("Filters to Apply"));
    filterButtonPanel.setLayout(new FlowLayout());
    filterButtonPanel.setPreferredSize(new Dimension(300, 500));
    filterColPanel.add(filterButtonPanel);
    filters = new JButton[commands.length];

    for (int i = 0; i < commands.length; i++) {
      filters[i] = new JButton(commands[i]);
      filters[i].setActionCommand(commands[i]);
      filterButtonPanel.add(filters[i]);
    }

    this.revalidate();
    this.repaint();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
    for (JButton button: filters) {
      button.addActionListener(listener);
    }
  }

  @Override
  public void addListListener(ListSelectionListener listener) {
    listOfStrings.addListSelectionListener(listener);
  }

  @Override
  public String loadFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image Files", "jpg", "png", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(ImageGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    throw new IllegalStateException("I have no idea whats happening");
  }

  @Override
  public String saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(ImageGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    throw new IllegalStateException("I have no idea whats happening");
  }

  @Override
  public void updateImage(ImageProcessingModel model) {
    BufferedImage image = new BufferedImage(model.getImageWidth(), model.getImageHeight(),
            BufferedImage.TYPE_INT_RGB);
    Color c;
    for (int row = 0; row < model.getImageHeight(); row++) {
      for (int col = 0; col < model.getImageWidth(); col++) {
        c = new Color(model.getPixelAt(row, col).getChannel("red"),
                model.getPixelAt(row, col).getChannel("green"),
                model.getPixelAt(row, col).getChannel("blue"));
        image.setRGB(col, row, c.getRGB());
      }
    }
    imageLabel.setIcon(new ImageIcon(image));
    this.revalidate();
    this.repaint();
  }

  @Override
  public String queryUser(String message) {
    return JOptionPane.showInputDialog(message);
  }

  @Override
  public void sendError(String errorMessage) {
    JOptionPane.showMessageDialog(ImageGUIView.this,
            errorMessage,
            "Error!",
            JOptionPane.PLAIN_MESSAGE);
  }
}
