package cs3500.pa05;

import cs3500.pa05.controller.OpeningWindowController;
import cs3500.pa05.controller.PalletManager;
import cs3500.pa05.view.OpeningWindowViewer;
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

  /**
   * Starts the GUI with the primaryStage.
   *
   * @param primaryStage the primary stage for this application, onto which
   * the application scene can be set.
   * Applications may create other stages, if needed, but they will not be
   * primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    OpeningWindowController updateWeekNameController = new OpeningWindowController();
    OpeningWindowViewer updateWeekNameView = new OpeningWindowViewer(updateWeekNameController);

    PalletManager.setCurrentPallet(PalletManager.defaultPallet);
    primaryStage.setTitle("Week Journal");
    primaryStage.setScene(updateWeekNameView.load());
    primaryStage.show();
  }
}
