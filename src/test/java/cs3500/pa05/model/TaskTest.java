package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the task class
 */
class TaskTest {
  private Task task;

  /**
   * Sets up the tests
   */
  @BeforeEach
  void setUp() {
    task = new Task("test", "test", DayOfWeek.MONDAY);
  }

  /**
   * Checks if complete works correctly
   */
  @Test
  void completeTest() {
    assertFalse(task.isDone());
    task.complete();
    assertTrue(task.isDone());
  }

  /**
   * Checks if incomplete works correctly
   */
  @Test
  void incompleteTest() {
    assertFalse(task.isDone());
    task.incomplete();
    assertFalse(task.isDone());
  }

  /**
   * Checks if isDone works correctly
   */
  @Test
  void isDoneTest() {
    assertFalse(task.isDone());
    task.incomplete();
    assertFalse(task.isDone());
    task.complete();
    assertTrue(task.isDone());
  }
}