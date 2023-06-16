package cs3500.pa05;

import cs3500.pa05.model.DayOfWeek;

/**
 * A utilities class
 */
public class Utils {
  /**
   * Given a time in minutes, converts it to a string in the form "12:00"
   *
   * @param startTime the time to convert in minutes
   * @return the time as a String
   */
  public static String timeToString(int startTime) {
    int hour = startTime / 60;
    int minutes = startTime % 60;

    if (hour > 12) {
      hour = hour - 12;
    }

    return hour + ":" + minutes;
  }

  public static String dayOfWeekToString(DayOfWeek dayOfWeek) {
    return dayOfWeek.toString().charAt(0)
        + dayOfWeek.toString().substring(1).toLowerCase();
  }
}
