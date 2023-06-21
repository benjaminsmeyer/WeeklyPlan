package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderTest {

  private FileRead fileRead;

  @BeforeEach
  void setup() {
    fileRead = new FileRead();
  }

  @Test
  void getAllBujoFilesTest() {
    assertEquals("[MyWeek.bujo]", FileRead.getAllBujoFiles().toString());
  }

  @Test
  void openFileTest() {
    File file = new File("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    fileRead.openFile(file);
  }

  @Test
  void readFileTest() {
    String filePath = "src/test/testfiles/weekTest.bujo";
    FileWrite fileWrite = new FileWrite(filePath);
    fileWrite.writeToFile("hello");

    try {
      assertEquals("hello", Files.readString(Path.of(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }

    filePath = "src/test/testfiles/weekTest.bujo";
    fileWrite = new FileWrite(filePath);
    fileWrite.writeToFile("test");

    try {
      assertEquals("test", Files.readString(Path.of(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}