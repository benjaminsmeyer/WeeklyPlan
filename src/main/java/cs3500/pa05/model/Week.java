package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {
  List<Day> days;
  String label;
  //List<String> categories;

  /**
   * Default constructor for Week
   *
   * @param days the Days of this Week
   * @param label the name of this Week
   */
  public Week(List<Day> days, String label) {
    this.days = days;
    this.label = label;
  }

  /**
   * Convenience constructor for Week. Initializes a week with empty days
   */
  public Week() {
    days = new ArrayList<>();
    setWeekStart(DayOfWeek.SUNDAY);

    this.label = "Week";
  }

  public Week(DayOfWeek startingDay) {
    setWeekStart(startingDay);
    this.label = "Week";
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

}
