package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.NewEventView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class NewEventController {
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
  private Button createEvent;
  @FXML
  private Label nameLabel;
  @FXML
  private TextField name;
  @FXML
  private Label descriptionLabel;
  @FXML
  private TextArea description;
  @FXML
  private Label dayLabel;
  @FXML
  private TextField hour;
  @FXML
  private TextField minute;
  @FXML
  private Label timeLabel;
  @FXML
  private TextField duration;
  @FXML
  private Label durationLabel;
  @FXML
  private Button am;
  @FXML
  private Button pm;
  @FXML
  private Label windowLabel;
  private Button selectedDay;
  private Button selectedTime;
  private Event event;

  public NewEventController() {

  }

  public NewEventController(Event event) {
    this.event = event;
  }

  @FXML
  public void initialize() {
    sunday.setStyle("-fx-background-color: " + Constants.eventColor);
    monday.setStyle("-fx-background-color: " + Constants.eventColor);
    tuesday.setStyle("-fx-background-color: " + Constants.eventColor);
    wednesday.setStyle("-fx-background-color: " + Constants.eventColor);
    thursday.setStyle("-fx-background-color: " + Constants.eventColor);
    friday.setStyle("-fx-background-color: " + Constants.eventColor);
    saturday.setStyle("-fx-background-color: " + Constants.eventColor);
    am.setStyle("-fx-background-color: " + Constants.eventColor);
    pm.setStyle("-fx-background-color: " + Constants.eventColor);

    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));
    am.setOnAction(e -> selectTime(am));
    pm.setOnAction(e -> selectTime(pm));

    createEvent.setStyle("-fx-background-color: " + Constants.eventColor);
    createEvent.setOnAction(e -> createEvent());

    hour.textProperty().addListener(e -> handleTime(hour));
    minute.textProperty().addListener(e -> handleTime(minute));

    if (this.event != null) {
      name.setText(event.getName());
      description.setText(event.getDescription());

      switch(event.getDayOfWeek()){
        case SUNDAY -> selectDay(sunday);
        case MONDAY -> selectDay(monday);
        case TUESDAY -> selectDay(tuesday);
        case WEDNESDAY -> selectDay(wednesday);
        case THURSDAY -> selectDay(thursday);
        case FRIDAY -> selectDay(friday);
        case SATURDAY -> selectDay(saturday);
      }

      String timeString = Utils.timeToString(event.getStartTime());
      String hourString = timeString.substring(0,2);
      String minuteString = timeString.substring(3, 5);

      hour.setText(hourString);
      minute.setText(minuteString);

      if (timeString.endsWith("PM")) {
        selectTime(pm);
      } else {
        selectTime(am);
      }

      windowLabel.setText("Edit Event");
      createEvent.setText("Save Edits");
      duration.setText("" + event.getDuration());
    }
  }

  private void selectDay(Button button) {
    if (selectedDay != null) {
      selectedDay.setStyle("-fx-background-color: " + Constants.eventColor);
    }
    selectedDay = button;
    selectedDay.setStyle("-fx-background-color: " + Constants.taskSelectedColor);


    // If you can select multiple days, uncomment this
    /*
    if (isButtonSelected(button)) {
      button.setStyle("-fx-background-color: " + Constants.taskColor);
    } else {
      button.setStyle("-fx-background-color: " + Constants.taskSelectedColor);
    } */
  }

  private void selectTime(Button button) {
    if (selectedTime != null) {
      selectedTime.setStyle("-fx-background-color: " + Constants.eventColor);
    }
    selectedTime = button;
    selectedTime.setStyle("-fx-background-color: " + Constants.taskSelectedColor);
  }

  private void createEvent() {
    boolean validInput = true;
    String nameText = name.getText();

    if (nameText.equals("")) {
      nameLabel.setTextFill(Constants.invalidInputLabelColor);
      validInput = false;
    } else {
      nameLabel.setTextFill(Constants.validInputLabelColor);
    }

    String descriptionText = description.getText();

    DayOfWeek dayOfWeek = null;
    if (selectedDay != null) {
      dayOfWeek = DayOfWeek.valueOf(selectedDay.getId().toUpperCase());
      dayLabel.setTextFill(Constants.validInputLabelColor);
    } else {
      dayLabel.setTextFill(Constants.invalidInputLabelColor);
      validInput = false;
    }


    boolean validTime = true;
    boolean pm = false;
    if (selectedTime == null) {
      validInput = false;
      validTime = false;
      timeLabel.setTextFill(Constants.invalidInputLabelColor);
    } else {
      pm = selectedTime.getText().equals("PM");
    }

    String hourText = hour.getText();
    int hourNum = 0;
    try {
      hourNum = Integer.parseInt(hourText);

      if (hourNum > 12 || hourNum < 0) {
        validInput = false;
        validTime = false;
        timeLabel.setTextFill(Constants.invalidInputLabelColor);
      } else {
        hourNum *= 60;
        if (pm) {
          hourNum += 720;
        }
      }
    } catch (Exception e) {
      validInput = false;
      validTime = false;
      timeLabel.setTextFill(Constants.invalidInputLabelColor);
    }

    String minuteText = minute.getText();
    int minuteNum = 0;
    try {
      minuteNum = Integer.parseInt(minuteText);

      if (minuteNum > 59 || minuteNum < 0) {
        validInput = false;
        validTime = false;
        timeLabel.setTextFill(Constants.invalidInputLabelColor);
      }
    } catch (Exception ex) {
      validInput = false;
      validTime = false;
      timeLabel.setTextFill(Constants.invalidInputLabelColor);
    }

    int timeNum = hourNum + minuteNum;

    if (validTime) {
      timeLabel.setTextFill(Constants.validInputLabelColor);
    }

    String durationText = duration.getText();
    int durationNum = 0;
    try {
      durationNum = Integer.parseInt(durationText);
      durationLabel.setTextFill(Constants.validInputLabelColor);
    } catch (Exception ex) {
      validInput = false;
      durationLabel.setTextFill(Constants.invalidInputLabelColor);
    }

    if (validInput) {
      if (event == null) {
        Event newTask = new Event(nameText, descriptionText, dayOfWeek, timeNum, durationNum);
        WeekManager.weekManager.addActivity(newTask);
      } else {
        event.setName(nameText);
        event.setDescription(descriptionText);
        event.setDayOfWeek(dayOfWeek);
        event.setStartTime(timeNum);
        event.setDuration(durationNum);
        WeekManager.weekManager.run();
      }

      Stage thisStage = (Stage) createEvent.getScene().getWindow();
      thisStage.close();
    }
  }

  private void handleTime(TextField textField) {
    if (textField.getText().length() > 2) {
      textField.setText(textField.getText(0, 2));
    }

  }
}
