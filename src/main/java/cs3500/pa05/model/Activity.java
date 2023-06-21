package cs3500.pa05.model;

/**
 * Name of the activity with their description and day.
 */
public abstract class Activity {
  private String name;
  private String description;
  private DayOfWeek dayOfWeek;

  /**
   * Initializes the activity.
   *
   * @param name        the name of the event
   * @param description the description of the event
   * @param dayOfWeek   the day of the week for the event
   */
  public Activity(String name, String description, DayOfWeek dayOfWeek) {
    this.name = name;
    this.description = description;
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Get the name of the event.
   *
   * @return the name of the event.
   */
  public String getName() {
    return name;
  }

  /**
   * Change the name of the event.
   *
   * @param name the name of the event.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the description of the event.
   *
   * @return the description of the event.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Change the description of the event.
   *
   * @param description the description of the event.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get the day of week of the event.
   *
   * @return the day of week of the event.
   */
  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Change the day of the week of the event.
   *
   * @param dayOfWeek the day of the week.
   */
  public void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

}
