package cs3500.pa05.view;

import cs3500.pa05.controller.NewTaskController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public abstract class SceneLoader {
  private FXMLLoader loader;
  public SceneLoader(Object controller, String fxml) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource(fxml));

    this.loader.setController(controller);
  }

  public Scene load() {
    try {
      return loader.load();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
