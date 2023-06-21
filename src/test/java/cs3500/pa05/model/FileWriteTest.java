package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class FileWriteTest {

  @Test
  void writeToFileTest() {
    String filePath = "src/test/testfiles/weekTest.bujo";
    FileWrite fileWrite = new FileWrite(filePath);
    fileWrite.writeToFile("hello");

    try {
      assertEquals("hello", Files.readString(Path.of(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}