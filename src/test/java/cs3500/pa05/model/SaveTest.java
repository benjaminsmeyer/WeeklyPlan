package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.controller.PalletManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Save class.
 */
class SaveTest {
  /**
   * Tests function saveWeek.
   */
  @Test
  void saveWeekTest() {
    PalletManager.setupTestPalletManager();
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

    Week week = new Week(days, "test week", Integer.MAX_VALUE, Integer.MAX_VALUE, "", "",
        PalletManagerMockTest.defaultPallet);
    Save save = new Save("src/test/testfiles/weekTest.bujo");
    save.saveWeek(week);
    FileRead fileRead = new FileRead();
    File file = new File("src/test/testfiles/weekTest.bujo");
    fileRead.openFile(file);
    assertEquals("test week", fileRead.readFile().getName());
  }
}