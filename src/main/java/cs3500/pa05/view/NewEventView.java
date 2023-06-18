package cs3500.pa05.view;

import cs3500.pa05.controller.NewEventController;

public class NewEventView extends SceneLoader {
  public NewEventView(NewEventController controller) {
    super(controller, "newEvent.fxml");
  }
}
