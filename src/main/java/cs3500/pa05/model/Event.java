package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Event for the day.
 */
public class Event extends Activity {
  private int startTime; // STARTS AT 0000 TO 1440 IN MINUTES
  private int duration; // HOW LONG EVENT IS IN MINUTES

  /**
   * Initializes the event.
   *
   * @param name        the name of the event
   * @param description the description of the event
   * @param dayOfWeek   the day of the week for the event
   * @param startTime   the start time of the event
   * @param duration    the duration of the event
   */
  @JsonCreator
  public Event(@JsonProperty("name") String name, @JsonProperty("description") String description,
               @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
               @JsonProperty("startTime") int startTime, @JsonProperty("duration") int duration) {
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
   * Change the start time of the event.
   *
   * @param startTime the start time of the event
   */
  public void setStartTime(int startTime) {
    this.startTime = startTime;
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
   * Change the duration of the event.
   *
   * @param duration the duration of the event
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }
}
