package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Update week name controller.
 */
public class UpdateWeekNameController {
  @FXML
  private Button saveButton;
  @FXML
  private Label nameLabel;
  @FXML
  private Label maxEventsLabel;
  @FXML
  private Label maxTasksLabel;
  @FXML
  private TextField maxEventsField;
  @FXML
  private TextField maxTasksField;
  @FXML
  private TextField name;
  @FXML
  private Button sunday;
  @FXML
  private Button monday;
  @FXML
  private Button tuesday;
  @FXML
  private Button wednesday;
  @FXML
  private Button thursday;
  @FXML
  private Button friday;
  @FXML
  private Button saturday;
  @FXML
  private VBox mainBox;
  @FXML
  private Label dayLabel;
  @FXML
  private Label windowLabel;
  private Button selectedButton;
  private Week week;

  /**
   * Update week name controller.
   *
   * @param week the week
   */
  public UpdateWeekNameController(Week week) {
    this.week = week;
  }

  /**
   * Update week name controller.
   */
  public UpdateWeekNameController() {

  }

  /**
   * Initializes update week name controller.
   */
  @FXML
  public void initialize() {
    updateTheme();
    setStyle();
    setAction();

    if (week != null) {
      readWeek();
    } else {
      selectDay(sunday);
    }

    saveButton.setOnAction(e -> updateWeekSettings());
    saveButton.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
  }

  /**
   * Updates the UI with the values of the local variable week
   */
  private void readWeek() {
    switch (week.getStartOfWeek()) {
      case SUNDAY -> selectDay(sunday);
      case MONDAY -> selectDay(monday);
      case TUESDAY -> selectDay(tuesday);
      case WEDNESDAY -> selectDay(wednesday);
      case THURSDAY -> selectDay(thursday);
      case FRIDAY -> selectDay(friday);
      default -> selectDay(saturday);
    }

    if (week.getMaxEvents() != Integer.MAX_VALUE) {
      maxEventsField.setText(String.valueOf(week.getMaxEvents()));
    }

    if (week.getMaxTasks() != Integer.MAX_VALUE) {
      maxTasksField.setText(String.valueOf(week.getMaxTasks()));
    }
    name.setText(week.getName());
  }

  /**
   * Set actions for Task buttons.
   */
  private void setAction() {
    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));
  }

  /**
   * Set styles for Task buttons.
   */
  private void setStyle() {
    sunday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    monday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    tuesday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    wednesday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    thursday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    friday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
    saturday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().saveColor());
  }

  /**
   * Handles select days.
   *
   * @param button the button to select days
   */
  private void selectDay(Button button) {
    if (selectedButton != null) {
      selectedButton.setStyle("-fx-background-color: "
          + PalletManager.palletManager.getCurrentPallet().saveColor());
    }
    selectedButton = button;
    selectedButton.setStyle("-fx-background-color: " + Constants.selectedColor);
  }

  /**
   * Updates week settings.
   */
  private void updateWeekSettings() {
    String newWeekName = name.getText();

    boolean validInput = true;

    if (newWeekName.length() > 0) {
      if (week != null) {
        week.setName(newWeekName);
      }
    } else {
      validInput = false;
      nameLabel.setTextFill(
          Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor()));
    }

    int maxEvents = readMax(maxEventsField, maxEventsLabel);
    if (maxEvents < 0) {
      validInput = false;
    }

    int maxTasks = readMax(maxTasksField, maxTasksLabel);
    if (maxTasks < 0) {
      validInput = false;
    }

    DayOfWeek selectedDay = DayOfWeek.valueOf(selectedButton.getId().toUpperCase());

    if (validInput) {
      if (week != null) {
        updateWeek(selectedDay, maxEvents, maxTasks);
      } else {
        newWeek(newWeekName, maxEvents, maxTasks, selectedDay);
      }

      Stage stage = (Stage) name.getScene().getWindow();
      stage.close();
    }
  }

  /**
   * Updates the week currently being viewed with the given information
   *
   * @param selectedDay the day the week should start on
   * @param maxEvents maximum number of events per day
   * @param maxTasks maximum number of tasks per day
   */
  private void updateWeek(DayOfWeek selectedDay, int maxEvents, int maxTasks) {
    week.updateWeekStart(selectedDay);
    week.setMaxEvents(maxEvents);
    week.setMaxTasks(maxTasks);
    WeekManager.weekManager.run();
  }

  /**
   * Creates and displays a new week with the given information
   *
   * @param newWeekName The name of the new week to create
   * @param maxEvents the max number of events for each day
   * @param maxTasks the max number of tasks for each day
   * @param selectedDay the first day of the week
   */
  private void newWeek(String newWeekName, int maxEvents, int maxTasks, DayOfWeek selectedDay) {
    week = new Week(newWeekName, maxEvents, maxTasks, selectedDay);

    WeekManager.setup(week);
    Stage stage = new Stage();
    stage.setScene(WeekManager.weekManager.getScene());
    stage.show();
  }


  /**
   * Reads max.
   *
   * @param field the text field
   * @param label the label
   * @return the max
   */
  private int readMax(TextField field, Label label) {
    int max = -1;
    if (field.getText().equals("")) {
      max = Integer.MAX_VALUE;
    } else {
      try {
        max = Integer.parseInt(field.getText());
        if (max < 0) {
          label.setTextFill(
              Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor()));
        }
      } catch (Exception ex) {
        label.setTextFill(
            Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor()));
      }
    }

    return max;
  }

  /**
   * Update the theme.
   */
  private void updateTheme() {
    mainBox.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().backgroundColor());
    windowLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    maxEventsLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    maxTasksLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    dayLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    maxEventsLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    maxTasksLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    dayLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());

    nameLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    nameLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());

    saveButton.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    saveButton.setFont(PalletManager.palletManager.getCurrentPallet().textFont());

    updateFieldTheme();
    updateDaysTheme();
  }

  /**
   * Updates the theme of field events
   */
  private void updateFieldTheme() {
    maxEventsField.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    maxEventsField.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());

    maxTasksField.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    maxTasksField.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());

    name.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    name.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());
  }

  /**
   * Updates the GUI theme for the days of the week.
   */
  private void updateDaysTheme() {
    sunday.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    monday.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    tuesday.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    wednesday.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    thursday.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    friday.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    saturday.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    sunday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    monday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    tuesday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    wednesday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    thursday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    friday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    saturday.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
  }
}
