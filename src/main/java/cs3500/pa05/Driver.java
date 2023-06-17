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
    Week week = new Week();
    WeekManager.setup(week);

    primaryStage.setTitle("Week Journal");
    primaryStage.setScene(WeekManager.weekManager.getScene());
    WeekManager.weekManager.run();
    primaryStage.show();
  }
}
