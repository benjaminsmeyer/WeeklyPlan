package cs3500.pa05.controller;

import cs3500.pa05.view.UpdateWeekNameView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class OpeningWindowController {
  @FXML
  private Button newWeek;
  @FXML
  private MenuButton loadWeek;

  @FXML
  public void initialize(){
    newWeek.setOnAction(e -> handleNewWeek());
    //TODO: Set up the loadWeek menu button to read from a directory of saved weeks and allow
    //      the user to select one
    List<File> weeks = new ArrayList<>(); // = FileReader.readAllInDir(someSaveDirectory)
    for (File week : weeks) {
      MenuItem weekToLoad = new MenuItem();
      weekToLoad.setOnAction(e -> handleLoadWeek(week));
      loadWeek.getItems().add(weekToLoad);
    }
  }

  private void handleNewWeek() {
    PalletManager.setCurrentPallet(PalletManager.defaultPallet);
    UpdateWeekNameController updateWeekNameController = new UpdateWeekNameController();
    UpdateWeekNameView updateWeekNameView = new UpdateWeekNameView(updateWeekNameController);

    Stage stage = new Stage();
    stage.setScene(updateWeekNameView.load());
    stage.show();

    Stage thisStage = (Stage) newWeek.getScene().getWindow();
    thisStage.close();
  }

  private void handleLoadWeek(File file) {
    //TODO: implement
  }
}
