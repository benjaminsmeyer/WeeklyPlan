package cs3500.pa05.controller;

import cs3500.pa05.model.Pallet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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

  public static List<Pallet> themes
      = new ArrayList<>(Arrays.stream(new Pallet[]{defaultPallet, darkPallet, violetPallet}).toList());

  public static void setCurrentPallet(Pallet pallet) {
    currentPallet = pallet;
  }

}
