package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages the different Pallets.
 */
public class PalletManagerMockTest {
  public static Pallet currentPallet;

  public static Pallet defaultPallet = new Pallet(
      "Default",
      "#D7DAE5",
      "#F7F7F2",
      "#8EAF9D",
      "#A6D8D4",
      "#B9CDDA",
      null,
      null,
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
      null,
      null,
      "#FFFFFF",
      "#FF0000"
  );

  public static Pallet violetPallet = new Pallet(
      "Violet",
      "#513B56",
      "#525174",
      "#348AA7",
      "#F87575",
      "#A69658",
      null,
      null,
      "#FFFFFF",
      "#FF0000"
  );

  public static Pallet bubblegumPallet = new Pallet(
      "Bubble Gum",
      "#E574BC",
      "#F9B4ED",
      "#E5F4E3",
      "#FDF0D5",
      "#FF8360",
      null,
      null,
      "#1E2D24",
      "#FF0000"
  );

  public static Pallet monoPallet = new Pallet(
      "Monochrome",
      "#717171",
      "#c6c6c6",
      "#e9e9e9",
      "#c0c0c0",
      "#efefef",
      null,
      null,
      "#000000",
      "#FFFFFF"
  );

  public static List<Pallet> themes
      = new ArrayList<>(Arrays.stream(new Pallet[] {defaultPallet,
        darkPallet, violetPallet, bubblegumPallet,
        monoPallet}).toList());

  /**
   * Gets the pallet with name.
   *
   * @param name the name
   * @return the pallet with name
   */
  public static Pallet getPalletWithName(String name) {
    for (Pallet p : themes) {
      if (p.name().equals(name)) {
        return p;
      }
    }

    return null;
  }

  /**
   * Updates the current color Pallet being rendered.
   *
   * @param pallet the pallet to set to be the current pallet
   */
  public static void setCurrentPallet(Pallet pallet) {
    currentPallet = pallet;
  }

}
