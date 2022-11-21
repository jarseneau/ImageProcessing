package view;

import controller.ImageProcessingFeatures;
import model.ImageProcessingModel;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
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
  private JList<String> listOfStrings;
  private ImageProcessingFeatures features;

  private ImageProcessingModel model;

  public ImageGUIView(ImageProcessingModel model) throws IOException {
    super();
    this.model = model;
    setTitle("GUI VIEW");
    setPreferredSize(new Dimension(1000, 800));
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
    columnPanel.add(histogramColPanel);


    int[][] histogram = model.histogram();
    JPanel redHistogram = new BarChart(histogram[0], Color.red);
    histogramColPanel.add(redHistogram);

    JPanel greenHistogram = new BarChart(histogram[1], Color.green);
    histogramColPanel.add(greenHistogram);

    JPanel blueHistogram = new BarChart(histogram[2], Color.blue);
    histogramColPanel.add(blueHistogram);

    JPanel intensityHistogram = new BarChart(histogram[3], Color.black);
    histogramColPanel.add(intensityHistogram);







    imageLabel = new JLabel();
    scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(600, 400));
    imageColPanel.add(scrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Load and Save"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.setPreferredSize(new Dimension(600, 200));
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
  public void render(ImageProcessingModel m) {

  }

  @Override
  public void addCommands(String[] commands) {
    // filter selection list
    JPanel selectionListPanel = new JPanel();
    selectionListPanel.setBorder(BorderFactory.createTitledBorder("Selection lists"));
    selectionListPanel.setLayout(new BoxLayout(selectionListPanel, BoxLayout.X_AXIS));
    selectionListPanel.setPreferredSize(new Dimension(300, 500));
    filterColPanel.add(selectionListPanel);

    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    for (String com: commands) {
      dataForListOfStrings.addElement(com);
    }

    listOfStrings = new JList<>(dataForListOfStrings);
    listOfStrings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane listScroll = new JScrollPane(listOfStrings);
    selectionListPanel.add(listScroll);

    this.revalidate();
    this.repaint();
  }

  @Override
  public void addFeatures(ImageProcessingFeatures features) {
    this.features = features;
  }

  @Override
  public void addActionListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
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
}
