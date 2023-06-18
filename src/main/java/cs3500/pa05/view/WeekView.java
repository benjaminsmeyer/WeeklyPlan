package cs3500.pa05.view;

import cs3500.pa05.controller.WeekManager;

/**
 * Week View for User
 */
public class WeekView extends SceneLoader {
  public WeekView(WeekManager controller) {
    super(controller, "week.fxml");
  }

}
