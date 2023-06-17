package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.Week;
import java.lang.module.Configuration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateWeekNameController {
  @FXML
  private Button saveButton;
  @FXML
  private Label nameLabel;
  @FXML
  private TextField name;
  private Week week;
  UpdateWeekNameController(Week week) {
    this.week = week;
  }

  @FXML
  public void initialize() {
    saveButton.setOnAction(e -> updateWeekName());
    saveButton.setStyle("-fx-background-color: " + Constants.saveColor);
    name.setText(week.getName());
  }

  private void updateWeekName() {
    String newWeekName = name.getText();

    if (newWeekName.length() > 0) {
      week.setName(newWeekName);
      WeekManager.weekManager.run();

      Stage stage = (Stage) name.getScene().getWindow();
      stage.close();
    } else {
      nameLabel.setTextFill(Constants.invalidInputLabelColor);
    }
  }
}
