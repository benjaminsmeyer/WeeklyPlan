package cs3500.pa05.view;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.controller.NewEventController;
import cs3500.pa05.controller.PalletManager;
import cs3500.pa05.model.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EventBox extends VBox {
  Event event;
  public EventBox(String name, String description, String time, Event event) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(PalletManager.currentPallet.headerFont());
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    nameLabel.setWrapText(true);

    Label descriptionLabel = new Label(description);
    descriptionLabel.setFont(PalletManager.currentPallet.textFont());
    descriptionLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    descriptionLabel.setOpacity(Constants.descriptionOpacity);
    descriptionLabel.setWrapText(true);

    Label timeLabel = new Label(time);
    timeLabel.setFont(PalletManager.currentPallet.textFont());
    timeLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    timeLabel.setOpacity(Constants.descriptionOpacity);
    timeLabel.setWrapText(true);

    this.event = event;
    getChildren().add(nameLabel);
    getChildren().add(descriptionLabel);
    getChildren().add(timeLabel);
    setup();
  }

  public EventBox(String name, String time, Event event) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(Font.font(Constants.weekFont, FontWeight.SEMI_BOLD,
        FontPosture.REGULAR, 15));
    nameLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    nameLabel.setWrapText(true);


    Label timeLabel = new Label(time);
    timeLabel.setFont(PalletManager.currentPallet.textFont());
    timeLabel.setTextFill(Color.web(PalletManager.currentPallet.validTextColor()));
    timeLabel.setOpacity(Constants.descriptionOpacity);
    timeLabel.setWrapText(true);

    this.event = event;
    getChildren().add(nameLabel);
    getChildren().add(timeLabel);
    setup();
  }

  private void setup() {
    setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
    setPadding(new Insets(5, 5, 5, 5));
    setAlignment(Pos.TOP_CENTER);
    setMaxWidth(Constants.activityWidth);

    setSpacing(10);
    setOnMouseClicked(e -> editEvent());
    Utils.setButtonCursorStatus(this);
  }

  private void editEvent() {
    NewEventController newTaskController = new NewEventController(event);
    NewEventView newTaskView = new NewEventView(newTaskController);

    Stage stage = new Stage();
    stage.setScene(newTaskView.load());
    stage.show();
  }
}
