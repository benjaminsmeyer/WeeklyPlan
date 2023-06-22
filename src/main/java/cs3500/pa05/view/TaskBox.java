package cs3500.pa05.view;

import cs3500.pa05.controller.NewTaskController;
import cs3500.pa05.controller.PalletManager;
import cs3500.pa05.model.Task;
import javafx.stage.Stage;

/**
 * A custom VBox to TaskBoxes.
 */
public class TaskBox extends ActivityBox {
  Task task;

  /**
   * Constructor for TaskBox that takes only a name and task.
   *
   * @param name the name of the Task
   * @param task the Task to be rendered
   */
  public TaskBox(String name, Task task) {
    createHeader(name);

    this.task = task;
    setup();
  }

  /**
   * Constructor for TaskBox that takes a name, description, and the task to be rendered.
   *
   * @param name        the name of the Task
   * @param description a description of the Task
   * @param task        the Task to be rendered
   */
  public TaskBox(String name, String description, Task task) {
    createHeader(name);
    createSubtext(description);

    this.task = task;
    setup();
  }

  @Override
  protected void setup() {
    super.setup();
    setStyle("-fx-background-color: " + PalletManager.palletManager.getCurrentPallet().taskColor());

    if (task.isDone()) {
      setOpacity(.6);
      createSubtext("Complete");
    }
  }

  @Override
  protected void edit() {
    NewTaskController newTaskController = new NewTaskController(task);
    NewTaskView newTaskView = new NewTaskView(newTaskController);

    Stage stage = new Stage();
    stage.setScene(newTaskView.load());
    stage.show();
  }
}
