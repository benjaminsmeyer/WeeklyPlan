package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {
  Map<DayOfWeek, Day> days;
  String label;
  //List<String> categories;

  public Week(Map<DayOfWeek, Day> days, String label) {
    this.days = days;
    this.label = label;
  }

  public Week() {
    days = new HashMap<>();

    days.put(DayOfWeek.SUNDAY, new Day(DayOfWeek.SUNDAY));
    days.put(DayOfWeek.MONDAY, new Day(DayOfWeek.MONDAY));
    days.put(DayOfWeek.TUESDAY, new Day(DayOfWeek.TUESDAY));
    days.put(DayOfWeek.WEDNESDAY, new Day(DayOfWeek.WEDNESDAY));
    days.put(DayOfWeek.THURSDAY, new Day(DayOfWeek.THURSDAY));
    days.put(DayOfWeek.FRIDAY, new Day(DayOfWeek.FRIDAY));
    days.put(DayOfWeek.SATURDAY, new Day(DayOfWeek.SATURDAY));

    this.label = "Week";
  }

  public List<Day> getDays() {
    return new ArrayList<>(days.values());
  }

}
