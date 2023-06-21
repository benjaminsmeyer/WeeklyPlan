package cs3500.pa05.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Handles the scene loader.
 */
public abstract class SceneLoader {
  private final FXMLLoader loader;

  /**
   * Initializes the scene loader.
   *
   * @param controller the controller
   * @param fxml       the fxml for the gui
   */
  public SceneLoader(Object controller, String fxml) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxml));

    this.loader.setController(controller);
  }

  /**
   * Loads the scene.
   *
   * @return the scene
   */
  public Scene load() {
    try {
      return loader.load();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
