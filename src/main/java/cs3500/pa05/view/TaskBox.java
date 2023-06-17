package cs3500.pa05.view;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.controller.NewTaskController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import cs3500.pa05.model.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TaskBox extends VBox {
  Task task;

  public TaskBox(String name, Task task) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(Font.font(Constants.weekFont, FontWeight.SEMI_BOLD,
        FontPosture.REGULAR, 15));
    nameLabel.setTextFill(Color.web(Constants.activityNameColor));
    nameLabel.setWrapText(true);
    this.task = task;

    getChildren().add(nameLabel);
    setup();
  }

  public TaskBox(String name, String description, Task task) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(Font.font(Constants.weekFont, FontWeight.SEMI_BOLD,
        FontPosture.REGULAR, 15));
    nameLabel.setTextFill(Color.web(Constants.activityNameColor));
    nameLabel.setWrapText(true);

    Label descriptionLabel = new Label(description);
    descriptionLabel.setFont(Font.font(Constants.weekFont, FontWeight.NORMAL,
        FontPosture.REGULAR, 10));
    descriptionLabel.setTextFill(Color.web(Constants.activityDescrptionColor));
    descriptionLabel.setWrapText(true);
    descriptionLabel.setWrapText(true);
    this.task = task;

    getChildren().add(nameLabel);
    getChildren().add(descriptionLabel);
    setup();
  }

  private void setup() {
    setStyle("-fx-background-color: " + Constants.taskColor);
    if (task.isDone()){
      setOpacity(.6);
      Label completeLabel = new Label("Complete");
      completeLabel.setFont(Font.font(Constants.weekFont, FontWeight.NORMAL,
          FontPosture.REGULAR, 10));
      completeLabel.setTextFill(Color.web(Constants.activityDescrptionColor));
      completeLabel.setWrapText(true);
      getChildren().add(completeLabel);
    }

    setSpacing(10);
    Utils.setButtonCursorStatus(this);
    setOnMouseClicked(e -> editTask());
    setPadding(new Insets(5, 5, 5, 5));
    setAlignment(Pos.TOP_CENTER);
    setMaxWidth(Constants.activityWidth);
  }

  private void editTask() {
    NewTaskController newTaskController = new NewTaskController(task);
    NewTaskView newTaskView = new NewTaskView(newTaskController);

    Stage stage = new Stage();
    stage.setScene(newTaskView.load());
    stage.show();
  }
}
