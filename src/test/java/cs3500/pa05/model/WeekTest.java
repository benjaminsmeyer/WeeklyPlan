package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Week class.
 */
class WeekTest {
  private Week week;
  private List<Day> days;

  /**
   * Instantiates Week instance for tests.
   */
  @BeforeEach
  void setUp() {
    days = new ArrayList<>();
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

    week = new Week(days, "test week", Integer.MAX_VALUE, Integer.MAX_VALUE, "", "",
        PalletManagerMockTest.defaultPallet);
  }

  /**
   * Tests updateWeekStart function.
   */
  @Test
  void updateWeekStartTest() {
    assertEquals(DayOfWeek.SUNDAY, week.getStartOfWeek());
    week.updateWeekStart(DayOfWeek.THURSDAY);
    assertEquals(DayOfWeek.THURSDAY, week.getStartOfWeek());
  }

  /**
   * Tests getWeekStart function.
   */
  @Test
  void getStartOfWeekTest() {
    assertEquals(DayOfWeek.SUNDAY, week.getStartOfWeek());
  }

  /**
   * Tests getDays function.
   */
  @Test
  void getDaysTest() {
    assertEquals(7, week.getDays().size());
  }

  /**
   * Tests that different Week class constructors build.
   */
  @Test
  void testContructors() {
    Week maxDefinedWeek = new Week("max's are defined", 10, 10, DayOfWeek.FRIDAY);
    assertEquals(10, maxDefinedWeek.getMaxEvents());
  }

  /**
   * Tests addActivity function.
   */
  @Test
  void addActivityTest() {
    Day day = week.getDays().get(0);
    assertEquals(2, day.getSchedule().size());
    week.addActivity(new Task("task", "description", DayOfWeek.SUNDAY));
    assertEquals(3, day.getSchedule().size());
  }

  /**
   * Tests removeActivity function.
   */
  @Test
  void removeActivityTest() {
    Day day = week.getDays().get(0);
    assertEquals(2, day.getSchedule().size());
    week.removeActivity(day.getTasks().get(0));
    assertEquals(1, day.getSchedule().size());
  }

  /**
   * Tests updateActivityDates function.
   */
  @Test
  void updateActivityDatesTest() {
    assertEquals(DayOfWeek.SUNDAY, week.getDays().get(0).getDayOfWeek());
    week.updateWeekStart(DayOfWeek.THURSDAY);
    week.updateActivityDates();
    assertEquals(DayOfWeek.THURSDAY, week.getDays().get(0).getDayOfWeek());
  }

  /**
   * Tests setName function.
   */
  @Test
  void setNameTest() {
    assertEquals("test week", week.getName());
    week.setName("new name");
    assertEquals("new name", week.getName());
  }

  /**
   * Tests getName function.
   */
  @Test
  void getNameTest() {
    assertEquals("test week", week.getName());
  }

  /**
   * Tests setMaxEvents function.
   */
  @Test
  void setMaxEventsTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxEvents());
    week.setMaxEvents(10);
    assertEquals(10, week.getMaxEvents());

  }

  /**
   * Tests getMaxEvents function.
   */
  @Test
  void setMaxTasksTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxTasks());
    week.setMaxTasks(10);
    assertEquals(10, week.getMaxTasks());
  }

  /**
   * Tests getMaxEvents function.
   */
  @Test
  void getMaxEventsTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxEvents());
  }

  /**
   * Tests getMaxTasks function.
   */
  @Test
  void getMaxTasksTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxTasks());
  }

  /**
   * Tests getNotes function.
   */
  @Test
  void getNotesTest() {
    assertEquals("", week.getNotes());
  }

  /**
   * Tests setNotes function.
   */
  @Test
  void setNotesTest() {
    assertEquals("", week.getNotes());
    week.setNotes("this is a note");
    assertEquals("this is a note", week.getNotes());
  }

  /**
   * Tests getQuotes function.
   */
  @Test
  void getQuotesTest() {
    assertEquals("", week.getQuotes());
  }

  /**
   * Tests setQuotes function.
   */
  @Test
  void setQuotesTest() {
    assertEquals("", week.getQuotes());
    week.setQuotes("this is a quote");
    assertEquals("this is a quote", week.getQuotes());
  }

  /**
   * Tests totalWeekEvents function.
   */
  @Test
  void totalWeekEventsTest() {
    assertEquals(1, week.totalWeekEvents());
    week.addActivity(new Event("new event", "new description", DayOfWeek.MONDAY, 2000, 60));
    assertEquals(2, week.totalWeekEvents());
  }

  /**
   * Tests totalWeekTasks function.
   */
  @Test
  void totalWeekTasksTest() {
    assertEquals(1, week.totalWeekTasks());
    week.addActivity(new Task("new task", "new description", DayOfWeek.MONDAY));
    assertEquals(2, week.totalWeekTasks());
  }

  /**
   * Tests totalCompleteTasks function.
   */
  @Test
  void totalCompleteTasksTest() {
    assertEquals(0, week.totalCompleteTasks());
    week.getDays().get(0).getTasks().get(0).complete();
    assertEquals(1, week.totalCompleteTasks());
  }

  /**
   * Tests getPalletName function.
   */
  @Test
  void getPalletNameTest() {
    assertEquals(PalletManagerMockTest.defaultPallet.name(), week.getPalletName());
  }

  /**
   * Tests setPallet function.
   */
  @Test
  void setPalletTest() {
    assertEquals(PalletManagerMockTest.defaultPallet.name(), week.getPalletName());
  }
}