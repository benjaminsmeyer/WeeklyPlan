package cs3500.pa05;

import cs3500.pa05.model.DayOfWeek;
import javafx.scene.Cursor;
import javafx.scene.Node;

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
    boolean pm = false;

    while (hour > 12) {
      hour = hour - 12;
      if (hour > 11)
        pm = !pm;
    }

    if (hour == 12) {
      pm = !pm;
    }

    return hour + ":" + ((minutes < 10)? "0" + minutes : minutes) + ((pm)? "PM" : "AM");
  }

  public static String dayOfWeekToString(DayOfWeek dayOfWeek) {
    return dayOfWeek.toString().charAt(0)
        + dayOfWeek.toString().substring(1).toLowerCase();
  }

  public static void setButtonCursorStatus(Node n) {
    n.setOnMouseEntered(e -> n.getScene().setCursor(Cursor.HAND));
    n.setOnMouseExited(e -> n.getScene().setCursor(Cursor.DEFAULT));
  }
}
