package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create a week.
 */
public class Week {
  Map<DayOfWeek, Day> days;
  String label;
  //List<String> categories;

  /**
   * Default constructor for Week.
   *
   * @param days the Days of this Week
   * @param label the name of this Week
   */
  public Week(Map<DayOfWeek, Day> days, String label) {
    this.days = days;
    this.label = label;
  }

  /**
   * Convenience constructor for Week. Initializes a week with empty days.
   */
  public Week() {
    setWeekStart(DayOfWeek.SUNDAY);
//    days = new HashMap<>();
//
//    days.put(DayOfWeek.SUNDAY, new Day(DayOfWeek.SUNDAY));
//    days.put(DayOfWeek.MONDAY, new Day(DayOfWeek.MONDAY));
//    days.put(DayOfWeek.TUESDAY, new Day(DayOfWeek.TUESDAY));
//    days.put(DayOfWeek.WEDNESDAY, new Day(DayOfWeek.WEDNESDAY));
//    days.put(DayOfWeek.THURSDAY, new Day(DayOfWeek.THURSDAY));
//    days.put(DayOfWeek.FRIDAY, new Day(DayOfWeek.FRIDAY));
//    days.put(DayOfWeek.SATURDAY, new Day(DayOfWeek.SATURDAY));

    this.label = "Week";
  }

  /**
   * Convenience constructor for Week. Initializes a week starting
   * at <code>startingDay</code> with empty days.
   *
   * @param startingDay what day the week should start on
   */
  public Week(DayOfWeek startingDay) {
    setWeekStart(startingDay);
    this.label = "Week";

  }

  /**
   * Sets which day of the week, the Week should start on.
   *
   * @param startingDay what day the week should start on
   */
  private void setWeekStart(DayOfWeek startingDay) {
    days = new HashMap<>();
    List<DayOfWeek> array = List.of(DayOfWeek.values());

    DayOfWeek[] ordering = new DayOfWeek[array.size()];
    int indexOfStartingDay = array.indexOf(startingDay);

    for (int i = 0; i < array.size(); i ++) {
      int newIndexes = i + (array.size() - indexOfStartingDay);
      ordering[newIndexes % array.size()] = array.get(i);
    }

    for (DayOfWeek day : ordering) {
      days.put(day, new Day(day));
    }
  }

  /**
   * Returns the Days stored in this week
   *
   * @return the Days stored in this week
   */
  public List<Day> getDays() {
    return new ArrayList<>(days.values());
  }

}
