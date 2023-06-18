package cs3500.pa05.view;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.controller.NewTaskController;
import cs3500.pa05.controller.PalletManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    nameLabel.setFont(PalletManager.currentPallet.headerFont());
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    nameLabel.setWrapText(true);
    this.task = task;

    getChildren().add(nameLabel);
    setup();
  }

  public TaskBox(String name, String description, Task task) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(PalletManager.currentPallet.headerFont());
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    nameLabel.setWrapText(true);

    Label descriptionLabel = new Label(description);
    descriptionLabel.setFont(PalletManager.currentPallet.textFont());
    descriptionLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    descriptionLabel.setOpacity(Constants.descriptionOpacity);
    descriptionLabel.setWrapText(true);
    descriptionLabel.setWrapText(true);
    this.task = task;

    getChildren().add(nameLabel);
    getChildren().add(descriptionLabel);
    setup();
  }

  private void setup() {
    setStyle("-fx-background-color: " + PalletManager.currentPallet.taskColor());
    if (task.isDone()){
      setOpacity(.6);
      Label completeLabel = new Label("Complete");
      completeLabel.setFont(PalletManager.currentPallet.textFont());
      completeLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
      completeLabel.setOpacity(Constants.descriptionOpacity);
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
