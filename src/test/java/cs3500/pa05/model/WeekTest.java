package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.controller.PalletManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {
  private Week week;

  @BeforeEach
  void setUp() {
    List<Day> days = new ArrayList<>();
    Day day = new Day(DayOfWeek.SUNDAY);
    day.addActivity(new Task("task", "task description", DayOfWeek.SUNDAY));
    day.addActivity(new Event("event", "event description", DayOfWeek.SUNDAY, 1000, 60));
    days.add(day);
    week = new Week(days, "test week");
  }

  @Test
  void updateWeekStartTest() {
    assertEquals(DayOfWeek.SUNDAY, week.getStartOfWeek());
    week.updateWeekStart(DayOfWeek.THURSDAY);
    assertEquals(DayOfWeek.THURSDAY, week.getStartOfWeek());
  }

  @Test
  void getStartOfWeekTest() {
    assertEquals(DayOfWeek.SUNDAY, week.getStartOfWeek());
  }

  @Test
  void getDaysTest() {
    assertEquals(1, week.getDays().size());
  }

  @Test
  void addActivityTest() {
    Day day = week.getDays().get(0);
    assertEquals(2, day.getSchedule().size());
    week.addActivity(new Task("task", "description", DayOfWeek.SUNDAY));
    assertEquals(3, day.getSchedule().size());
  }

  @Test
  void removeActivityTest() {
    Day day = week.getDays().get(0);
    assertEquals(2, day.getSchedule().size());
    week.removeActivity(new Task("task", "description", DayOfWeek.SUNDAY));
    assertEquals(1, day.getSchedule().size());
  }

  @Test
  void updateActivityDatesTest() {

  }

  @Test
  void setNameTest() {
    assertEquals("test week", week.getName());
    week.setName("new name");
    assertEquals("new name", week.getName());
  }

  @Test
  void getNameTest() {
    assertEquals("test week", week.getName());
  }

  @Test
  void setMaxEventsTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxEvents());
    week.setMaxEvents(10);
    assertEquals(10, week.getMaxEvents());

  }

  @Test
  void setMaxTasksTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxTasks());
    week.setMaxTasks(10);
    assertEquals(10, week.getMaxTasks());
  }

  @Test
  void getMaxEventsTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxEvents());
  }

  @Test
  void getMaxTasksTest() {
    assertEquals(Integer.MAX_VALUE, week.getMaxTasks());
  }

  @Test
  void getNotesTest() {
    assertEquals("", week.getNotes());
  }

  @Test
  void setNotesTest() {
    assertEquals("", week.getNotes());
    week.setNotes("this is a note");
    assertEquals("this is a note", week.getNotes());
  }

  @Test
  void getQuotesTest() {
    assertEquals("", week.getQuotes());
  }

  @Test
  void setQuotesTest() {
    assertEquals("", week.getQuotes());
    week.setQuotes("this is a quote");
    assertEquals("this is a note", week.getQuotes());
  }

  @Test
  void totalWeekEventsTest() {
    assertEquals(1, week.totalWeekEvents());
    week.addActivity(new Event("new event", "description", DayOfWeek.MONDAY, 2000, 60));
    assertEquals(2, week.totalWeekEvents());
  }

  @Test
  void totalWeekTasksTest() {
    assertEquals(1, week.totalWeekEvents());
    week.addActivity(new Event("new event", "description", DayOfWeek.MONDAY, 2000, 60));
    assertEquals(2, week.totalWeekEvents());
  }

  @Test
  void totalCompleteTasksTest() {
    assertEquals(0, week.totalCompleteTasks());
    //set the first activity as complete
    week.getDays().get(0).getTasks().get(0).complete();
    assertEquals(1, week.totalCompleteTasks());
  }

  @Test
  void getPalletNameTest() {
    assertEquals(PalletManager.defaultPallet.name(), week.getPalletName());
  }

  @Test
  void setPalletTest() {
    assertEquals(PalletManager.defaultPallet.name(), week.getPalletName());
    week.setPallet(PalletManager.bubblegumPallet);
    assertEquals(PalletManager.bubblegumPallet.name(), week.getPalletName());
  }
}