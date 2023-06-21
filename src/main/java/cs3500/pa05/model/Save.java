package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.TextJson;
import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves file
 */
public class Save {
  FileWrite fileWrite;
  ObjectMapper mapper;

  /**
   * Initializes a Save of a week.
   *
   * @param fileName the name the week .bujo file
   */
  public Save(String fileName) {
    fileWrite = new FileWrite(fileName);
    mapper = new ObjectMapper();
  }

  /**
   * Saves the week and creates the output file.
   *
   * @param week the week instance being saved
   */
  public void saveWeek(Week week) {
    JsonNode jsonResponse = JsonUtils.serializeRecord(weekToJson(week));
    try {
      String str = mapper.writeValueAsString(jsonResponse);
      fileWrite.writeToFile(str);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Converts a week into weekJson.
   *
   * @param week the week instance being converted
   * @return the WeekJson of the given week
   */
  private WeekJson weekToJson(Week week) {
    WeekJson response = new WeekJson(daysToJson(week.getDays()), week.getName(),
        week.getMaxEvents(), week.getMaxTasks(), textToJson(week), week.getPalletName());
    return response;
  }

  /**
   * Converts text into corresponding json.
   *
   * @param week the week this text belongs to
   * @return the corresponding TextJson in week
   */
  private TextJson textToJson(Week week) {
    return new TextJson(week.getQuotes(), week.getNotes());
  }

  /**
   * Converts a list of days into an array of DayJson.
   *
   * @param days the list of days being given
   * @return the corresponding array of DayJson to the days
   */
  private DayJson[] daysToJson(List<Day> days) {
    List<DayJson> week = new ArrayList<>();
    for (Day day : days) {
      week.add(new DayJson(day.getDayOfWeek(), eventsToJson(day), tasksToJson(day)));
    }
    return week.toArray(DayJson[]::new);
  }

  /**
   * Converts events to json
   *
   * @param day the day
   * @return the events to json
   */
  private EventJson[] eventsToJson(Day day) {
    List<EventJson> events = new ArrayList<>();
    for (Event event : day.getEvents()) {
      events.add(mapper.convertValue(event, EventJson.class));
    }
    return events.toArray(EventJson[]::new);
  }

  /**
   * Converts tasks to json
   *
   * @param day the day
   * @return the tasks to json
   */
  private TaskJson[] tasksToJson(Day day) {
    List<TaskJson> tasks = new ArrayList<>();
    for (Task task : day.getTasks()) {
      tasks.add(mapper.convertValue(task, TaskJson.class));
    }
    return tasks.toArray(TaskJson[]::new);
  }
}
