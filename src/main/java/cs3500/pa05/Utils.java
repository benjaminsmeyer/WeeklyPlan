package cs3500.pa05;

import cs3500.pa05.model.DayOfWeek;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * A utilities class.
 */
public class Utils {
  private static final String activityNameColor = "#000000";
  private static final String weekFont = "Verdana";

  /**
   * Given a time in minutes, converts it to a string in the form "12:00".
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
      if (hour > 11) {
        pm = !pm;
      }
    }

    if (hour == 12) {
      pm = !pm;
    }

    return hour + ":" + ((minutes < 10) ? "0" + minutes : minutes) + ((pm) ? "PM" : "AM");
  }

  /**
   * Converts the day of the week to a string.
   *
   * @param dayOfWeek the day of the week
   * @return a string of the day of the week
   */
  public static String dayOfWeekToString(DayOfWeek dayOfWeek) {
    return dayOfWeek.toString().charAt(0)
        + dayOfWeek.toString().substring(1).toLowerCase();
  }

  /**
   * Sets the button cursor status.
   *
   * @param n the button status
   */
  public static void setButtonCursorStatus(Node n) {
    n.setOnMouseEntered(e -> n.getScene().setCursor(Cursor.HAND));
    n.setOnMouseExited(e -> n.getScene().setCursor(Cursor.DEFAULT));
  }

  /**
   * Sets the default label.
   *
   * @param text the default label
   * @return the new label with its text
   */
  public static Label defaultLabel(String text) {
    Label label = new Label(text);
    label.setFont(Font.font(weekFont, FontWeight.NORMAL,
        FontPosture.REGULAR, 10));
    label.setTextFill(Color.web(activityNameColor));
    label.setWrapText(true);
    return label;
  }

  /**
   * Sets the default label with its paint color.
   *
   * @param text  the default label text
   * @param color the color of the default label
   * @return the new label with its text and color
   */
  public static Label defaultLabel(String text, Paint color) {
    Label label = defaultLabel(text);
    label.setTextFill(color);
    return label;
  }
}
