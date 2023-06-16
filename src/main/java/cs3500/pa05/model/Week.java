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

    days.add(new Day(DayOfWeek.SUNDAY));
    days.add(new Day(DayOfWeek.MONDAY));
    days.add(new Day(DayOfWeek.TUESDAY));
    days.add(new Day(DayOfWeek.WEDNESDAY));
    days.add(new Day(DayOfWeek.THURSDAY));
    days.add(new Day(DayOfWeek.FRIDAY));
    days.add(new Day(DayOfWeek.SATURDAY));

    this.label = "Week";
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
