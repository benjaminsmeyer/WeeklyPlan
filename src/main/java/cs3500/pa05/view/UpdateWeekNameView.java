package cs3500.pa05.view;

import cs3500.pa05.controller.UpdateWeekNameController;

/**
 * Updates the week name view.
 */
public class UpdateWeekNameView extends SceneLoader {

  /**
   * Initializes the update week name view.
   *
   * @param controller the update week name controller
   */
  public UpdateWeekNameView(UpdateWeekNameController controller) {
    super(controller, "updateWeekName.fxml");
  }
}
