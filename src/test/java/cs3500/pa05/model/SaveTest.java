package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Test;

class SaveTest {

  @Test
  void saveWeekTest() {
    Save save = new Save("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    Week week = new Week();
    save.saveWeek(week);
    FileReader fileReader = new FileReader();
    File file = new File("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    fileReader.openFile(file);
    assertEquals("My Week", fileReader.readFile().getName());
  }
}