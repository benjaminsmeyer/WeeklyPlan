package cs3500.pa05.controller;

import cs3500.pa05.model.Pallet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Manages the different Pallets
 */
public class PalletManager {
  public static Pallet currentPallet;

  public static Pallet defaultPallet = new Pallet(
      "Default",
      "#D7DAE5",
      "#F7F7F2",
      "#8EAF9D",
      "#A6D8D4",
      "#B9CDDA",
      Font.font("Verdana", FontWeight.NORMAL,
          FontPosture.REGULAR, 15),
      Font.font("Verdana", FontWeight.NORMAL,
          FontPosture.REGULAR, 12),
      "#000000",
      "#FF0000"
  );

  public static Pallet darkPallet = new Pallet(
      "Dark",
      "#261C15",
      "#2d2d2d",
      "#4E8098",
      "#F05D23",
      "#9A348E",
      Font.font("Century", FontWeight.NORMAL,
          FontPosture.REGULAR, 15),
      Font.font("Century", FontWeight.NORMAL,
          FontPosture.REGULAR, 12),
      "#FFFFFF",
      "#FF0000"
  );

  public static Pallet violetPallet = new Pallet(
      "Violet",
      "#513B56",
      "#525174",
      "#348AA7",
      "#5DD39E",
      "#BCE784",
      Font.font("Jokerman", FontWeight.NORMAL,
          FontPosture.REGULAR, 15),
      Font.font("Jokerman", FontWeight.NORMAL,
          FontPosture.REGULAR, 12),
      "#000000",
      "#FF0000"
  );

  public static Pallet bubblegumPallet = new Pallet(
      "Bubble Gum",
      "#E574BC",
      "#F9B4ED",
      "#E5F4E3",
      "#FDF0D5",
      "#FF8360",
      Font.font("Comic Sans MS", FontWeight.NORMAL,
          FontPosture.REGULAR, 15),
      Font.font("Comic Sans MS", FontWeight.NORMAL,
          FontPosture.REGULAR, 12),
      "#1E2D24",
      "#FF0000"
  );

  public static Pallet monoPallet = new Pallet(
      "Monochrome",
      "#717171",
      "#979797",
      "#e9e9e9",
      "#c0c0c0",
      "#efefef",
      Font.font("Courier New", FontWeight.NORMAL,
          FontPosture.REGULAR, 15),
      Font.font("Courier New", FontWeight.NORMAL,
          FontPosture.REGULAR, 12),
      "#000000",
      "#FFFFFF"
  );

  public static List<Pallet> themes
      = new ArrayList<>(Arrays.stream(new Pallet[]{defaultPallet, darkPallet,
      violetPallet, bubblegumPallet, monoPallet}).toList());

  public static Pallet getPalletWithName(String name) {
    for (Pallet p : themes) {
      if (p.name().equals(name)) {
        return p;
      }
    }

    return null;
  }

  /**
   * Updates the current color Pallet being rendered
   *
   * @param pallet the pallet to set to be the current pallet
   */
  public static void setCurrentPallet(Pallet pallet) {
    currentPallet = pallet;
  }

}
