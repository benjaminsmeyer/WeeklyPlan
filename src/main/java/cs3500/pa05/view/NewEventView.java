package cs3500.pa05.view;

import cs3500.pa05.controller.NewEventController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class NewEventView {
  private FXMLLoader loader;
  public NewEventView(NewEventController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("newEvent.fxml"));

    this.loader.setController(controller);
  }

  public Scene load() {
    try {
      return loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
