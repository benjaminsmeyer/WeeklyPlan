package cs3500.pa05.json;

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
 * @param schedule a list of activities for the day
 */
public record DayJson(
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("schedule") ActivityJson[] schedule) {
}
