package cs3500.pa05.model;

import cs3500.pa05.Constants;
import cs3500.pa05.controller.PalletManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the week
 */
public class Week {
  private List<Day> days;
  private String name;
  private int maxEvents;
  private int maxTasks;

  private String notes;
  private String quotes;
  private Pallet weekTheme;
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
    this.weekTheme = PalletManager.defaultPallet;
  }

  /**
   * Week constructor
   *
   * @param days      the days of the week
   * @param name      the name of the week
   * @param maxEvents the max events
   * @param maxTasks  the max tasks
   */
  public Week(List<Day> days, String name, int maxEvents, int maxTasks) {
    this.days = days;
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = "";
    this.quotes = "";
    this.weekTheme = PalletManager.defaultPallet;
  }

  /**
   * Week constructor
   *
   * @param days       the days of the week
   * @param name       the name of the week
   * @param maxEvents  the max events
   * @param maxTasks   the max tasks
   * @param notes      the notes
   * @param quotes     the quotes
   * @param palletName the pallet name
   */
  public Week(List<Day> days, String name, int maxEvents, int maxTasks,
              String notes, String quotes, String palletName) {
    this.days = days;
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = notes;
    this.quotes = quotes;
    this.weekTheme = PalletManager.getPalletWithName(palletName);
    PalletManager.setCurrentPallet(weekTheme);
  }

  /**
   * Week constructor
   *
   * @param startDay  the start of the week
   * @param name      the name of the week
   * @param maxEvents the max events
   * @param maxTasks  the max tasks
   */
  public Week(String name, int maxEvents, int maxTasks, DayOfWeek startDay) {
    days = new ArrayList<>();
    setWeekStart(startDay);
    this.name = name;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.notes = "";
    this.quotes = "";
    this.weekTheme = PalletManager.defaultPallet;
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
    this.weekTheme = PalletManager.defaultPallet;
  }

  /**
   * Week constructor
   *
   * @param startingDay the start of the week
   */
  public Week(DayOfWeek startingDay) {
    setWeekStart(startingDay);
    this.maxEvents = Integer.MAX_VALUE;
    this.maxTasks = Integer.MAX_VALUE;
    this.name = "Week";
    this.notes = "";
    this.quotes = "";
    this.weekTheme = PalletManager.defaultPallet;
  }

  /**
   * Sets the week start
   *
   * @param startingDay the start of the week
   */
  private void setWeekStart(DayOfWeek startingDay) {
    days = new ArrayList<>();
    List<DayOfWeek> array = List.of(DayOfWeek.values());

    DayOfWeek[] ordering = new DayOfWeek[array.size()];
    int indexOfStartingDay = array.indexOf(startingDay);

    for (int i = 0; i < array.size(); i++) {
      int newIndexes = i + (array.size() - indexOfStartingDay);
      ordering[newIndexes % array.size()] = array.get(i);
    }

    for (DayOfWeek day : ordering) {
      days.add(new Day(day));
    }
  }

  /**
   * Updates the week start
   *
   * @param startingDay the start of the week
   */
  public void updateWeekStart(DayOfWeek startingDay) {
    List<DayOfWeek> array = List.of(DayOfWeek.values());

    DayOfWeek[] ordering = new DayOfWeek[array.size()];
    int indexOfStartingDay = array.indexOf(startingDay);

    for (int i = 0; i < array.size(); i++) {
      int newIndexes = i + (array.size() - indexOfStartingDay);
      ordering[newIndexes % array.size()] = array.get(i);
    }

    ArrayList<Day> newDays = new ArrayList<>();
    for (DayOfWeek day : ordering) {
      newDays.add(this.getDay(day));
    }
    this.days = newDays;
  }

  /**
   * Get the day
   *
   * @param dayOfWeek the day of the week
   */
  private Day getDay(DayOfWeek dayOfWeek) {
    for (Day day : days) {
      if (day.getDayOfWeek() == dayOfWeek) {
        return day;
      }
    }
    throw new IllegalArgumentException("Invalid day given " + dayOfWeek);
  }

  /**
   * Get the start of the week
   *
   * @return day of the week
   */
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

  /**
   * Adds activity
   *
   * @param activity the activity
   */
  public void addActivity(Activity activity) {
    for (Day day : days) {
      if (activity.getDayOfWeek() == day.getDayOfWeek()) {
        day.addActivity(activity);
      }
    }
  }

  /**
   * Removes activity
   *
   * @param activity the activity
   */
  public void removeActivity(Activity activity) {
    for (Day day : days) {
      if (activity.getDayOfWeek() == day.getDayOfWeek()) {
        day.removeActivity(activity);
      }
    }
  }

  /**
   * Updates activity dates
   */
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

  /**
   * Gets all activities
   *
   * @return a list of all the activities
   */
  private List<Activity> getAllActivities() {
    List<Activity> activities = new ArrayList<>();

    for (Day day : days) {
      activities.addAll(day.getSchedule());
    }

    return activities;
  }

  /**
   * Clears day activities
   */
  private void clearDayActivities() {
    for (Day day : days) {
      day.getSchedule().clear();
    }
  }

  /**
   * Gets the name
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name
   *
   * @param name the name
   */
  public void setName(String name) {
    File thisFile = new File(Constants.weekPath + this.name + ".bujo");
    this.name = name;
    thisFile.renameTo(new File(Constants.weekPath + this.name + ".bujo"));
  }

  /**
   * Gets the max events
   *
   * @return the max events
   */
  public int getMaxEvents() {
    return maxEvents;
  }

  /**
   * Sets max events
   *
   * @param maxEvents the max events
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Gets the max tasks
   *
   * @return the max tasks
   */
  public int getMaxTasks() {
    return maxTasks;
  }

  /**
   * Sets max tasks
   *
   * @param maxTasks the max tasks
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Gets the notes
   *
   * @return the notes
   */
  public String getNotes() {
    return notes;
  }

  /**
   * Sets the notes
   *
   * @param notes the notes
   */
  public void setNotes(String notes) {
    this.notes = notes;
  }

  /**
   * Gets the quotes
   *
   * @return the quotes
   */
  public String getQuotes() {
    return quotes;
  }

  /**
   * Sets the quotes
   *
   * @param quotes the quotes
   */
  public void setQuotes(String quotes) {
    this.quotes = quotes;
  }

  /**
   * The total of week events
   *
   * @return the total amount of week events
   */
  public int totalWeekEvents() {
    int count = 0;
    for (Day day : days) {
      count += day.countEvents();
    }
    return count;
  }

  /**
   * The total of week tasks
   *
   * @return the total amount of week tasks
   */
  public int totalWeekTasks() {
    int count = 0;
    for (Day day : days) {
      count += day.countTasks();
    }
    return count;
  }

  /**
   * The total of week complete tasks
   *
   * @return the total amount of week complete tasks
   */
  public int totalCompleteTasks() {
    int count = 0;
    for (Day day : days) {
      count += day.countCompletedTasks();
    }
    return count;
  }

  /**
   * Gets the pallet name
   *
   * @return the pallet name
   */
  public String getPalletName() {
    return this.weekTheme.name();
  }

  /**
   * Sets the pallet name
   *
   * @param pallet the pallet
   */
  public void setPallet(Pallet pallet) {
    PalletManager.setCurrentPallet(pallet);
    this.weekTheme = pallet;
  }

}
