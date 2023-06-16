package cs3500.pa05.view;

import cs3500.pa05.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EventBox extends VBox {
  public EventBox(String name, String description, String time) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(Font.font(Constants.weekFont));

    Label descriptionLabel = new Label(description);
    descriptionLabel.setFont(Font.font(Constants.weekFont));

    Label timeLabel = new Label(time);
    timeLabel.setFont(Font.font(Constants.weekFont));

    getChildren().add(nameLabel);
    getChildren().add(descriptionLabel);
    getChildren().add(timeLabel);
    setup();
  }

  public EventBox(String name, String time) {
    Label nameLabel = new Label(name);
    nameLabel.setFont(Font.font(Constants.weekFont));

    Label timeLabel = new Label(time);
    timeLabel.setFont(Font.font(Constants.weekFont));

    getChildren().add(nameLabel);
    getChildren().add(timeLabel);
    setup();
  }

  private void setup() {
    setStyle("-fx-background-color: " + Constants.eventColor);
    setPadding(new Insets(5, 5, 5, 5));
    setAlignment(Pos.TOP_CENTER);
  }
}
