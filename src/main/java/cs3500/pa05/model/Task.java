package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A task for the day.
 */
public class Task extends Activity {
  private boolean complete;

  /**
   * Initializes the task.
   *
   * @param name the name of the task
   * @param description the description of the task
   * @param dayOfWeek the day of the week
   */
  public Task(String name, String description, DayOfWeek dayOfWeek) {
    super(name, description, dayOfWeek);
    incomplete();
  }

  @JsonCreator
  public Task(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek, @JsonProperty("done") boolean done) {
    super(name, description, dayOfWeek);
    this.complete = done;
  }

  /**
   * Set the task as complete.
   */
  public void complete() {
    complete = true;
  }

  /**
   * Set the task as incomplete.
   */
  public void incomplete() {
    complete = false;
  }

  /**
   * Checks if the task is done.
   *
   * @return true if task is complete, false otherwise.
   */
  public boolean isDone() {
    return complete;
  }
}
