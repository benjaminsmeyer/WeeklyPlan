package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.NewTaskView;
import javafx.fxml.FXML;
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
  private Button deleteTask;
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
  private CheckBox complete;
  @FXML
  private Label windowLabel;
  @FXML
  private VBox mainBox;
  private Button selectedButton;
  private Task task;

  public NewTaskController() {

  }

  public NewTaskController(Task task) {
    this.task = task;
  }

  @FXML
  public void initialize(){
    updateTheme();

    windowLabel.setTextFill(Paint.valueOf(PalletManager.currentPallet.validTextColor()));
    windowLabel.setTextFill(Paint.valueOf(PalletManager.currentPallet.validTextColor()));

    sunday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    monday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    tuesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    wednesday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    thursday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    friday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    saturday.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());

    sunday.setOnAction(e -> selectDay(sunday));
    monday.setOnAction(e -> selectDay(monday));
    tuesday.setOnAction(e -> selectDay(tuesday));
    wednesday.setOnAction(e -> selectDay(wednesday));
    thursday.setOnAction(e -> selectDay(thursday));
    friday.setOnAction(e -> selectDay(friday));
    saturday.setOnAction(e -> selectDay(saturday));

    createTask.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    createTask.setOnAction(e -> createTask());
    deleteTask.setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    deleteTask.setOnAction(e -> removeTask());



    if (task != null) {
      name.setText(task.getName());
      description.setText(task.getDescription());

      switch(task.getDayOfWeek()){
        case SUNDAY -> selectDay(sunday);
        case MONDAY -> selectDay(monday);
        case TUESDAY -> selectDay(tuesday);
        case WEDNESDAY -> selectDay(wednesday);
        case THURSDAY -> selectDay(thursday);
        case FRIDAY -> selectDay(friday);
        case SATURDAY -> selectDay(saturday);
      }

      complete.setSelected(task.isDone());

      windowLabel.setText("Edit Task");
      deleteTask.setText("Delete Event");
      createTask.setText("Save Edits");
    }
  }

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

  private void removeTask() {
    WeekManager.weekManager.removeActivity(task);
    WeekManager.weekManager.run();
  }

  private void updateTheme() {
    mainBox.setStyle("-fx-background-color: " + PalletManager.currentPallet.backgroundColor());
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    descriptionLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    dayLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    
    nameLabel.setFont(PalletManager.currentPallet.textFont());
    descriptionLabel.setFont(PalletManager.currentPallet.textFont());
    dayLabel.setFont(PalletManager.currentPallet.textFont());

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

    createTask.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    createTask.setFont(PalletManager.currentPallet.textFont());
  }
}
