package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

public class Day {
  private List<Activity> schedule;
  private int maxEvents;
  private DayOfWeek dayOfWeek;


  public Day(DayOfWeek dayOfWeek, int maxEvents, List<Activity> schedule) {
    this.schedule = schedule;
    this.maxEvents = maxEvents;
    this.dayOfWeek = dayOfWeek;
  }

  public Day(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    schedule = new ArrayList<>();
    maxEvents = Integer.MAX_VALUE;
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

  /**
   * Updates number of maximum events allowed per day.
   *
   * @param max maximum number of events allowed per day
   */
  public void setMaxEvents(int max) {
    maxEvents = max;
  }
}
