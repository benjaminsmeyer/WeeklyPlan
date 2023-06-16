package cs3500.pa05;

public class Utils {
  public static String timeToString(int startTime) {
    int hour = startTime / 60;
    int minutes = startTime % 60;

    return hour + ":" + minutes;
  }
}
