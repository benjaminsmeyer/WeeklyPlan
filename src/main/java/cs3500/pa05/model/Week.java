package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

public class Week {
  private List<Day> days;
  private String name;
  private int maxEvents;
  private int maxTasks;

  private String notes;
  private String quotes;
  //List<String> categories;

  /**
   * Default constructor for Week
   *
   * @param days the Days of this Week
   * @param name the name of this Week
   */
  public Week(List<Day> days, String name) {
    this.days = days;
    this.name = name;
    this.maxEvents = Integer.MAX_VALUE;
    this.maxTasks = Integer.MAX_VALUE;
    this.notes = "";
    this.quotes = "";
  }

  public Week(List<Day> days, String name, int maxEvents, int maxTasks) {
    this.days = days;
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = "";
    this.quotes = "";
  }

  public Week(List<Day> days, String name, int maxEvents, int maxTasks,
              String notes, String quotes) {
    this.days = days;
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = notes;
    this.quotes = quotes;
  }

  public Week(String name, int maxEvents, int maxTasks, DayOfWeek startDay) {
    days = new ArrayList<>();
    setWeekStart(startDay);
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = "";
    this.quotes = "";
  }


  /**
   * Convenience constructor for Week. Initializes a week with empty days
   */
  public Week() {
    days = new ArrayList<>();
    setWeekStart(DayOfWeek.SUNDAY);
    this.maxEvents = Integer.MAX_VALUE;
    this.maxTasks = Integer.MAX_VALUE;
    this.name = "My Week";
    this.notes = "";
    this.quotes = "";
  }

  public Week(DayOfWeek startingDay) {
    setWeekStart(startingDay);
    this.maxEvents = Integer.MAX_VALUE;
    this.maxTasks = Integer.MAX_VALUE;
    this.name = "Week";
    this.notes = "";
    this.quotes = "";
  }

  private void setWeekStart(DayOfWeek startingDay) {
    days = new ArrayList<>();
    List<DayOfWeek> array = List.of(DayOfWeek.values());

    DayOfWeek[] ordering = new DayOfWeek[array.size()];
    int indexOfStartingDay = array.indexOf(startingDay);

    for (int i = 0; i < array.size(); i ++) {
      int newIndexes = i + (array.size() - indexOfStartingDay);
      ordering[newIndexes % array.size()] = array.get(i);
    }

    for (DayOfWeek day : ordering) {
      days.add(new Day(day));
    }
  }

  public void updateWeekStart(DayOfWeek startingDay) {
    List<DayOfWeek> array = List.of(DayOfWeek.values());

    DayOfWeek[] ordering = new DayOfWeek[array.size()];
    int indexOfStartingDay = array.indexOf(startingDay);

    for (int i = 0; i < array.size(); i ++) {
      int newIndexes = i + (array.size() - indexOfStartingDay);
      ordering[newIndexes % array.size()] = array.get(i);
    }

    ArrayList<Day> newDays = new ArrayList<>();
    for (DayOfWeek day : ordering) {
      newDays.add(this.getDay(day));
    }
    this.days = newDays;
  }

  private Day getDay(DayOfWeek dayOfWeek) {
    for (Day day : days) {
      if (day.getDayOfWeek() == dayOfWeek) {
        return  day;
      }
    }
    throw new IllegalArgumentException("Invalid day given " + dayOfWeek);
  }

  public DayOfWeek getStartOfWeek() {
    return days.get(0).getDayOfWeek();
  }


  /**
   * Returns the Days stored in this week
   *
   * @return the Days stored in this week
   */
  public List<Day> getDays() {
    return days;
  }

  public void addActivity(Activity activity) {
    for (Day day : days) {
      if (activity.getDayOfWeek() == day.getDayOfWeek()) {
        day.addActivity(activity);
      }
    }
  }

  public void removeActivity(Activity activity) {
    for (Day day : days) {
      if (activity.getDayOfWeek() == day.getDayOfWeek()) {
        day.removeActivity(activity);
      }
    }
  }

  public void updateActivityDates() {
    List<Activity> activities = getAllActivities();
    clearDayActivities();

    for (Day day : days) {
      for (Activity activity : activities) {
        if (day.getDayOfWeek() == activity.getDayOfWeek()) {
          day.addActivity(activity);
        }
      }
    }

  }

  private List<Activity> getAllActivities() {
    List<Activity> activities = new ArrayList<>();

    for (Day day : days) {
      activities.addAll(day.getSchedule());
    }

    return activities;
  }

  private void clearDayActivities() {
    for (Day day : days) {
      day.getSchedule().clear();
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  public int getMaxEvents() {
    return maxEvents;
  }

  public int getMaxTasks() {
    return maxTasks;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getQuotes() {
    return quotes;
  }

  public void setQuotes(String quotes) {
    this.quotes = quotes;
  }

  public int totalWeekEvents() {
    int count = 0;
    for (Day day : days) {
      count += day.countEvents();
    }
    return count;
  }

  public int totalWeekTasks() {
    int count = 0;
    for (Day day : days) {
      count += day.countTasks();
    }
    return count;
  }

  public int totalCompleteTasks() {    int count = 0;
    for (Day day : days) {
      count += day.countCompletedTasks();
    }
    return count;}


}
