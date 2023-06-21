package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.FileRead;
import cs3500.pa05.view.UpdateWeekNameView;
import java.io.File;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Opening window controller.
 */
public class OpeningWindowController {
  @FXML
  private Button newWeek;
  @FXML
  private MenuButton loadWeek;

  /**
   * Initializes the opening window controller.
   */
  @FXML
  public void initialize() {
    newWeek.setOnAction(e -> handleNewWeek());
    //TODO: Set up the loadWeek menu button to read from a directory of saved weeks and allow
    //      the user to select one

    List<String> weeks = FileRead.getAllBujoFiles();
    for (String week : weeks) {
      MenuItem weekToLoad = new MenuItem(week.substring(0, week.indexOf(".bujo")));
      weekToLoad.setOnAction(e -> handleLoadWeek(week));
      loadWeek.getItems().add(weekToLoad);
    }
  }

  /**
   * Handles new week.
   */
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

  /**
   * Handles load week.
   *
   * @param file the file to load
   */
  private void handleLoadWeek(String file) {
    PalletManager.setCurrentPallet(PalletManager.defaultPallet);
    FileRead reader = new FileRead();
    reader.openFile(new File(Constants.weekPath + file));
    WeekManager.setup(reader.readFile());

    Stage stage = new Stage();
    stage.setScene(WeekManager.weekManager.getScene());
    stage.show();

    Stage thisStage = (Stage) newWeek.getScene().getWindow();
    thisStage.close();
  }
}
