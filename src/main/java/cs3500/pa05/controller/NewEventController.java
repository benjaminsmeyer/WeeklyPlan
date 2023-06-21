package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * New Event Controller.
 */
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
  private Button am;
  @FXML
  private Button pm;

  @FXML
  private HBox buttonBox;
  @FXML
  private VBox mainBox;

  @FXML
  private Label nameLabel;
  @FXML
  private Label descriptionLabel;
  @FXML
  private Label dayLabel;
  @FXML
  private Label timeLabel;
  @FXML
  private Label durationLabel;
  @FXML
  private Label windowLabel;

  @FXML
  private TextField name;
  @FXML
  private TextArea description;
  @FXML
  private TextField hour;
  @FXML
  private TextField minute;
  @FXML
  private TextField duration;

  private Button deleteEvent;
  private Button selectedDay;
  private Button selectedTime;
  private Event event;

  private boolean validInput;
  private boolean validTime;


  /**
   * Initializes a new event controller.
   */
  public NewEventController() {
  }

  /**
   * Initializes a new event controller.
   *
   * @param event the event
   */
  public NewEventController(Event event) {
    this.event = event;
  }

  /**
   * Initializes the GUI.
   */
  @FXML
  public void initialize() {
    updateTheme();

    setStyle();
    setAction();
    setTextSpecifications();

    createEvent.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    createEvent.setOnAction(e -> createEvent());

    if (this.event != null) {
      name.setText(event.getName());
      description.setText(event.getDescription());

      switch (event.getDayOfWeek()) {
        case SUNDAY -> selectDay(sunday);
        case MONDAY -> selectDay(monday);
        case TUESDAY -> selectDay(tuesday);
        case WEDNESDAY -> selectDay(wednesday);
        case THURSDAY -> selectDay(thursday);
        case FRIDAY -> selectDay(friday);
        default -> selectDay(saturday);
      }

      String timeString = Utils.timeToString(event.getStartTime());
      String hourString = timeString.substring(0, timeString.indexOf(":"));
      String minuteString = timeString.substring(timeString.indexOf(":") + 1);

      hour.setText(hourString);
      minute.setText(minuteString);

      if (timeString.endsWith("PM")) {
        selectTime(pm);
      } else {
        selectTime(am);
      }

      deleteEventSpecifications();
      windowLabel.setText("Edit Event");
      createEvent.setText("Save Edits");
      duration.setText(String.valueOf(event.getDuration()));
    }
  }

  /**
   * Set actions for Event buttons.
   */
  private void setAction() {
    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));
    am.setOnAction(e -> selectTime(am));
    pm.setOnAction(e -> selectTime(pm));
  }

  /**
   * Set styles for Event buttons.
   */
  private void setStyle() {
    sunday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    monday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    tuesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    wednesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    thursday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    friday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    saturday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    am.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    pm.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
  }

  /**
   * Set text specifications for Text Fields.
   */
  private void setTextSpecifications() {
    name.setFont(PalletManager.currentPallet.textFont());
    name.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    description.setFont(PalletManager.currentPallet.textFont());
    description.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    hour.setFont(PalletManager.currentPallet.textFont());
    hour.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    minute.setFont(PalletManager.currentPallet.textFont());
    minute.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    duration.setFont(PalletManager.currentPallet.textFont());
    duration.setStyle(
        "-fx-control-inner-background: " + PalletManager.currentPallet.overlayColor());

    hour.textProperty().addListener(e -> handleTime(hour));
    minute.textProperty().addListener(e -> handleTime(minute));
  }

  /**
   * Set specifications for delete button.
   */
  private void deleteEventSpecifications() {
    deleteEvent = new Button("Delete Event");
    deleteEvent.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    deleteEvent.setFont(PalletManager.currentPallet.textFont());
    deleteEvent.setOnAction(e -> removeEvent());
    deleteEvent.setCursor(Cursor.HAND);
    buttonBox.getChildren().add(deleteEvent);
  }

  /**
   * Selects the day.
   *
   * @param button selected day button
   */
  private void selectDay(Button button) {
    if (selectedDay != null) {
      selectedDay.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
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

  /**
   * Selects the time.
   *
   * @param button selected time button
   */
  private void selectTime(Button button) {
    if (selectedTime != null) {
      selectedTime.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    }
    selectedTime = button;
    selectedTime.setStyle("-fx-background-color: " + Constants.taskSelectedColor);
  }

  /**
   * Creates an event.
   */
  private void createEvent() {
    validInput = true;
    String nameText = name.getText();

    if (nameText.equals("")) {
      nameLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
      validInput = false;
    } else {
      nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    }

    String descriptionText = description.getText();

    DayOfWeek dayOfWeek = null;
    if (selectedDay != null) {
      dayOfWeek = DayOfWeek.valueOf(selectedDay.getId().toUpperCase());
      dayLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    } else {
      dayLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
      validInput = false;
    }

    validTime = true;
    boolean pm = false;
    if (selectedTime == null) {
      invalidInput();
    } else {
      pm = selectedTime.getText().equals("PM");
    }

    int timeNum = caculateHour(pm) + calculateMinute();

    if (validTime) {
      timeLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    }

    int durationNum = calculateDuration();

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

  /**
   * Calculates hour for event.
   *
   * @param pm if the hour takes place in pm time
   * @return the hour of the event
   */
  private int caculateHour(boolean pm) {
    String hourText = hour.getText();
    int hourNum = 0;
    try {
      hourNum = Integer.parseInt(hourText);

      if (hourNum > 12 || hourNum < 0) {
        invalidInput();
      } else {
        hourNum *= 60;
        if (pm) {
          hourNum += 720;
        }
      }
    } catch (Exception e) {
      invalidInput();
    }
    return hourNum;
  }

  /**
   * Calculate minutes for event.
   *
   * @return the minute of the event
   */
  private int calculateMinute() {
    String minuteText = minute.getText();
    int minuteNum = 0;
    try {
      minuteNum = Integer.parseInt(minuteText);

      if (minuteNum > 59 || minuteNum < 0) {
        invalidInput();
      }
    } catch (Exception ex) {
      invalidInput();
    }
    return minuteNum;
  }

  /**
   * Calculates the duration of the event.
   *
   * @return the duration of the event
   */
  private int calculateDuration() {
    String durationText = duration.getText();
    int durationNum = 0;
    try {
      durationNum = Integer.parseInt(durationText);
      durationLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    } catch (Exception ex) {
      validInput = false;
      durationLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
    }
    return durationNum;
  }

  /**
   * Responds to invalid input for event creation.
   */
  private void invalidInput() {
    validInput = false;
    validTime = false;
    timeLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
  }

  /**
   * Removes event from GUI.
   */
  private void removeEvent() {
    WeekManager.weekManager.removeActivity(event);
    WeekManager.weekManager.run();

    Stage thisStage = (Stage) createEvent.getScene().getWindow();
    thisStage.close();
  }

  /**
   * Handles the time.
   *
   * @param textField the text field for time
   */
  private void handleTime(TextField textField) {
    if (textField.getText().length() > 2) {
      textField.setText(textField.getText(0, 2));
    }
  }

  /**
   * Updates the GUI theme.
   */
  private void updateTheme() {
    mainBox.setStyle("-fx-background-color: " + PalletManager.currentPallet.backgroundColor());

    windowLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    descriptionLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    dayLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    timeLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    durationLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));

    nameLabel.setFont(PalletManager.currentPallet.textFont());
    descriptionLabel.setFont(PalletManager.currentPallet.textFont());
    dayLabel.setFont(PalletManager.currentPallet.textFont());
    timeLabel.setFont(PalletManager.currentPallet.textFont());
    timeLabel.setFont(PalletManager.currentPallet.textFont());

    updateDaysTheme();

    am.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    am.setFont(PalletManager.currentPallet.textFont());

    pm.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    pm.setFont(PalletManager.currentPallet.textFont());

    createEvent.setFont(PalletManager.currentPallet.textFont());
    createEvent.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
  }

  /**
   * Updates the GUI theme for the days of the week.
   */
  private void updateDaysTheme() {
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
  }
}
