package cs3500.pa05;

import cs3500.pa05.controller.PalletManager;
import javafx.scene.paint.Paint;

/**
 * Constant variables to use throughout the class
 */
public final class Constants {

  public static final int activityWidth = 120;
  public static final int defaultBorderRadius = 18;

  public static final String eventColor = PalletManager.currentPallet.eventColor();
  public static final String backgroundColor = PalletManager.currentPallet.backgroundColor();
  public static final String taskColor = PalletManager.currentPallet.taskColor();
  public static final String saveColor = PalletManager.currentPallet.saveColor();
  public static final String defaultTextColor = PalletManager.currentPallet.validTextColor();
  public static final String invalidTextColor = PalletManager.currentPallet.invalidTextColor();
  public static final String overlayColor = PalletManager.currentPallet.overlayColor();
  public static final double descriptionOpacity = .7;
  public static final String taskSelectedColor = "#8EAFFF";
  public static final String activityNameColor = "#000000";
  public static final String activityDescriptionColor = "#545454";
  public static final String weekFont = "Verdana";
  public static final String weekPath = "weeks/";
  public static final Paint validInputLabelColor = Paint.valueOf(defaultTextColor);
  public static final Paint invalidInputLabelColor = Paint.valueOf(invalidTextColor);
}
