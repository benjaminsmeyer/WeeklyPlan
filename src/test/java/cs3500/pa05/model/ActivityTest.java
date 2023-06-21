package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Activity Class.
 */
class ActivityTest {
  private Activity activity;

  /**
   * Set up Activity instance for tests.
   */
  @BeforeEach
  void setUp() {
    activity = new Task("task", "description", DayOfWeek.MONDAY);
  }

  /**
   * Tests the getName function.
   */
  @Test
  void getNameTest() {
    assertEquals("task", activity.getName());
  }

  /**
   * Tests the getDescription function.
   */
  @Test
  void getDescriptionTest() {
    assertEquals("description", activity.getDescription());
  }

  /**
   * Tests the getDayOfWeek function.
   */
  @Test
  void getDayOfWeekTest() {
    assertEquals(DayOfWeek.MONDAY, activity.getDayOfWeek());
  }

  /**
   * Tests the setDayOfWeek function.
   */
  @Test
  void setDayOfWeekTest() {
    activity.setDayOfWeek(DayOfWeek.SATURDAY);
    assertEquals(DayOfWeek.SATURDAY, activity.getDayOfWeek());
  }

  /**
   * Tests the setName function.
   */
  @Test
  void setNameTest() {
    activity.setName("new name");
    assertEquals("new name", activity.getName());
  }

  /**
   * Tests the setDescription function.
   */
  @Test
  void setDescriptionTest() {
    activity.setDescription("new description");
    assertEquals("new description", activity.getDescription());
  }
}