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

    String name = event.getName();

    String startTime = Utils.timeToString(event.getStartTime());
    String endTime = Utils.timeToString(event.getStartTime() + event.getDuration());

    String time = startTime + " - " + endTime;

    if (!event.getDescription().equals("")) {
      String description = event.getDescription();
      return new EventBox(name, description, time);
    } else {
      return new EventBox(name, time);
    }
  }

  /**
   * Returns a VBox representing the given Task
   *
   * @param task The Task to render
   * @returna VBox representing the given Task
   */
  private static VBox renderTask(Task task) {
    if (!task.getDescription().equals("")) {
      return new TaskBox(task.getName(), task.getDescription(), task.isDone());
    } else {
      return new TaskBox(task.getName(), task.isDone());
    }

  }
}
