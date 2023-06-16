package cs3500.pa05.view;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.VBox;

public class DayView {

  /**
   * Returns a list of VBox with a rendered version of each of the given activities
   *
   * @param activities a list of activities to render
   * @return list of VBoxes with corresponding to the given list of activities
   */
  public static List<VBox> renderActivities(List<Activity> activities) {
    List<VBox> result = new ArrayList<>();

    for (Activity activity : activities) {
      if (activity instanceof Event) {
        result.add(renderEvent((Event) activity));
      } else if (activity instanceof Task) {
        result.add(renderTask((Task) activity));
      } else {
        throw new IllegalArgumentException("Invalid activity given");
      }
    }

    return result;
  }

  /**
   * Returns a VBox representing the given event
   *
   * @param event the Event to render
   * @return a VBox representing the given Event
   */
  private static VBox renderEvent(Event event) {
    VBox buttonBox = new VBox();
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

    buttonBox.setAlignment(Pos.TOP_CENTER);

    return buttonBox;
  }

  /**
   * Returns a VBox representing the given Task
   *
   * @param task The Task to render
   * @returna VBox representing the given Task
   */
  private static VBox renderTask(Task task) {
    VBox buttonBox = new VBox();
    buttonBox.setPrefWidth(Constants.activityWidth);

    CheckBox name = new CheckBox(task.getName());
    name.setSelected(task.isDone());
    buttonBox.getChildren().add(name);

    if (!task.getDescription().equals("")) {
      Label description = new Label(task.getDescription());
      buttonBox.getChildren().add(description);
    }

    buttonBox.setAlignment(Pos.TOP_CENTER);

    return buttonBox;
  }
}
