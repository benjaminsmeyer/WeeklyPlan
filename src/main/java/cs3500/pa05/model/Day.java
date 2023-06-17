package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a Day.
 */
public class Day {
  private List<Activity> schedule;
  private DayOfWeek dayOfWeek;


  /**
   * Default constructor for day
   *
   * @param dayOfWeek the day of the week represented by this day
   * @param schedule a list of events for the day
   */
  public Day(DayOfWeek dayOfWeek, List<Activity> schedule) {
    this.schedule = schedule;
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * A convenience constructor for Day. Initializes it with an empty list of activities
   * and a max number of maxEvents
   *
   * @param dayOfWeek the day of the week represented by this day
   */
  public Day(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    this.schedule = new ArrayList<>();
  }

  public List<Activity> getSchedule()  {
    return schedule;
  }

  /**
   * Returns all Tasks stored in this day.
   *
   * @return  all tasks stored in this day
   */
  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();

    for (Activity activity : schedule) {
      if (activity instanceof Task) {
        tasks.add((Task) activity);
      }
    }

    return tasks;
  }

  /**
   * Returns all Events stored in this day.
   *
   * @return  all events stored in this day
   */
  public List<Event> getEvents() {
    List<Event> events = new ArrayList<>();

    for (Activity activity : schedule) {
      if (activity instanceof Event) {
        events.add((Event) activity);
      }
    }

    return events;
  }

  /**
   * Adds activity to the day.
   *
   * @param activity  activity to be added to day
   */
  public void addActivity(Activity activity) {
    schedule.add(activity);
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }
}
