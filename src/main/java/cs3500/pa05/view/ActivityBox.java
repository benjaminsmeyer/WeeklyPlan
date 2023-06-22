package cs3500.pa05.view;

import cs3500.pa05.Constants;
import cs3500.pa05.Utils;
import cs3500.pa05.controller.PalletManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Activity box.
 */
public abstract class ActivityBox extends VBox {

  /**
   * Initializes the settings on this ActivityBox.
   */
  protected void setup() {
    setPadding(new Insets(5, 5, 5, 5));
    setAlignment(Pos.TOP_CENTER);
    setMaxWidth(Constants.activityWidth);

    setSpacing(10);
    setOnMouseClicked(e -> edit());
    Utils.setButtonCursorStatus(this);
  }

  /**
   * Opens a new Stage that allows the user to edit or create a new Activity.
   */
  protected abstract void edit();

  /**
   * Creates a new Header label and adds it to this HBox's children.
   *
   * @param text the text to render
   */
  protected void createHeader(String text) {
    Label label = new Label(text);
    label.setFont(PalletManager.palletManager.getCurrentPallet().headerFont());
    label.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    label.setWrapText(true);
    getChildren().add(label);
  }

  /**
   * Creates a new subtext label and adds it to this HBox's children.
   *
   * @param text the text to render
   */
  protected void createSubtext(String text) {
    Label label = new Label(text);
    label.setTextFill(Color.web(PalletManager.palletManager.getCurrentPallet().validTextColor()));
    label.setWrapText(true);
    label.setFont(PalletManager.palletManager.getCurrentPallet().textFont());
    label.setOpacity(Constants.descriptionOpacity);
    getChildren().add(label);
  }
}
