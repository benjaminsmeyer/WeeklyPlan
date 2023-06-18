package cs3500.pa05;

import cs3500.pa05.controller.PalletManager;
import cs3500.pa05.controller.WeekManager;
import cs3500.pa05.model.Week;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs the weekly planner
 */
public class Driver extends Application {
  /**
   * Runs the program
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Week week = new Week();
    WeekManager.setup(week);

    PalletManager.setCurrentPallet(PalletManager.defaultPallet);
    primaryStage.setTitle("Week Journal");
    primaryStage.setScene(WeekManager.weekManager.getScene());
    primaryStage.show();
  }
}
