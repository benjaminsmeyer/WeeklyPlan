package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.json.ActivityJson;
import cs3500.pa05.json.DayJson;
import cs3500.pa05.json.EventJson;
import cs3500.pa05.json.TaskJson;
import cs3500.pa05.json.WeekJson;
import cs3500.pa05.model.Activity;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.FileWriter;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.ArrayList;
import java.util.List;

public class Save {
  WeekJson savedWeek;
  FileWriter fileWriter;
  ObjectMapper mapper;

  public Save(String fileName) {
    fileWriter = new FileWriter(fileName);
    mapper = new ObjectMapper();
  }

  public void saveWeek(Week week) {
    savedWeek = weekToJson(week);
    try {
      String str = mapper.writeValueAsString(savedWeek);
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
    if (activity instanceof Event) {
      return new ActivityJson(true, eventToJson((Event) activity), false, null);
    } else {
      return new ActivityJson(false, null, true, taskToJson((Task) activity));
    }
  }

  private EventJson eventToJson(Event event) {
    return mapper.convertValue(event, EventJson.class);
  }

  private TaskJson taskToJson(Task task) {
    return mapper.convertValue(task, TaskJson.class);
  }

}
