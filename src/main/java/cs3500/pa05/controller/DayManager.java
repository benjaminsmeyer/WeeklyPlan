package cs3500.pa05.controller;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the Day.
 */
public class DayManager {

  private final ArrayList<Activity> schedule;
  private int maxEvents;
  private DayOfWeek dayOfWeek;

  /**
   * Initializes the Day.
   *
   * @param schedule  the list of activities assigned to day
   * @param maxEvents the maximum amount of events allowed per day
   * @param dayOfWeek the day of the week for the event
   */
  public DayManager(ArrayList<Activity> schedule, int maxEvents, DayOfWeek dayOfWeek) {
    this.schedule = schedule;
    this.maxEvents = maxEvents;
    this.dayOfWeek = dayOfWeek;
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
}
