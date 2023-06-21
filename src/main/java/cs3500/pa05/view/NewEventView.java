package cs3500.pa05.view;

import cs3500.pa05.controller.NewEventController;

/**
 * New event view
 */
public class NewEventView extends SceneLoader {
  /**
   * Initializes a new event view
   *
   * @param controller a new event controller
   */
  public NewEventView(NewEventController controller) {
    super(controller, "newEvent.fxml");
  }
}
