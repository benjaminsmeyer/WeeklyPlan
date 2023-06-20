package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.view.NewEventView;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * New Event Controller
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
  private HBox buttonBox;
  private Button deleteEvent;
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
  @FXML
  private VBox mainBox;
  private Button selectedDay;
  private Button selectedTime;
  private Event event;

  /**
   * Initializes a new event controller.
   */
  public NewEventController() {}

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

    sunday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    monday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    tuesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    wednesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    thursday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    friday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    saturday.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    am.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    pm.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());

    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));
    am.setOnAction(e -> selectTime(am));
    pm.setOnAction(e -> selectTime(pm));

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

    createEvent.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
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
      String hourString = timeString.substring(0,timeString.indexOf(":"));
      String minuteString = timeString.substring(timeString.indexOf(":")+1, timeString.length());

      hour.setText(hourString);
      minute.setText(minuteString);

      if (timeString.endsWith("PM")) {
        selectTime(pm);
      } else {
        selectTime(am);
      }

      deleteEvent = new Button("Delete Event");
      deleteEvent.setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
      deleteEvent.setFont(PalletManager.currentPallet.textFont());
      deleteEvent.setOnAction(e -> removeEvent());
      deleteEvent.setCursor(Cursor.HAND);
      buttonBox.getChildren().add(deleteEvent);

      windowLabel.setText("Edit Event");
      createEvent.setText("Save Edits");
      duration.setText("" + event.getDuration());
    }
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
    boolean validInput = true;
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


    boolean validTime = true;
    boolean pm = false;
    if (selectedTime == null) {
      validInput = false;
      validTime = false;
      timeLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
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
        timeLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
      } else {
        hourNum *= 60;
        if (pm) {
          hourNum += 720;
        }
      }
    } catch (Exception e) {
      validInput = false;
      validTime = false;
      timeLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
    }

    String minuteText = minute.getText();
    int minuteNum = 0;
    try {
      minuteNum = Integer.parseInt(minuteText);

      if (minuteNum > 59 || minuteNum < 0) {
        validInput = false;
        validTime = false;
        timeLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
      }
    } catch (Exception ex) {
      validInput = false;
      validTime = false;
      timeLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
    }

    int timeNum = hourNum + minuteNum;

    if (validTime) {
      timeLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    }

    String durationText = duration.getText();
    int durationNum = 0;
    try {
      durationNum = Integer.parseInt(durationText);
      durationLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    } catch (Exception ex) {
      validInput = false;
      durationLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
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

  /**
   * Removes the event.
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

    am.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    pm.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    am.setFont(PalletManager.currentPallet.textFont());
    pm.setFont(PalletManager.currentPallet.textFont());

    createEvent.setFont(PalletManager.currentPallet.textFont());
    createEvent.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
  }
}
