package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTest {
  private Day emptyDay;
  private Day day;

  @BeforeEach
  void setUp() {
    emptyDay = new Day(DayOfWeek.SUNDAY);

    List<Activity> activities = new ArrayList<>();
    activities.add(new Task("task", "task description", DayOfWeek.SUNDAY));
    activities.add(new Task("task2", "task description2", DayOfWeek.SUNDAY));
    activities.add(new Event("event", "event description", DayOfWeek.SUNDAY, 1000, 60));

    day = new Day(DayOfWeek.SUNDAY, activities);
  }

  @Test
  void getScheduleTest() {
    List<Activity> schedule = emptyDay.getSchedule();
    assertEquals(0, schedule.size());

    schedule = day.getSchedule();
    assertEquals(3, schedule.size());
  }

  @Test
  void getTasksTest() {
    List<Task> tasks = day.getTasks();
    assertEquals(2, tasks.size());
    assertEquals("task", tasks.get(0).getName());
  }

  @Test
  void getEventsTest() {
    List<Event> events = day.getEvents();
    assertEquals(1, events.size());
    assertEquals("event", events.get(0).getName());
  }

  @Test
  void countEventsTest() {
    assertEquals(1, day.countEvents());
    assertEquals(day.getEvents().size(), day.countEvents());
  }

  @Test
  void countTasksTest() {
    assertEquals(2, day.countTasks());
    assertEquals(day.getTasks().size(), day.countTasks());
  }

  @Test
  void countCompletedTasksTest() {
    assertEquals(0, day.countCompletedTasks());
    List<Task> tasks = day.getTasks();
    tasks.get(0).complete();
    assertEquals(1, day.countCompletedTasks());
  }

  @Test
  void addActivityTest() {
    assertEquals(1, day.countEvents());
    day.addActivity(new Event("event2", "event description2", DayOfWeek.SUNDAY, 1000, 60));
    assertEquals(2, day.countEvents());
  }

  @Test
  void removeActivityTest() {
    assertEquals(1, day.countEvents());
    day.removeActivity(day.getEvents().get(0));
    assertEquals(0, day.countEvents());
  }

  @Test
  void getDayOfWeekTest() {
    assertEquals(DayOfWeek.SUNDAY, day.getDayOfWeek());
  }
}