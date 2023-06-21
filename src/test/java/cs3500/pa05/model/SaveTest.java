package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SaveTest {

  @Test
  void saveWeekTest() {
    List<Day> days = new ArrayList<>();

    Day day = new Day(DayOfWeek.SUNDAY);
    day.addActivity(new Task("task", "task description", DayOfWeek.SUNDAY));
    day.addActivity(new Event("event", "event description", DayOfWeek.SUNDAY, 1000, 60));
    days.add(day);

    days.add(new Day(DayOfWeek.MONDAY));
    days.add(new Day(DayOfWeek.TUESDAY));
    days.add(new Day(DayOfWeek.WEDNESDAY));
    days.add(new Day(DayOfWeek.THURSDAY));
    days.add(new Day(DayOfWeek.FRIDAY));
    days.add(new Day(DayOfWeek.SATURDAY));

    Week week = new Week(days, "test week");
    Save save = new Save("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    save.saveWeek(week);
    FileReader fileReader = new FileReader();
    File file = new File("src/test/java/cs3500/pa05/model/testfiles/weekTest.bujo");
    fileReader.openFile(file);
    assertEquals("test week", fileReader.readFile().getName());
  }
}