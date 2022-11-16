package view;

import controller.ImageCommand;
import model.ImageProcessingModel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class ImageGUIView extends JFrame implements ImageProcessingRenderedView {
  private final JScrollPane scrollPane;
  private final JLabel imageLabel;
  private final JPanel mainPanel;
  private final JPanel columnPanel;
  private final JPanel imageColPanel;
  private final JPanel filterColPanel;
  private final JPanel histogramColPanel;
  private final JLabel fileOpenDisplay;
  private final JLabel fileSaveDisplay;
  private JList<String> listOfStrings;

  public ImageGUIView(String[] commands) throws IOException {
    super();
    setTitle("GUI VIEW");
    setPreferredSize(new Dimension(1000, 1000));
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

    // add image and saving column
    this.imageColPanel = new JPanel();
    imageColPanel.setLayout(new BoxLayout(imageColPanel, BoxLayout.Y_AXIS));
    columnPanel.add(imageColPanel);

    this.histogramColPanel = new JPanel();
    histogramColPanel.setLayout(new BoxLayout(histogramColPanel, BoxLayout.Y_AXIS));
    //columnPanel.add(histogramColPanel);

    ImageIcon i = null;
    imageLabel = new JLabel(i, JLabel.CENTER);
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
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    // file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    pack();
    setVisible(true);
  }

  @Override
  public void save(String filename, ImageProcessingModel model) throws IllegalStateException, NullPointerException {

  }

  @Override
  public void renderMessage(String message) throws NullPointerException {
    // something to do with sending a warning message -> only need messages for errors now
  }

  @Override
  public void render(ImageProcessingModel m) {

  }
}
