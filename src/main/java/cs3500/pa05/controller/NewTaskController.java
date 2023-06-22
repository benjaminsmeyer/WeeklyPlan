package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * New Task Controller.
 */
public class NewTaskController {
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
  private Button createTask;
  @FXML
  private HBox buttonBox;
  @FXML
  private VBox mainBox;
  @FXML
  private CheckBox complete;
  @FXML
  private Label nameLabel;
  @FXML
  private Label descriptionLabel;
  @FXML
  private Label dayLabel;
  @FXML
  private Label windowLabel;
  @FXML
  private TextArea description;
  @FXML
  private TextField name;
  private Button selectedButton;
  private Button deleteTask;
  private Task task;

  /**
   * New Task Controller.
   */
  public NewTaskController() {
  }

  /**
   * New Task Controller.
   *
   * @param task the task of controller
   */
  public NewTaskController(Task task) {
    this.task = task;
  }

  /**
   * Initializes the task.
   */
  @FXML
  public void initialize() {
    updateTheme();
    setStyle();
    setAction();
    setTextSpecifications();

    if (task != null) {
      name.setText(task.getName());
      description.setText(task.getDescription());

      switch (task.getDayOfWeek()) {
        case SUNDAY -> selectDay(sunday);
        case MONDAY -> selectDay(monday);
        case TUESDAY -> selectDay(tuesday);
        case WEDNESDAY -> selectDay(wednesday);
        case THURSDAY -> selectDay(thursday);
        case FRIDAY -> selectDay(friday);
        default -> selectDay(saturday);
      }

      complete.setSelected(task.isDone());
      deleteTaskSpecifications();

      windowLabel.setText("Edit Task");
      deleteTask.setText("Delete Event");
      createTask.setText("Save Edits");
    }
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

    createTask.setOnAction(e -> createTask());
  }

  /**
   * Set styles for Task buttons.
   */
  private void setStyle() {
    sunday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    monday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    tuesday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    wednesday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    thursday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    friday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    saturday.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());
    createTask.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().taskColor());

  }

  /**
   * Set text specifications for Text Fields.
   */
  private void setTextSpecifications() {
    name.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    name.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());

    description.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    description.setStyle(
        "-fx-control-inner-background: "
            + PalletManager.palletManager.getCurrentPallet().overlayColor());
  }

  /**
   * Set specifications for delete button.
   */
  private void deleteTaskSpecifications() {
    deleteTask = new Button("Delete Event");
    deleteTask.setStyle(
        "-fx-background-color: " + PalletManager.palletManager.getCurrentPallet().taskColor());
    deleteTask.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    deleteTask.setOnAction(e -> removeTask());
    deleteTask.setCursor(Cursor.HAND);
    buttonBox.getChildren().add(deleteTask);
  }

  /**
   * Handles the selected days.
   *
   * @param button the select button day
   */
  private void selectDay(Button button) {
    if (selectedButton != null) {
      selectedButton.setStyle("-fx-background-color: "
          + PalletManager.palletManager.getCurrentPallet().taskColor());
    }
    selectedButton = button;
    selectedButton.setStyle("-fx-background-color: " + Constants.selectedColor);
  }

  /**
   * Creates a new task.
   */
  private void createTask() {
    boolean validInput = true;

    String nameText = name.getText();
    if (nameText.equals("")) {
      nameLabel.setTextFill(
          Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor()));
      validInput = false;
    } else {
      nameLabel.setTextFill(
          Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    }

    String descriptionText = description.getText();

    DayOfWeek dayOfWeek = null;
    if (selectedButton != null) {
      dayOfWeek = DayOfWeek.valueOf(selectedButton.getId().toUpperCase());
      dayLabel.setTextFill(
          Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    } else {
      dayLabel.setTextFill(
          Color.web(PalletManager.palletManager.getCurrentPallet().invalidTextColor()));
      validInput = false;
    }

    if (validInput) {
      if (task == null) {
        newTask(nameText, descriptionText, dayOfWeek);
      } else {
        editTask(nameText, descriptionText, dayOfWeek);
      }

      Stage thisStage = (Stage) createTask.getScene().getWindow();
      thisStage.close();
    }
  }

  /**
   * Creates and renders a new theme
   *
   * @param nameText The name of the Task to create
   * @param descriptionText The description of the Task to create
   * @param dayOfWeek the Day of the week the Task to create takes place on
   */
  private void newTask(String nameText, String descriptionText, DayOfWeek dayOfWeek) {
    Task newTask = new Task(nameText, descriptionText, dayOfWeek, complete.isSelected());
    WeekManager.weekManager.addActivity(newTask);
  }

  /**
   * Edits the instance variable task with the given information and renders those changes
   *
   * @param nameText the Name of the Task to create
   * @param descriptionText The description of the Task to create
   * @param dayOfWeek the Day of the week the Task to create takes place on
   */
  private void editTask(String nameText, String descriptionText, DayOfWeek dayOfWeek) {
    task.setName(nameText);
    task.setDescription(descriptionText);
    task.setDayOfWeek(dayOfWeek);
    if (complete.isSelected()) {
      task.complete();
    } else {
      task.incomplete();
    }
    WeekManager.weekManager.run();
  }

  /**
   * Removes task from GUI.
   */
  private void removeTask() {
    WeekManager.weekManager.removeActivity(task);
    WeekManager.weekManager.run();

    Stage thisStage = (Stage) createTask.getScene().getWindow();
    thisStage.close();
  }

  /**
   * Updates the theme.
   */
  private void updateTheme() {
    windowLabel.setTextFill(
        Paint.valueOf(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    windowLabel.setTextFill(
        Paint.valueOf(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    mainBox.setStyle("-fx-background-color: "
        + PalletManager.palletManager.getCurrentPallet().backgroundColor());
    nameLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    descriptionLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    dayLabel.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));

    nameLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    descriptionLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    dayLabel.setFont(PalletManager.palletManager.getCurrentPallet().textFont());

    updateDaysTheme();

    createTask.setTextFill(
        Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    createTask.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
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
    complete.setTextFill(
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
