package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityTest {
  private Activity activity;
  @BeforeEach
  void setUp() {
    activity = new Task("task", "description", DayOfWeek.MONDAY);
  }

  @Test
  void getNameTest() {
    assertEquals("task", activity.getName());
  }

  @Test
  void getDescriptionTest() {
    assertEquals("description", activity.getDescription());
  }

  @Test
  void getDayOfWeekTest() {
    assertEquals(DayOfWeek.MONDAY, activity.getDayOfWeek());
  }

  @Test
  void setDayOfWeekTest() {
    activity.setDayOfWeek(DayOfWeek.SATURDAY);
    assertEquals(DayOfWeek.SATURDAY, activity.getDayOfWeek());
  }

  @Test
  void setNameTest() {
    activity.setName("new name");
    assertEquals("new name", activity.getName());
  }

  @Test
  void setDescriptionTest() {
    activity.setDescription("new description");
    assertEquals("new description", activity.getDescription());
  }
}