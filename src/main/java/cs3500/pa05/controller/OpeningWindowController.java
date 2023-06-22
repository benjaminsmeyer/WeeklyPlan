package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.FileRead;
import cs3500.pa05.view.OpeningWindowViewer;
import cs3500.pa05.view.SplashView;
import cs3500.pa05.view.UpdateWeekNameView;
import java.io.File;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    newWeek.setCursor(Cursor.HAND);
    loadWeek.setCursor(Cursor.HAND);

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
    PalletManager.setupPalletManager();
    UpdateWeekNameController updateWeekNameController = new UpdateWeekNameController();
    UpdateWeekNameView updateWeekNameView = new UpdateWeekNameView(updateWeekNameController);

    splashScreen(() -> {
      Stage stage = new Stage();
      stage.setScene(updateWeekNameView.load());
      stage.show(); });

    Stage thisStage = (Stage) newWeek.getScene().getWindow();
    thisStage.close();
  }

  /**
   * Handles load week.
   *
   * @param file the file to load
   */
  private void handleLoadWeek(String file) {
    PalletManager.setupPalletManager();
    FileRead reader = new FileRead();
    reader.openFile(new File(Constants.weekPath + file));
    WeekManager.setup(reader.readFile());

    splashScreen(() -> {
      Stage stage = new Stage();
      stage.setScene(WeekManager.weekManager.getScene());
      stage.show(); });
    Stage thisStage = (Stage) newWeek.getScene().getWindow();
    thisStage.close();
  }


  /**
   * Opens a splash screen for the application
   */
  private void splashScreen(Runnable onFinish) {
    Stage splash = new Stage();
    SplashView splashView = new SplashView();

    Scene splashScene = splashView.load();

    splash.setScene(splashScene);
    splash.show();

    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1),
        splashScene.getRoot().getChildrenUnmodifiable().get(0));
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    fadeIn.setCycleCount(1);

    FadeTransition fadeOut = new FadeTransition(Duration.seconds(1),
        splashScene.getRoot().getChildrenUnmodifiable().get(0));
    fadeOut.setFromValue(1);
    fadeOut.setToValue(1);
    fadeOut.setCycleCount(1);

    fadeOut.setOnFinished(e -> {
      splash.close();
      onFinish.run(); });

    fadeIn.setOnFinished(e -> fadeOut.play());
    fadeIn.play();
  }
}
