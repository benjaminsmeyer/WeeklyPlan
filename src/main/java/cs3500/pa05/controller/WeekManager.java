package cs3500.pa05.controller;

import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DayView;
import cs3500.pa05.view.WeekView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Manages the week.
 */
public class WeekManager {
  private Week week;
  @FXML
  private Label weekName;
  @FXML
  private VBox tasksLayout;
  @FXML
  private VBox dayLayout1;
  @FXML
  private VBox dayLayout2;
  @FXML
  private VBox dayLayout3;
  @FXML
  private VBox dayLayout4;
  @FXML
  private VBox dayLayout5;
  @FXML
  private VBox dayLayout6;
  @FXML
  private VBox dayLayout7;
  private List<VBox> dayLayouts;
  private WeekView weekView;

  public WeekManager(Week week) {
    this.week = week;

    weekView = new WeekView(this);
  }

  /**
   * Starts the week controller
   */
  public void run() {
    initWeek();
  }

  /**
   * Initializes the week by placing all the events in the week and
   * naming all the days appropriately
   */
  private void initWeek() {
    List<Day> days = week.getDays();
    setupDayLayout();

    for (int i = 0; i < dayLayouts.size(); i ++) {
      List<Activity> schedule = days.get(i).getSchedule();
      List<VBox> scheduleButtons = DayView.renderActivities(schedule);

      dayLayouts.get(i).getChildren().addAll(scheduleButtons);
    }
  }

  /**
   * Gets values from the FXML for week HBoxes and stores them in a list
   */
  private void setupDayLayout() {
    dayLayouts = new ArrayList<>();
    dayLayouts.add(dayLayout1);
    dayLayouts.add(dayLayout2);
    dayLayouts.add(dayLayout3);
    dayLayouts.add(dayLayout4);
    dayLayouts.add(dayLayout5);
    dayLayouts.add(dayLayout6);
    dayLayouts.add(dayLayout7);
  }

  /**
   * Returns the scene for this week
   *
   * @return the Scene representing this week
   */
  public Scene getScene() {
    return weekView.loadScene();
  }
}
