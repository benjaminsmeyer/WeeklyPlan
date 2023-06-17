package cs3500.pa05.controller;

import cs3500.pa05.Constants;
import cs3500.pa05.model.Pallet;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PalletManager {
  public static Pallet currentPallet;

  public static Pallet defaultPallete = new Pallet(
      "#D7DAE5",
      "#F7F7F2",
      "#8EAF9D",
      "#A6D8D4",
      "#B9CDDA",
      Font.font("SWItalc", FontWeight.NORMAL,
          FontPosture.REGULAR, 33),
      Font.font("Verdana", FontWeight.NORMAL,
          FontPosture.REGULAR, 33),
      "#000000",
      "#FF0000"
  );

  public static void setCurrentPallet(Pallet pallet) {
    currentPallet = pallet;
  }

}
