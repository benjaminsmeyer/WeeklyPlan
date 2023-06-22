package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.Constants;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.TextJson;
import cs3500.pa05.model.json.WeekJson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Reads the file.
 */
public class FileRead {
  List<Day> days;
  String name;
  int maxEvents;
  int maxTasks;
  String notes;
  String quotes;
  String palletName;
  private File file;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Gets all bujo files.
   *
   * @return all bujo files
   */
  public static List<String> getAllBujoFiles() {
    return Arrays.stream(Objects.requireNonNull(new File(Constants.weekPath).list())).toList();
  }

  /**
   * Opens the file.
   *
   * @param file the file to open
   */
  public void openFile(File file) {
    this.file = file;
  }

  /**
   * Reads the file.
   *
   * @return the week
   */
  public Week readFile() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(file);
      WeekJson message = parser.readValueAs(WeekJson.class);
      delegateMessage(message);
      return new Week(days, name, maxEvents, maxTasks, notes, quotes, palletName);
    } catch (IOException e) {
      throw new IllegalStateException("Could not read from file " + file.getName());
    }
  }

  /**
   * Delegates message.
   *
   * @param message the message to delegate
   */
  private void delegateMessage(WeekJson message) {
    parseDayJson(message.days());
    name = message.name();
    maxEvents = message.maxEvents();
    maxTasks = message.maxTasks();
    palletName = message.palletName();
    parseTextJson(message.text());
  }

  /**
   * Parse day json.
   *
   * @param dayJsons the day json
   */
  private void parseDayJson(DayJson[] dayJsons) {
    days = new ArrayList<>();
    for (DayJson dayJson : dayJsons) {
      days.add(new Day(dayJson.dayOfWeek(), parseSchedule(dayJson)));
    }
  }

  /**
   * Parse schedule.
   *
   * @param dayJson the day json
   * @return a list of activities from day
   */
  private List<Activity> parseSchedule(DayJson dayJson) {
    List<Activity> activities = new ArrayList<>();

    activities.addAll(parseEvents(dayJson));
    activities.addAll(parseTasks(dayJson));
    return activities;
  }

  /**
   * Parse event.
   *
   * @param dayJson the day json
   * @return a list of activities from day
   */
  private List<Activity> parseEvents(DayJson dayJson) {
    List<Activity> activities = new ArrayList<>();
    for (EventJson event : dayJson.events()) {
      activities.add(mapper.convertValue(event, Event.class));
    }
    return activities;
  }

  /**
   * Parse task.
   *
   * @param dayJson the day json
   * @return a list of activities from day
   */
  private List<Activity> parseTasks(DayJson dayJson) {
    List<Activity> activities = new ArrayList<>();
    for (TaskJson task : dayJson.tasks()) {
      activities.add(mapper.convertValue(task, Task.class));
    }
    return activities;
  }

  /**
   * Parse text json.
   */
  private void parseTextJson(TextJson textJson) {
    quotes = textJson.quotes();
    notes = textJson.notes();
  }
}
