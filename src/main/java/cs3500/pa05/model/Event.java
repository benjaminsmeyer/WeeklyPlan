package cs3500.pa05.model;

/**
 * Event for the day.
 */
public class Event extends Activity {
  private int startTime; // STARTS AT 0000 TO 1440 IN MINUTES
  private int duration; // HOW LONG EVENT IS IN MINUTES

  /**
   * Initializes the event.
   *
   * @param name the name of the event
   * @param description the description of the event
   * @param dayOfWeek the day of the week for the event
   * @param startTime the start time of the event
   * @param duration the duration of the event
   */
  public Event(String name, String description, DayOfWeek dayOfWeek, int startTime, int duration) {
    super(name, description, dayOfWeek);
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Get the start time of the event.
   *
   * @return the start time of the event.
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Get the duration of the event.
   *
   * @return the duration of the event.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Change the start time of the event.
   *
   * @param startTime the start time of the event
   */
  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }

  /**
   * Change the duration of the event.
   *
   * @param duration the duration of the event
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }
}
