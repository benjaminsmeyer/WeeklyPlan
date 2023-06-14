package cs3500.pa05.model;

/**
 * A task for the day.
 */
public class Task extends Activity {
  private boolean task;

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

  /**
   * Set the task as complete.
   */
  public void complete() {
    task = true;
  }

  /**
   * Set the task as incomplete.
   */
  public void incomplete() {
    task = false;
  }

  /**
   * Checks if the task is done.
   *
   * @return true if task is complete, false otherwise.
   */
  public boolean isDone() {
    return task;
  }
}
