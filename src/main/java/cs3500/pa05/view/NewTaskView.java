package cs3500.pa05.view;

import cs3500.pa05.controller.NewTaskController;

/**
 * View for creating a new Task.
 */
public class NewTaskView extends SceneLoader {

  /**
   * Initializes the new task view.
   *
   * @param controller the new task controller
   */
  public NewTaskView(NewTaskController controller) {
    super(controller, "newTask.fxml");
  }
}
