package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
  private Button selectedButton;
  private Week week;
  UpdateWeekNameController(Week week) {
    this.week = week;
  }

  @FXML
  public void initialize() {
    sunday.setStyle("-fx-background-color: " + Constants.saveColor);
    monday.setStyle("-fx-background-color: " + Constants.saveColor);
    tuesday.setStyle("-fx-background-color: " + Constants.saveColor);
    wednesday.setStyle("-fx-background-color: " + Constants.saveColor);
    thursday.setStyle("-fx-background-color: " + Constants.saveColor);
    friday.setStyle("-fx-background-color: " + Constants.saveColor);
    saturday.setStyle("-fx-background-color: " + Constants.saveColor);

    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));

    switch(week.getStartOfWeek()){
      case SUNDAY -> selectDay(sunday);
      case MONDAY -> selectDay(monday);
      case TUESDAY -> selectDay(tuesday);
      case WEDNESDAY -> selectDay(wednesday);
      case THURSDAY -> selectDay(thursday);
      case FRIDAY -> selectDay(friday);
      case SATURDAY -> selectDay(saturday);
    }

    if (week.getMaxEvents() != Integer.MAX_VALUE) {
      maxEventsField.setText("" + week.getMaxEvents());
    }

    if (week.getMaxTasks() != Integer.MAX_VALUE) {
      maxTasksField.setText("" + week.getMaxTasks());
    }

    saveButton.setOnAction(e -> updateWeekSettings());
    saveButton.setStyle("-fx-background-color: " + Constants.saveColor);
    name.setText(week.getName());
  }

  private void selectDay(Button button) {
    if (selectedButton != null) {
      selectedButton.setStyle("-fx-background-color: " + Constants.saveColor);
    }
    selectedButton = button;
    selectedButton.setStyle("-fx-background-color: " + Constants.taskSelectedColor);


    // If you can select multiple days, uncomment this
    /*
    if (isButtonSelected(button)) {
      button.setStyle("-fx-background-color: " + Constants.taskColor);
    } else {
      button.setStyle("-fx-background-color: " + Constants.taskSelectedColor);
    } */
  }

  private void updateWeekSettings() {
    String newWeekName = name.getText();

    boolean validInput = true;

    if (newWeekName.length() > 0) {
      week.setName(newWeekName);
    } else {
      validInput = false;
      nameLabel.setTextFill(Constants.invalidInputLabelColor);
    }

    int maxEvents = readMax(maxEventsField, maxEventsLabel);
    if (maxEvents < 0) {
      validInput = false;
    }

    int maxTasks = readMax(maxTasksField, maxTasksLabel);
    if (maxTasks < 0) {
      validInput = false;
    }

    if (validInput) {
      week.updateWeekStart(DayOfWeek.valueOf(selectedButton.getId().toUpperCase()));
      week.setMaxEvents(maxEvents);
      week.setMaxTasks(maxTasks);
      WeekManager.weekManager.run();

      Stage stage = (Stage) name.getScene().getWindow();
      stage.close();
    }
  }

  private int readMax(TextField field, Label label) {
    int max = -1;
    if (field.getText().equals("")) {
      max = Integer.MAX_VALUE;
    } else {
      try {
        max = Integer.parseInt(field.getText());
        if (max < 0) {
          label.setTextFill(Constants.invalidInputLabelColor);
        }
      } catch (Exception ex) {
        label.setTextFill(Constants.invalidInputLabelColor);
      }
    }

    return max;
  }
}
