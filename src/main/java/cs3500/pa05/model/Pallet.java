package cs3500.pa05.model;

import javafx.scene.text.Font;

/**
 * Pallet of each theme
 *
 * @param name             the name of theme
 * @param backgroundColor  color of theme
 * @param overlayColor     color of theme
 * @param taskColor        color of theme
 * @param eventColor       color of theme
 * @param saveColor        color of theme
 * @param headerFont       font of theme
 * @param textFont         font of theme
 * @param validTextColor   color of theme
 * @param invalidTextColor color of theme
 */
public record Pallet(
    String name,
    String backgroundColor,
    String overlayColor,
    String taskColor,
    String eventColor,
    String saveColor,
    Font headerFont,
    Font textFont,
    String validTextColor, String invalidTextColor) {
}
