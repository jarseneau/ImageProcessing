package controllertests;

import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import controller.ConsoleController;
import controller.ImageProcessingController;
import model.ImageProcessingModel;
import model.MockModel;
import view.ImageProcessingView;
import view.MockView;
import view.PPMImageView;

import static org.junit.Assert.assertEquals;

/**
 * A test class for the console controller class.
 */
public class TestConsoleController {
  static final String welcome = "Welcome to the Image Processing program\n";
  static final String menu =
          "Supported operations are:\n" +
          "\tload image-path image-name\n" +
          "\tsave image-path image-name\n" +
          "\tred-component image-path image-name " +
                  "(same for green, blue, value, luma and intensity)\n" +
          "\thorizontal-flip image-path image-name\n" +
          "\tvertical-flip image-path image-name\n" +
          "\"q\" or \"quit\" to quit\n" +
          "\"menu\" to see this menu again\n";
  static final String farewell = "Have a nice day!\n";

  @Test
  public void testQ() { // Test quitting using "q"
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new PPMImageView(out);
    Readable input = new StringReader("q");
    ImageProcessingController controller = new ConsoleController(input, view);
    controller.control();
    assertEquals(welcome + menu + farewell, out.toString());
  }

  @Test
  public void testQuit() { // Test quitting using "quit"
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new PPMImageView(out);
    Readable input = new StringReader("quit");
    ImageProcessingController controller = new ConsoleController(input, view);
    controller.control();
    assertEquals(welcome + menu + farewell, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor1() {
    Readable input = new StringReader("load Koala.ppm k");
    ImageProcessingController controller = new ConsoleController(input, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor2() {
    ImageProcessingView view = new PPMImageView();
    ImageProcessingController controller = new ConsoleController(null, view);
  }

  @Test
  public void testBadCommand() {
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new PPMImageView(out);
    Readable input = new StringReader("invalidCommand Koala.ppm k q");
    ImageProcessingController controller = new ConsoleController(input, view);
    controller.control();

    String expectedOut =
            "Error: command invalidCommand not found.\n" +
                    "Error: command Koala.ppm not found.\n" +
                    "Error: command k not found.\n";
    assertEquals(welcome + menu + expectedOut + farewell, out.toString());
  }

  @Test
  public void testNoQuit() { // Test some operations with a finite input without quitting.
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new PPMImageView(out);
    Readable input = new StringReader("load Koala.ppm k");
    ImageProcessingController controller = new ConsoleController(input, view);
    controller.control();

    String expectedOut =
            "Loading image at Koala.ppm as k\n" +
                    "Successful!\n";

    assertEquals(welcome + menu + expectedOut + farewell, out.toString());
  }

  @Test
  public void testBrighten() { //Test controller is sending right info to model
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new PPMImageView(out);
    Readable input = new StringReader("brighten -10 mock darkMock");
    StringBuilder log = new StringBuilder();
    MockModel mock = new MockModel(log);
    Map<String, ImageProcessingModel> map = new HashMap<>();
    map.put("mock", mock);
    ImageProcessingController controller = new ConsoleController(input, view, map);
    controller.control();

    String expectedLog = "model.BrightenAdjustor is being called\n";

    assertEquals(expectedLog, mock.getLog().toString());
  }

  @Test
  public void testHFlip() { //Test controller is sending right info to model
    StringBuilder out = new StringBuilder();
    ImageProcessingView view = new PPMImageView(out);
    Readable input = new StringReader("horizontal-flip mock darkMock");
    StringBuilder log = new StringBuilder();
    MockModel mock = new MockModel(log);
    Map<String, ImageProcessingModel> map = new HashMap<>();
    map.put("mock", mock);
    ImageProcessingController controller = new ConsoleController(input, view, map);
    controller.control();

    String expectedLog =
            "model.FlipHorizontalAdjustor is being called\n";

    assertEquals(expectedLog, mock.getLog().toString());
  }

  @Test
  public void testSave() { //Test controller is sending right info view
    StringBuilder out = new StringBuilder();
    MockView mockView = new MockView(out);
    Readable input = new StringReader("horizontal-flip mock darkMock save dm.ppm darkMock");
    StringBuilder log = new StringBuilder();
    MockModel mockModel = new MockModel(log);
    Map<String, ImageProcessingModel> map = new HashMap<>();
    map.put("mock", mockModel);
    ImageProcessingController controller = new ConsoleController(input, mockView, map);
    controller.control();

    String expectedLog = "Saving model to path: dm.ppm\n";

    assertEquals(expectedLog, mockView.getLog().toString());
  }




}
