package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 *{
 * "isEvent" : true,
 * "event" : {
 *   "name" : "name of task",
 *   "description" : "description of task",
 *   "dayOfWeek" : TUESDAY,
 *   "startTime" : 0000
 *   "duration" : 60
 *   },
 * "isTask" : false,
 * "task" : null
 * }
 * </code>
 * </p>
 *
 * @param isEvent is this Activity an event
 * @param event the event information
 * @param isTask is this Activity a task
 * @param task the task information
 */
public record ActivityJson(
    @JsonProperty("isEvent") boolean isEvent,
    @JsonProperty("event") EventJson event,
    @JsonProperty("isTask") boolean isTask,
    @JsonProperty("task") TaskJson task){
}
