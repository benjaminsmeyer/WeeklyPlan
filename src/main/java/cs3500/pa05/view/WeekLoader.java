package cs3500.pa05.view;

import cs3500.pa05.controller.WeekManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class WeekLoader {
  private FXMLLoader loader;

  public WeekLoader(WeekManager controller){
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("week.fxml"));

    this.loader.setController(controller);
  }

  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
