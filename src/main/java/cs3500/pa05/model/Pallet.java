package cs3500.pa05.model;

import javafx.scene.text.Font;

public class Pallet {
  public final String backgroundColor;
  public final String overlayColor;
  public final String taskColor;
  public final String eventColor;
  public final String saveColor;
  public final Font headerFont;
  public final Font textFont;
  public final String validTextColor;
  public final String invalidTextColor;

  public Pallet(String backgroundColor,
                String overlayColor,
                String taskColor,
                String eventColor,
                String saveColor,
                Font headerFont,
                Font textFont,
                String validTextColor,
                String invalidTextColor) {
    this.backgroundColor = backgroundColor;
    this.overlayColor = overlayColor;
    this.taskColor = taskColor;
    this.eventColor = eventColor;
    this.saveColor = saveColor;
    this.headerFont = headerFont;
    this.textFont = textFont;
    this.validTextColor = validTextColor;
    this.invalidTextColor = invalidTextColor;
  }

}
