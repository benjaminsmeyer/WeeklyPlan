package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DayView;
import cs3500.pa05.view.NewEventView;
import cs3500.pa05.view.NewTaskView;
import cs3500.pa05.view.TaskBox;
import cs3500.pa05.view.UpdateWeekNameView;
import cs3500.pa05.view.WeekView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Manages the week.
 */
public class WeekManager {
  private Week week;
  @FXML
  private Label weekName;
  @FXML
  private Label tasksName;
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
  @FXML
  private Label dayName1;
  @FXML
  private Label dayName2;
  @FXML
  private Label dayName3;
  @FXML
  private Label dayName4;
  @FXML
  private Label dayName5;
  @FXML
  private Label dayName6;
  @FXML
  private Label dayName7;
  private List<Label> dayNames;
  @FXML
  private Button newEvent;
  @FXML
  private Button newTask;
  @FXML
  private Button save;
  private WeekView weekView;
  public static WeekManager weekManager;
  @FXML
  private TextArea notes;
  @FXML
  private TextArea quotes;
  @FXML
  private TextArea overview;


  private WeekManager(Week week) {
    this.week = week;
    weekView = new WeekView(this);
  }

  @FXML
  public void initialize(){
    weekName.setOnMouseClicked(e -> updateWeekName());
  }

  public static void setup(Week week) {
    weekManager = new WeekManager(week);
  }

  /**
   * Starts the week controller
   */
  public void run() {
    week.updateActivityDates();
    updateWeekNameDisplay();
    initWeek();
  }

  /**
   * Initializes the week by placing all the events in the week and
   * naming all the days appropriately
   */
  private void initWeek() {
    List<Day> days = week.getDays();
    setupDayLayout();
    tasksLayout.getChildren().clear();
    tasksLayout.getChildren().add(tasksName);

    notes.setStyle("-fx-background-color: " + Constants.eventColor);
    quotes.setStyle("-fx-background-color: " + Constants.eventColor);

    quotes.setWrapText(true);
    notes.setWrapText(true);
    overview.setWrapText(true);

    quotes.setText(week.getQuotes());
    notes.setText(week.getNotes());
    overview.setEditable(false);

    // Show statistics for the Week in the GUI. At a minimum, include total Events and total Tasks, and percent of tasks completed.
    String weeklyStats = String.format("Total Events: %d\nTotal Tasks: %d\nTasks Completed: %d", week.totalWeekEvents(), week.totalWeekTasks(), 10)
        + "%"; // TODO: Add week.getPercentOfTotalTasksCompleted
    overview.setText(weeklyStats);

    for (int i = 0; i < dayLayouts.size(); i ++) {
      dayLayouts.get(i).getChildren().clear();

      List<Activity> schedule = days.get(i).getSchedule();
      List<VBox> scheduleButtons = DayView.renderActivities(schedule);

      dayNames.get(i).setText(Utils.dayOfWeekToString(days.get(i).getDayOfWeek()));
      dayLayouts.get(i).getChildren().add(dayNames.get(i));

      if (days.get(i).getEvents().size() > week.getMaxEvents()) {
        dayLayouts.get(i).getChildren().add(Utils.defaultLabel(
            "Max number of events (" + week.getMaxEvents() + ") exceeded!",
            Constants.invalidInputLabelColor));
      }

      if (days.get(i).getTasks().size() > week.getMaxTasks()) {
        dayLayouts.get(i).getChildren().add(Utils.defaultLabel(
            "Max number of tasks (" + week.getMaxTasks() + ") exceeded!",
            Constants.invalidInputLabelColor));
      }

      dayLayouts.get(i).getChildren().addAll(scheduleButtons);

      for (Activity a : schedule) {
        if (a instanceof Task) {
          tasksLayout.getChildren().add(new TaskBox(a.getName(), (Task)a));
        }
      }
    }

    newEvent.setOnAction(e -> openNewEventMenu());
    newTask.setOnAction(e -> openNewTaskMenu());
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

    dayNames = new ArrayList<>();
    dayNames.add(dayName1);
    dayNames.add(dayName2);
    dayNames.add(dayName3);
    dayNames.add(dayName4);
    dayNames.add(dayName5);
    dayNames.add(dayName6);
    dayNames.add(dayName7);
  }

  private void openNewEventMenu() {
    NewEventController newEventController = new NewEventController();
    NewEventView newEventView = new NewEventView(newEventController);

    Stage stage = new Stage();
    stage.setScene(newEventView.load());
    stage.show();
  }

  private void openNewTaskMenu() {
    NewTaskController newTaskController = new NewTaskController();
    NewTaskView newTaskView = new NewTaskView(newTaskController);

    Stage stage = new Stage();
    stage.setScene(newTaskView.load());
    stage.show();
  }
  /**
   * Returns the scene for this week
   *
   * @return the Scene representing this week
   */
  public Scene getScene() {
    return weekView.loadScene();
  }

  public void addActivity(Activity activity) {
    week.addActivity(activity);
    initWeek();
  }

  private void updateWeekNameDisplay() {
    weekName.setText(week.getName());
  }

  public void updateWeekName() {
    UpdateWeekNameController updateWeekNameController
        = new UpdateWeekNameController(this.week);
    UpdateWeekNameView updateWeekNameView = new UpdateWeekNameView(updateWeekNameController);

    Stage stage = new Stage();
    stage.setScene(updateWeekNameView.load());
    stage.show();
  }
}
