package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.WeekView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
  @FXML
  private VBox mainBox;
  @FXML
  private Label dayLabel;
  @FXML
  private Label windowLabel;
  private Button selectedButton;
  private Week week;
  public UpdateWeekNameController(Week week) {
    this.week = week;
  }

  public UpdateWeekNameController() {

  }

  @FXML
  public void initialize() {
    updateTheme();

    sunday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
    monday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
    tuesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
    wednesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
    thursday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
    friday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
    saturday.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());

    maxEventsField.setFont(PalletManager.currentPallet.textFont());
    maxEventsField.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    maxTasksField.setFont(PalletManager.currentPallet.textFont());
    maxTasksField.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    name.setFont(PalletManager.currentPallet.textFont());
    name.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));

    if (week != null) {
      switch (week.getStartOfWeek()) {
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
      name.setText(week.getName());
    } else {
      selectDay(sunday);
    }

    saveButton.setOnAction(e -> updateWeekSettings());
    saveButton.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
  }

  private void selectDay(Button button) {
    if (selectedButton != null) {
      selectedButton.setStyle("-fx-background-color: " + PalletManager.currentPallet.saveColor());
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
      if (week != null) {
        week.setName(newWeekName);
      }
    } else {
      validInput = false;
      nameLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
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
        week.updateWeekStart(selectedDay);
        week.setMaxEvents(maxEvents);
        week.setMaxTasks(maxTasks);
        WeekManager.weekManager.run();
      } else {
        week = new Week(newWeekName, maxEvents, maxTasks, selectedDay);

        WeekManager.setup(week);
        Stage stage = new Stage();
        stage.setScene(WeekManager.weekManager.getScene());
        stage.show();
      }

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
          label.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
        }
      } catch (Exception ex) {
        label.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
      }
    }

    return max;
  }

  private void updateTheme() {
    mainBox.setStyle("-fx-background-color: " + PalletManager.currentPallet.backgroundColor());
    windowLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    maxEventsLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    maxTasksLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    dayLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));

    maxEventsLabel.setFont(PalletManager.currentPallet.textFont());
    maxTasksLabel.setFont(PalletManager.currentPallet.textFont());
    dayLabel.setFont(PalletManager.currentPallet.textFont());

    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    nameLabel.setFont(PalletManager.currentPallet.textFont());

    sunday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    monday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    tuesday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    wednesday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    thursday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    friday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    saturday.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));

    sunday.setFont(PalletManager.currentPallet.textFont());
    monday.setFont(PalletManager.currentPallet.textFont());
    tuesday.setFont(PalletManager.currentPallet.textFont());
    wednesday.setFont(PalletManager.currentPallet.textFont());
    thursday.setFont(PalletManager.currentPallet.textFont());
    friday.setFont(PalletManager.currentPallet.textFont());
    saturday.setFont(PalletManager.currentPallet.textFont());

    saveButton.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    saveButton.setFont(PalletManager.currentPallet.textFont());
  }
}
