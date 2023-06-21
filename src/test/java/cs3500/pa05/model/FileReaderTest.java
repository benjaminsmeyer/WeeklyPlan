package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderTest {

  private FileReader fileReader;

  @BeforeEach
  void setup() {
    fileReader = new FileReader();
  }

  @Test
  void getAllBujoFilesTest() {
    assertEquals("[MyWeek.bujo]", FileReader.getAllBujoFiles().toString());
  }

  @Test
  void openFile() {
    File file = new File("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    fileReader.openFile(file);
  }

  @Test
  void readFile() {
    String filePath = "src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo";
    cs3500.pa05.model.FileWriter fileWriter = new FileWriter(filePath);
    fileWriter.writeToFile("hello");

    try {
      assertEquals("hello", Files.readString(Path.of(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}