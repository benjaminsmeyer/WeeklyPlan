package cs3500.pa05;

import cs3500.pa05.controller.WeekManager;
import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs the weekly planner
 */
public class Driver extends Application {
  /**
   * Runs the program
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Test values to look at the week
    Map<DayOfWeek, Day> days = new HashMap<>();
    List<Activity> testSchedule = new ArrayList<>();

    testSchedule.add(new Event("Event name", "Some description", DayOfWeek.TUESDAY, 1000, 60));
    testSchedule.add(new Task("Event name", "Some description", DayOfWeek.TUESDAY));

    days.put(DayOfWeek.SUNDAY, new Day(DayOfWeek.SUNDAY));
    days.put(DayOfWeek.MONDAY, new Day(DayOfWeek.MONDAY));
    days.put(DayOfWeek.TUESDAY, new Day(DayOfWeek.TUESDAY, Integer.MAX_VALUE, testSchedule));
    days.put(DayOfWeek.WEDNESDAY, new Day(DayOfWeek.WEDNESDAY));
    days.put(DayOfWeek.THURSDAY, new Day(DayOfWeek.THURSDAY));
    days.put(DayOfWeek.FRIDAY, new Day(DayOfWeek.FRIDAY));
    days.put(DayOfWeek.SATURDAY, new Day(DayOfWeek.SATURDAY));

    Week week = new Week(days, "Some name");
    WeekManager weekManager = new WeekManager(week);

    primaryStage.setScene(weekManager.getScene());
    weekManager.run();
    primaryStage.show();
  }
}
