package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the event class.
 */
class EventTest {
  private Event event;

  /**
   * Setups the variables.
   */
  @BeforeEach
  void setUp() {
    event = new Event("test", "test", DayOfWeek.MONDAY, 1000, 2000);
  }

  /**
   * Checks if the start time returns correctly.
   */
  @Test
  void getStartTimeTest() {
    assertEquals(1000, event.getStartTime());
    event.setStartTime(1500);
    assertEquals(1500, event.getStartTime());
  }

  /**
   * Checks if the duration returns correctly.
   */
  @Test
  void getDurationTest() {
    assertEquals(2000, event.getDuration());
    event.setDuration(3000);
    assertEquals(3000, event.getDuration());
  }

  /**
   * Checks if the start time sets correctly.
   */
  @Test
  void setStartTimeTest() {
    assertEquals(1000, event.getStartTime());
    event.setStartTime(1500);
    assertEquals(1500, event.getStartTime());
  }

  /**
   * Checks if the duration time sets correctly.
   */
  @Test
  void setDurationTest() {
    assertEquals(2000, event.getDuration());
    event.setDuration(3000);
    assertEquals(3000, event.getDuration());
  }
}