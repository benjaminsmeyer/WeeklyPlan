package cs3500.pa05.view;

import cs3500.pa05.controller.WeekManager;
import javafx.scene.Scene;

/**
 * Week View for User
 */
public class WeekView {
  WeekLoader loader;

  public WeekView(WeekManager controller) {
    this.loader = new WeekLoader(controller);
  }

  /**
   * Returns the scene stored in the FXML file in resources
   *
   * @return the scene stored in the FXML file in resources
   */
  public Scene loadScene() {
    return loader.load();
  }

}
