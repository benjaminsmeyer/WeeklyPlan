package cs3500.pa05.view;

import cs3500.pa05.controller.NewTaskController;

/**
 * View for creating a new Task
 */
public class NewTaskView extends SceneLoader {
  public NewTaskView(NewTaskController controller) {
    super(controller, "newTask.fxml");
  }
}
