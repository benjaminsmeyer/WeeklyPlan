package cs3500.pa05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileReader {
  //json file reader?



  public String readFile(File file) {
    Scanner sc;
    try { // The file may not exist, in which case we need to handle that error (hence try-catch)
      sc = new Scanner(new FileInputStream(file), StandardCharsets.UTF_8);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    StringBuilder content = new StringBuilder();
    while (sc.hasNextLine()) { // Check there is another unread line in the file
      String line = sc.nextLine();
      content.append(line);
    }

    return content.toString();
  }
}
