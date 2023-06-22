package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Pallet;
import cs3500.pa05.model.Save;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DayView;
import cs3500.pa05.view.NewEventView;
import cs3500.pa05.view.NewTaskView;
import cs3500.pa05.view.TaskBox;
import cs3500.pa05.view.UpdateWeekNameView;
import cs3500.pa05.view.WeekView;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Manages the week.
 */
public class WeekManager {
  /**
   * WeekManager static instance.
   */
  public static WeekManager weekManager;

  private final Week week;
  @FXML
  private VBox mainBox;
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
  @FXML
  private ScrollPane taskPane;
  @FXML
  private ScrollPane sundayPane;
  @FXML
  private ScrollPane mondayPane;
  @FXML
  private ScrollPane tuesdayPane;
  @FXML
  private ScrollPane wednesdayPane;
  @FXML
  private ScrollPane thursdayPane;
  @FXML
  private ScrollPane fridayPane;
  @FXML
  private ScrollPane saturdayPane;
  @FXML
  private TextArea notes;
  @FXML
  private MenuButton themeDropDown;
  @FXML
  private TextArea quotes;
  @FXML
  private VBox overview;
  @FXML
  private Label notesLabel;
  @FXML
  private Label quotesLabel;
  @FXML
  private Label overviewLabel;
  private final WeekView weekView;

  /**
   * Setups week manager.
   *
   * @param week the week
   */
  private WeekManager(Week week) {
    this.week = week;
    weekView = new WeekView(this);
  }

  /**
   * Creates a new WeekManager instance for the given Week.
   *
   * @param week the week
   */
  public static void setup(Week week) {
    weekManager = new WeekManager(week);
  }

  /**
   * Updates the week view. Should be called after any visual change to the week.
   */
  public void run() {
    week.updateActivityDates();
    updateWeekNameDisplay();
    initWeek();
    save();
  }

  /**
   * Called when the GUI loads in. Performs initial setup.
   */
  @FXML
  public void initialize() {
    weekName.setOnMouseClicked(e -> updateWeekName());
    save.setOnAction(e -> save());
    setupPalletDropdown();
    run();
  }

  /**
   * Loads the pallets into the pallet dropdown and adds ActionListeners to each of them
   * that updates the pallet to the selected pallet.
   */
  private void setupPalletDropdown() {
    for (int i = 0; i < PalletManager.palletManager.getThemes().size(); i++) {
      int finalI = i;
      MenuItem nextItem = new MenuItem(PalletManager.palletManager.getThemes().get(finalI).name());
      nextItem.setOnAction(e ->
          updateTheme(PalletManager.palletManager.getThemes().get(finalI)));
      themeDropDown.getItems().add(nextItem);
    }
  }

  /**
   * Sets all Node color/font values to be those of the given Pallet.
   *
   * @param pallet the color pallet to update to
   */
  private void updateTheme(Pallet pallet) {
    this.week.setPallet(pallet);
    updateTheme();
    run();
  }

  /**
   * Sets all Node color/font values to be those of the current Pallet.
   */
  private void updateTheme() {
    updateTaskbarTheme();
    updateWeekViewTheme();
    updateNotesTheme();
  }

  /**
   * Saves current week.
   */
  private void save() {
    Save save = new Save(Constants.weekPath + this.week.getName());
    save.saveWeek(this.week);
  }

  /**
   * Initializes the week by placing all the events in the week and
   * naming all the days appropriately.
   */
  private void initWeek() {
    setupDayLayout();
    setupTaskQueue();
    setupNotesAndQuotes();
    setupDayLayouts();
    updateTheme();
    setupButtons();
    updateWeeklyStats();
  }

  /**
   * Adds action listeners to all the buttons in the WeekView.
   */
  private void setupButtons() {
    newEvent.setOnAction(e -> openNewEventMenu());
    newTask.setOnAction(e -> openNewTaskMenu());
  }

  /**
   * Resets the Task Queue.
   */
  private void setupTaskQueue() {
    tasksLayout.getChildren().clear();
    tasksLayout.getChildren().add(tasksName);
  }

  /**
   * Resets the Notes, Quotes, and Overview fields.
   */
  private void setupNotesAndQuotes() {
    quotes.setWrapText(true);
    notes.setWrapText(true);

    quotes.setText(week.getQuotes());
    notes.setText(week.getNotes());

    quotes.textProperty().addListener(e -> updateQuotes());
    notes.textProperty().addListener(e -> updateNotes());
  }

  /**
   * Update weekly stats.
   */
  private void updateWeeklyStats() {
    String weeklyStats = String.format("Total Events: %d\nTotal Tasks: %d\nTasks Completed: %,.2f",
        week.totalWeekEvents(), week.totalWeekTasks(), totalTaskCompletePercent())
        + "%";
    overview.getChildren().clear();
    Label overviewLabel = Utils.defaultLabel(weeklyStats);
    overviewLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    overviewLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    overviewLabel.setPrefWidth(240);
    overview.getChildren().add(overviewLabel);
    overview.setStyle(
        "-fx-background-color: " + PalletManager.palletManager.getCurrentPallet().overlayColor());
  }

  /**
   * Total task complete percent.
   *
   * @return the total task complete percent
   */
  private double totalTaskCompletePercent() {
    if (week.totalWeekTasks() == 0) {
      return 0;
    } else {
      return ((double) week.totalCompleteTasks() / (double) week.totalWeekTasks()) * 100;
    }
  }

  /**
   * Resets each day Layout.
   */
  private void setupDayLayouts() {
    List<Day> days = week.getDays();
    for (int i = 0; i < dayLayouts.size(); i++) {
      dayLayouts.get(i).getChildren().clear();

      dayNames.get(i).setText(Utils.dayOfWeekToString(days.get(i).getDayOfWeek()));
      dayLayouts.get(i).getChildren().add(dayNames.get(i));

      if (days.get(i).getEvents().size() > week.getMaxEvents()) {
        dayLayouts.get(i).getChildren().add(Utils.defaultLabel(
            "Max number of events (" + week.getMaxEvents() + ") exceeded!",
            Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor())));
      }

      if (days.get(i).getTasks().size() > week.getMaxTasks()) {
        dayLayouts.get(i).getChildren().add(Utils.defaultLabel(
            "Max number of tasks (" + week.getMaxTasks() + ") exceeded!",
            Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor())));
      }

      List<Activity> schedule = days.get(i).getSchedule();
      List<VBox> scheduleButtons = DayView.renderActivities(schedule);
      dayLayouts.get(i).getChildren().addAll(scheduleButtons);

      for (Activity a : schedule) {
        if (a instanceof Task) {
          tasksLayout.getChildren().add(new TaskBox(a.getName(), (Task) a));
        }
      }
    }
  }


  /**
   * Updated the theme of the UI elements in the task bar
   */
  private void updateTaskbarTheme() {
    mainBox.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().backgroundColor());
    newEvent.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().eventColor());
    newTask.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    save.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());

    newEvent.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    newTask.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    save.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    weekName.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    themeDropDown.setCursor(Cursor.HAND);
    themeDropDown.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    themeDropDown.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
  }

  /**
   * Updates the theme of the UI in the Week View
   */
  private void updateWeekViewTheme() {
    for (Label name : dayNames) {
      name.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    }
    tasksName.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    taskPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    mondayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    tuesdayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    wednesdayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    thursdayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    fridayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    saturdayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
    sundayPane.setStyle("-fx-background: "
        + PalletManager.palletManager.getCurrentPallet().overlayColor());
  }

  /**
   * Updates the theme of the UI elements in the Notes and Quotes section
   */
  private void updateNotesTheme() {
    notesLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    quotesLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    overviewLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    quotes.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    quotes.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());

    notes.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    notes.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());
  }

  /**
   * Gets values from the FXML for week HBoxes and stores them in a list.
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

  /**
   * Opens the menu for creating a new Event.
   */
  private void openNewEventMenu() {
    NewEventController newEventController = new NewEventController();
    NewEventView newEventView = new NewEventView(newEventController);

    Stage stage = new Stage();
    stage.setScene(newEventView.load());
    stage.show();
  }

  /**
   * Opens the menu for creating a new Task.
   */
  private void openNewTaskMenu() {
    NewTaskController newTaskController = new NewTaskController();
    NewTaskView newTaskView = new NewTaskView(newTaskController);

    Stage stage = new Stage();
    stage.setScene(newTaskView.load());
    stage.show();
  }

  /**
   * Returns the scene for this week.
   *
   * @return the Scene representing this week
   */
  public Scene getScene() {
    return weekView.load();
  }

  /**
   * Add an activity.
   *
   * @param activity the activity
   */
  public void addActivity(Activity activity) {
    week.addActivity(activity);
    run();
  }

  /**
   * Remove an activity.
   *
   * @param activity the activity
   */
  public void removeActivity(Activity activity) {
    week.removeActivity(activity);
    run();
  }

  /**
   * Update week name display.
   */
  private void updateWeekNameDisplay() {
    weekName.setText(week.getName());
  }

  /**
   * Update week name.
   */
  public void updateWeekName() {
    UpdateWeekNameController updateWeekNameController
        = new UpdateWeekNameController(this.week);
    UpdateWeekNameView updateWeekNameView = new UpdateWeekNameView(updateWeekNameController);

    Stage stage = new Stage();
    stage.setScene(updateWeekNameView.load());
    stage.show();
  }

  /**
   * Update notes.
   */
  private void updateNotes() {
    week.setNotes(notes.getText());
  }

  /**
   * Update quotes.
   */
  private void updateQuotes() {
    week.setQuotes(quotes.getText());
  }
}
