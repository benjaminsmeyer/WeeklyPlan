package cs3500.pa05.controller;

import cs3500.pa05.model.Pallet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.text.Font;

/**
 * Manages the different Pallets.
 */
public class PalletManager {
  /**
   * A singleton instance of the PalletManager
   */
  public static PalletManager palletManager;
  private Pallet currentPallet;
  private Pallet defaultPallet;
  private Pallet darkPallet;
  private Pallet violetPallet;
  private Pallet bubblegumPallet;
  private Pallet monoPallet;
  private List<Pallet> themes;

  /**
   * Static method that sets up a static instance of the PalletManager
   */
  public static void setupPalletManager() {
    palletManager = new PalletManager();

    palletManager.defaultPallet = new Pallet(
        "Default",
        "#D7DAE5",
        "#F7F7F2",
        "#8EAF9D",
        "#A6D8D4",
        "#B9CDDA",
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/verdana.ttf").toExternalForm(), 15),
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/verdana.ttf").toExternalForm(), 12),
        "#000000",
        "#FF0000"
    );
    palletManager.darkPallet = new Pallet(
        "Dark",
        "#261C15",
        "#2d2d2d",
        "#4E8098",
        "#F05D23",
        "#9A348E",
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/verdana.ttf").toExternalForm(), 15),
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/verdana.ttf").toExternalForm(), 12),
        "#FFFFFF",
        "#FF0000"
    );
    palletManager.violetPallet = new Pallet(
        "Violet",
        "#513B56",
        "#525174",
        "#348AA7",
        "#F87575",
        "#A69658",
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/Mona-Sans.ttf").toExternalForm(), 15),
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/Mona-Sans.ttf").toExternalForm(), 12),
        "#FFFFFF",
        "#FF0000"
    );
    palletManager.bubblegumPallet = new Pallet(
        "Bubble Gum",
        "#E574BC",
        "#F9B4ED",
        "#E5F4E3",
        "#FDF0D5",
        "#FF8360",
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/LDFComicSans.ttf").toExternalForm(), 15),
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/LDFComicSans.ttf").toExternalForm(), 12),
        "#1E2D24",
        "#FF0000"
    );
    palletManager.monoPallet = new Pallet(
        "Monochrome",
        "#717171",
        "#c6c6c6",
        "#e9e9e9",
        "#c0c0c0",
        "#efefef",
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/cour.ttf").toExternalForm(), 15),
        Font.loadFont(PalletManager.class.getClassLoader()
            .getResource("fonts/cour.ttf").toExternalForm(), 12),
        "#000000",
        "#FFFFFF"
    );
    palletManager.themes = new ArrayList<>(Arrays.stream(new Pallet[] {palletManager.defaultPallet,
        palletManager.darkPallet, palletManager.violetPallet, palletManager.bubblegumPallet,
        palletManager.monoPallet}).toList());

    palletManager.currentPallet = palletManager.getDefault();
  }

  /**
   * A static method for setting up a pallet manager in test methods
   * Should not be used outside of testing
   */
  public static void setupTestPalletManager() {
    palletManager = new PalletManager();

    palletManager.defaultPallet = new Pallet(
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

    palletManager.themes
        = new ArrayList<>(Arrays.stream(new Pallet[] {palletManager.defaultPallet}).toList());

    palletManager.currentPallet = palletManager.defaultPallet;

  }

  /**
   * Gets the pallet with name.
   *
   * @param name the name
   * @return the pallet with name
   */
  public Pallet getPalletWithName(String name) {
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
  public void setCurrentPallet(Pallet pallet) {
    currentPallet = pallet;
  }

  /**
   * Returns a list of all the themes managed by the PalletManager
   *
   * @return a list of themes managed by the PalletManager
   */
  public List<Pallet> getThemes() {
    return themes;
  }

  /**
   * Returns the default theme
   *
   * @return the default Pallet
   */
  public Pallet getDefault() {
    return defaultPallet;
  }

  /**
   * Returns the Pallet currently in use
   *
   * @return the Pallet currently in use
   */
  public Pallet getCurrentPallet() {
    return currentPallet;
  }
}
