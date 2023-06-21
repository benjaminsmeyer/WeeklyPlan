package cs3500.pa05.view;

import cs3500.pa05.controller.NewEventController;
import cs3500.pa05.controller.PalletManager;
import cs3500.pa05.model.Event;
import javafx.stage.Stage;

/**
 * Event box
 */
public class EventBox extends ActivityBox {
  private Event event;

  /**
   * Constructor for EventBox that takes a name, description, time, and the Event to render
   *
   * @param name        the name of the Event
   * @param description a description of the Event
   * @param time        the time of the Event
   * @param event       the Event to be rendered
   */
  public EventBox(String name, String description, String time, Event event) {
    createHeader(name);
    createSubtext(description);
    createSubtext(time);

    this.event = event;
    setup();
  }

  /**
   * A constructor for EventBox that takes a name, time, and the Event to be rendered
   *
   * @param name  the name of the event
   * @param time  a description of the event
   * @param event the Event to be rendered
   */
  public EventBox(String name, String time, Event event) {
    createHeader(name);
    createSubtext(time);

    this.event = event;
    setup();
  }

  @Override
  protected void setup() {
    super.setup();
    setStyle("-fx-background-color: " + PalletManager.currentPallet.eventColor());
  }

  @Override
  protected void edit() {
    NewEventController newTaskController = new NewEventController(event);
    NewEventView newTaskView = new NewEventView(newTaskController);

    Stage stage = new Stage();
    stage.setScene(newTaskView.load());
    stage.show();
  }
}
