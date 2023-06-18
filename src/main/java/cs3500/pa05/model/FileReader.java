package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.Constants;
import cs3500.pa05.json.ActivityJson;
import cs3500.pa05.json.DayJson;
import cs3500.pa05.json.TextJson;
import cs3500.pa05.json.WeekJson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {
  //json file reader?
  private File file;
  private ObjectMapper mapper = new ObjectMapper();
  List<Day> days;
  String name;
  int maxEvents;
  int maxTasks;
  String notes;
  String quotes;

  public void openFile(File file) {
    this.file = file;
  }

  public Week readFile() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(file);
      WeekJson message = parser.readValueAs(WeekJson.class);
      delegateMessage(message);
      return new Week(days, name, maxEvents, maxTasks, notes, quotes);
    } catch (IOException e) {
      throw new IllegalStateException("Could not read from file " + file.getName());
      // Disconnected from server or parsing exception
    }
  }

  public static List<String> getAllBujoFiles() {
    List<String> names = Arrays.stream(new File(Constants.weekPath).list()).toList();
    System.out.println(names.get(0));
    return names;
  }

  private void delegateMessage(WeekJson message) {
    parseDayJson(message.days());
    name = message.name();
    maxEvents = message.maxEvents();
    maxTasks = message.maxTasks();
    parseTextJson(message.text());
  }

  private void parseDayJson(DayJson[] dayJsons) {
    days = new ArrayList<>();
    for (DayJson dayJson : dayJsons) {
      days.add(new Day(dayJson.dayOfWeek(), parseSchedule(dayJson)));
    }
  }

  private List<Activity> parseSchedule(DayJson dayJson) {
    List<Activity> activities = new ArrayList<>();
    for (ActivityJson activity : dayJson.schedule()) {
      if (activity.isEvent()) {
        activities.add(mapper.convertValue(activity.event(), Event.class));
      } else {
        activities.add(mapper.convertValue(activity.task(), Task.class));
      }
    }
    return activities;
  }

  private void parseTextJson(TextJson textJson) {
    quotes = textJson.quotes();
    notes = textJson.notes();
  }
}
