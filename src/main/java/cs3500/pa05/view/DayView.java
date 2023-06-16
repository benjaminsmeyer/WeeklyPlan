package cs3500.pa05.view;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.VBox;

public class DayView {

  public static List<Button> renderActivities(List<Activity> activities) {
    List<Button> result = new ArrayList<>();

    for (Activity activity : activities) {
      if (activity instanceof Event) {
        result.add(renderEvent((Event)activity));
      } else if (activity instanceof Task) {
        result.add(renderTask((Task)activity));
      } else {
        throw new IllegalArgumentException("Invalid activity given");
      }
    }

    return result;
  }

  private static Button renderEvent(Event event) {
    Button buttonControl = new Button();
    VBox buttonBox = new VBox();
    TestButton button = new TestButton(buttonControl);
    buttonBox.setPrefWidth(Constants.activityWidth);

    Label name = new Label(event.getName());
    buttonBox.getChildren().add(name);

    if (!event.getDescription().equals("")) {
      Label description = new Label(event.getDescription());
      buttonBox.getChildren().add(description);
    }

    String startTime = Utils.timeToString(event.getStartTime());
    String endTime = Utils.timeToString(event.getStartTime() + event.getDuration());

    Label time = new Label(startTime + " - " + endTime);
    buttonBox.getChildren().add(time);

    button.getChildren().add(buttonBox);

    return buttonControl;
  }

  private static Button renderTask(Task task) {
    Button buttonControl = new Button();
    VBox buttonBox = new VBox();
    TestButton button = new TestButton(buttonControl);
    buttonBox.setPrefWidth(Constants.activityWidth);
    button.getChildren().add(buttonBox);

    CheckBox name = new CheckBox(task.getName());
    name.setSelected(task.isDone());
    buttonBox.getChildren().add(name);

    if (!task.getDescription().equals("")) {
      Label description = new Label(task.getDescription());
      buttonBox.getChildren().add(description);
    }

    return buttonControl;
  }
}

class TestButton extends ButtonSkin {
  public TestButton(Button control) {
    super(control);
  }
}