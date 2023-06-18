package cs3500.pa05.model;

import javafx.scene.text.Font;

public record Pallet (
    String name,
   String backgroundColor,
   String overlayColor,
   String taskColor,
  String eventColor,
  String saveColor,
  Font headerFont,
  Font textFont,
  String validTextColor, String invalidTextColor){ }
