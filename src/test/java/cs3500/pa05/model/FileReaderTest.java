package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the FileReader class.
 */
class FileReaderTest {
  private FileRead fileRead;

  /**
   * Setups the variables.
   */
  @BeforeEach
  void setup() {
    fileRead = new FileRead();
  }

  /**
   * Tests the getAllBujoFiles function.
   */
  @Test
  void getAllBujoFilesTest() {
    assertEquals("[MyWeek.bujo]", FileRead.getAllBujoFiles().toString());
  }

  /**
   * Tests the openFile function.
   */
  @Test
  void openFileTest() {
    File file = new File("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    fileRead.openFile(file);
  }

  /**
   * Tests the readFile function.
   */
  @Test
  void readFileTest() {
    String filePath = "src/test/testfiles/weekTest.bujo";
    FileWriter fileWriter = new FileWriter(filePath);
    fileWriter.writeToFile("hello");

    try {
      assertEquals("hello", Files.readString(Path.of(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }

    filePath = "src/test/testfiles/weekTest.bujo";
    fileWriter = new FileWriter(filePath);
    fileWriter.writeToFile("test");

    try {
      assertEquals("test", Files.readString(Path.of(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}