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

  public Save(String fileName) {
    fileWriter = new FileWriter(fileName);
    mapper = new ObjectMapper();
  }

  public void saveWeek(Week week) {
    JsonNode jsonResponse = JsonUtils.serializeRecord(weekToJson(week));
    try {
      String str = mapper.writeValueAsString(jsonResponse);
      fileWriter.writeToFile(str);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
    }
  }

  private WeekJson weekToJson(Week week) {
    WeekJson response = new WeekJson(daysToJson(week.getDays()), week.getName(),
        week.getMaxEvents(), week.getMaxTasks());
    return response;
  }

  private DayJson[] daysToJson(List<Day> days) {
    List<DayJson> week = new ArrayList<>();
    for (Day day : days) {
      week.add(new DayJson(day.getDayOfWeek(), activitiesToJson(day.getSchedule())));
    }
    return week.toArray(DayJson[]::new);
  }

  private ActivityJson[] activitiesToJson(List<Activity> activities) {
    List<ActivityJson> schedule = new ArrayList<>();
    for (Activity activity : activities) {
      schedule.add(activityToJson(activity));
    }
    return schedule.toArray(ActivityJson[]::new);
  }

  private ActivityJson activityToJson(Activity activity) {
    return new ActivityJson(
        activity instanceof Event,
        eventToJson(activity),
        activity instanceof Task,
        taskToJson(activity)
    );
  }

  private EventJson eventToJson(Activity activity) {
    try {
      return mapper.convertValue(activity, EventJson.class);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }

  private TaskJson taskToJson(Activity activity) {
    try {
      return mapper.convertValue(activity, TaskJson.class);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
