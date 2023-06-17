package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

public class Week {
  List<Day> days;
  String name;
  //List<String> categories;

  /**
   * Default constructor for Week
   *
   * @param days the Days of this Week
   * @param name the name of this Week
   */
  public Week(List<Day> days, String name) {
    this.days = days;
    this.name = name;
  }

  /**
   * Convenience constructor for Week. Initializes a week with empty days
   */
  public Week() {
    days = new ArrayList<>();
    setWeekStart(DayOfWeek.SUNDAY);

    this.name = "Week";
  }

  public Week(DayOfWeek startingDay) {
    setWeekStart(startingDay);
    this.name = "Week";
  }

  private void setWeekStart(DayOfWeek startingDay) {
    days = new ArrayList<>();
    List<DayOfWeek> array = List.of(DayOfWeek.values());

    DayOfWeek[] ordering = new DayOfWeek[array.size()];
    int indexOfStartingDay = array.indexOf(startingDay);

    for (int i = 0; i < array.size(); i ++) {
      int newIndexes = i + (array.size() - indexOfStartingDay);
      ordering[newIndexes % array.size()] = array.get(i);
    }

    for (DayOfWeek day : ordering) {
      days.add(new Day(day));
    }
  }


  /**
   * Returns the Days stored in this week
   *
   * @return the Days stored in this week
   */
  public List<Day> getDays() {
    return days;
  }

  public void addActivity(Activity activity) {
    for (Day day : days) {
      if (activity.getDayOfWeek() == day.getDayOfWeek()) {
        day.addActivity(activity);
      }
    }
  }

  public void updateActivityDates() {
    List<Activity> activities = getAllActivities();
    clearDayActivities();

    for (Day day : days) {
      for (Activity activity : activities) {
        if (day.getDayOfWeek() == activity.getDayOfWeek()) {
          day.addActivity(activity);
        }
      }
    }

  }

  private List<Activity> getAllActivities() {
    List<Activity> activities = new ArrayList<>();

    for (Day day : days) {
      activities.addAll(day.getSchedule());
    }

    return activities;
  }

  private void clearDayActivities() {
    for (Day day : days) {
      day.getSchedule().clear();
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}
