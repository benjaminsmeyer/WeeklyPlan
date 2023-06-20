package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

/**
 * JSON format of this record:
 * <p>
 * <code>
 *{
 * "dayOfWeek" : "TUESDAY",
 * "schedule" : [
 *  {
 *     "isEvent" : true,
 *     "event" : {
 *       "name" : "name of event",
 *        "description" : "description of event",
 *        "dayOfWeek" : TUESDAY,
 *       "startTime" : 0000
 *       "duration" : 60
 *      }
 *     "isTask" : false,
 *      "task" : null
 *  }, {
 *    "isEvent" : false,
 *    "event" : null,
 *    "isTask" : true,
 *    "task" : {
 *      "name" : "name of task"
 *      "description" : "description of task"
 *       "dayOfWeek" : TUESDAY
 *       "complete" : false
 *     }
 *  }
 * ]
 * }
 * </code>
 * </p>
 *
 * @param dayOfWeek the day of the week represented by this day
 * @param events a list of Event activities for the day
 * @param tasks a list of Task activities for the day
 */
public record DayJson(
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("events") EventJson[] events,
    @JsonProperty("tasks") TaskJson[] tasks) {
}
