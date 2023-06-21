package cs3500.pa05.model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes a file
 */
public class FileWriter {
  private File file;

  /**
   * Instantiates an instance of FileWriter with the <code>content</code> to be written.
   *
   * @param pathName where to write the contents
   */
  public FileWriter(String pathName) {
    file = new File(pathName);
  }

  /**
   * Writes to the file.
   */
  public void writeToFile(String content) {
    Path path;

    if (file.toString().endsWith("." + "bujo")) {
      path = file.toPath();
    } else {
      path = Path.of(file.toPath() + ".bujo");
    }

    try {
      Files.writeString(path, content);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
