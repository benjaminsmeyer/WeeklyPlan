package cs3500.pa05.view;

import cs3500.pa05.controller.NewTaskController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class NewTaskView {
  private FXMLLoader loader;
  public NewTaskView(NewTaskController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("newTask.fxml"));
  }

  public Scene load() {
    try {
      return loader.load();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
