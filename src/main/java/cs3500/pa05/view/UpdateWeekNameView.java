package cs3500.pa05.view;

import cs3500.pa05.controller.NewEventController;
import cs3500.pa05.controller.UpdateWeekNameController;
import cs3500.pa05.model.Event;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class UpdateWeekNameView {
  private FXMLLoader loader;
  public UpdateWeekNameView(UpdateWeekNameController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("updateWeekName.fxml"));

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
