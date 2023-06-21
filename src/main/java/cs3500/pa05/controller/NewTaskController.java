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

    windowLabel.setTextFill(Paint.valueOf(PalletManager.currentPallet.validTextColor()));
    windowLabel.setTextFill(Paint.valueOf(PalletManager.currentPallet.validTextColor()));

    setStyle();
    setAction();
    setTextSpecifications();

    createTask.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    createTask.setOnAction(e -> createTask());

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
  }

  /**
   * Set styles for Task buttons.
   */
  private void setStyle() {
    sunday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    monday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    tuesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    wednesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    thursday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    friday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    saturday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
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
  }

  /**
   * Set specifications for delete button.
   */
  private void deleteTaskSpecifications() {
    deleteTask = new Button("Delete Event");
    deleteTask.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    deleteTask.setFont(PalletManager.currentPallet.textFont());
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
      selectedButton.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    }
    selectedButton = button;
    selectedButton.setStyle("-fx-background-color: " + Constants.taskSelectedColor);


    // If you can select multiple days, uncomment this
    /*
    if (isButtonSelected(button)) {
      button.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    } else {
      button.setStyle("-fx-background-color: " + Constants.taskSelectedColor);
    } */
  }

  /**
   * Creates a new task.
   */
  private void createTask() {
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
    if (selectedButton != null) {
      dayOfWeek = DayOfWeek.valueOf(selectedButton.getId().toUpperCase());
      dayLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    } else {
      dayLabel.setTextFill(Color.web(PalletManager.currentPallet.invalidTextColor()));
      validInput = false;
    }

    if (validInput) {
      if (task == null) {
        Task newTask = new Task(nameText, descriptionText, dayOfWeek, complete.isSelected());
        WeekManager.weekManager.addActivity(newTask);
      } else {
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

      Stage thisStage = (Stage) createTask.getScene().getWindow();
      thisStage.close();
    }
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
    mainBox.setStyle("-fx-background-color: " + PalletManager.currentPallet.backgroundColor());
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    descriptionLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    dayLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));

    nameLabel.setFont(PalletManager.currentPallet.textFont());
    descriptionLabel.setFont(PalletManager.currentPallet.textFont());
    dayLabel.setFont(PalletManager.currentPallet.textFont());

    updateDaysTheme();

    createTask.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    createTask.setFont(PalletManager.currentPallet.textFont());
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
    complete.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));

    sunday.setFont(PalletManager.currentPallet.textFont());
    monday.setFont(PalletManager.currentPallet.textFont());
    tuesday.setFont(PalletManager.currentPallet.textFont());
    wednesday.setFont(PalletManager.currentPallet.textFont());
    thursday.setFont(PalletManager.currentPallet.textFont());
    friday.setFont(PalletManager.currentPallet.textFont());
    saturday.setFont(PalletManager.currentPallet.textFont());
  }
}
