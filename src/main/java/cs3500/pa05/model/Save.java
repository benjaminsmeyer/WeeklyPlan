package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.json.ActivityJson;
import cs3500.pa05.json.DayJson;
import cs3500.pa05.json.EventJson;
import cs3500.pa05.json.JsonUtils;
import cs3500.pa05.json.TaskJson;
import cs3500.pa05.json.WeekJson;
import java.util.ArrayList;
import java.util.List;

public class Save {
  FileWriter fileWriter;
  ObjectMapper mapper;

  /**
   * Initializes a Save of a week.
   *
   * @param fileName the name the week .bujo file
   */
  public Save(String fileName) {
    fileWriter = new FileWriter(fileName);
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
      fileWriter.writeToFile(str);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Converts a week into weekJson.
   *
   * @param week  the week instance being converted
   * @return  the WeekJson of the given week
   */
  private WeekJson weekToJson(Week week) {
    WeekJson response = new WeekJson(daysToJson(week.getDays()), week.getName(),
        week.getMaxEvents(), week.getMaxTasks());
    return response;
  }

  /**
   * Converts a list of days into an array of DayJson.
   *
   * @param days the list of days being given
   * @return  the corresponding array of DayJson to the days
   */
  private DayJson[] daysToJson(List<Day> days) {
    List<DayJson> week = new ArrayList<>();
    for (Day day : days) {
      week.add(new DayJson(day.getDayOfWeek(), activitiesToJson(day.getSchedule())));
    }
    return week.toArray(DayJson[]::new);
  }

  /**
   * Converts a list of Activity into an array of ActivityJson.
   *
   * @param activities the list of activities being given
   * @return  the corresponding array of ActivityJson to the days
   */
  private ActivityJson[] activitiesToJson(List<Activity> activities) {
    List<ActivityJson> schedule = new ArrayList<>();
    for (Activity activity : activities) {
      schedule.add(activityToJson(activity));
    }
    return schedule.toArray(ActivityJson[]::new);
  }

  /**
   * Converts an individual activity to its corresponding Json.
   *
   * @param activity the activity to be converted
   * @return  the converted activity
   */
  private ActivityJson activityToJson(Activity activity) {
    return new ActivityJson(
        activity instanceof Event,
        eventToJson(activity),
        activity instanceof Task,
        taskToJson(activity)
    );
  }

  /**
   * Converts an activity into an EventJson.
   *
   * @param activity the activity to be converted
   * @return  the corresponding EventJson or null
   */
  private EventJson eventToJson(Activity activity) {
    try {
      return mapper.convertValue(activity, EventJson.class);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  /**
   * Converts an activity into an TaskJson.
   *
   * @param activity the activity to be converted
   * @return  the corresponding TaskJson or null
   */
  private TaskJson taskToJson(Activity activity) {
    try {
      return mapper.convertValue(activity, TaskJson.class);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
