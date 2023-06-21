package cs3500.pa05.view;

import cs3500.pa05.controller.OpeningWindowController;

/**
 * Opening window viewer.
 */
public class OpeningWindowViewer extends SceneLoader {

  /**
   * Initializes the opening window viewer.
   *
   * @param controller the opening window controller
   */
  public OpeningWindowViewer(OpeningWindowController controller) {
    super(controller, "opening.fxml");
  }
}
