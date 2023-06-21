package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {

  private Week week;

  @BeforeEach
  void setup() {
    week = new Week();
  }

  @Test
  void updateWeekStart() {
    week.updateWeekStart(DayOfWeek.THURSDAY);
  }

  @Test
  void getStartOfWeek() {
    week.updateWeekStart(DayOfWeek.THURSDAY);
    assertEquals(DayOfWeek.THURSDAY, week.getStartOfWeek());
  }

  @Test
  void getDays() {
    assertEquals(DayOfWeek.SUNDAY, week.getDays().get(0).getDayOfWeek());
  }

  @Test
  void addActivity() {
    week.addActivity(new Event("hello", "hello", DayOfWeek.THURSDAY, 100, 100));
  }

  @Test
  void removeActivity() {
    week.removeActivity(new Event("hello", "hello", DayOfWeek.THURSDAY, 100, 100));
  }

  @Test
  void updateActivityDates() {
    week.updateActivityDates();
  }

  @Test
  void getName() {
    assertEquals("My Week", week.getName());
  }

  @Test
  void setName() {
    assertEquals("My Week", week.getName());
    week.setName("hello");
    assertEquals("hello", week.getName());
  }

  @Test
  void getMaxEvents() {
    assertEquals(2147483647, week.getMaxEvents());
  }

  @Test
  void setMaxEvents() {
    assertEquals(2147483647, week.getMaxEvents());
    week.setMaxEvents(10);
    assertEquals(10, week.getMaxEvents());
  }

  @Test
  void getMaxTasks() {
    assertEquals(2147483647, week.getMaxTasks());
  }

  @Test
  void setMaxTasks() {
    assertEquals(2147483647, week.getMaxTasks());
    week.setMaxTasks(10);
    assertEquals(10, week.getMaxTasks());
  }

  @Test
  void getNotes() {
    assertEquals("", week.getNotes());
  }

  @Test
  void setNotes() {
    assertEquals("", week.getNotes());
    week.setNotes("hello");
    assertEquals("hello", week.getNotes());
  }

  @Test
  void getQuotes() {
    assertEquals("", week.getQuotes());
  }

  @Test
  void setQuotes() {
    assertEquals("", week.getQuotes());
    week.setQuotes("hello");
    assertEquals("hello", week.getQuotes());
  }

  @Test
  void totalWeekEvents() {
  }

  @Test
  void totalWeekTasks() {
  }

  @Test
  void totalCompleteTasks() {
  }

  @Test
  void getPalletName() {
  }

  @Test
  void setPallet() {
  }
}