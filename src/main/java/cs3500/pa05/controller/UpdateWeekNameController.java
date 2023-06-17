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

    saveButton.setOnAction(e -> updateWeekName());
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

  private void updateWeekName() {
    String newWeekName = name.getText();

    if (newWeekName.length() > 0) {
      week.setName(newWeekName);
      week.setWeekStart(DayOfWeek.valueOf(selectedButton.getId().toUpperCase()));
      WeekManager.weekManager.run();

      Stage stage = (Stage) name.getScene().getWindow();
      stage.close();
    } else {
      nameLabel.setTextFill(Constants.invalidInputLabelColor);
    }
  }
}
