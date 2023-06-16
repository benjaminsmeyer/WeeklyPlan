package cs3500.pa05.view;

import cs3500.pa05.Constants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TaskBox extends VBox {
  public TaskBox(String name, boolean isDone) {
    CheckBox nameLabel = new CheckBox(name);
    nameLabel.setFont(Font.font(Constants.weekFont));
    nameLabel.setSelected(isDone);
    nameLabel.setWrapText(true);

    getChildren().add(nameLabel);
    setup();
  }

  public TaskBox(String name, String description, boolean isDone) {
    CheckBox nameLabel = new CheckBox(name);
    nameLabel.setFont(Font.font(Constants.weekFont));
    nameLabel.setSelected(isDone);
    nameLabel.setWrapText(true);

    Label descriptionLabel = new Label(description);
    descriptionLabel.setFont(Font.font(Constants.weekFont));
    descriptionLabel.setWrapText(true);

    getChildren().add(nameLabel);
    getChildren().add(descriptionLabel);
    setup();
  }

  private void setup() {
    setStyle("-fx-background-color: " + Constants.taskColor);
    setPadding(new Insets(5, 5, 5, 5));
    setAlignment(Pos.TOP_CENTER);
    setMaxWidth(Constants.activityWidth);
  }
}
