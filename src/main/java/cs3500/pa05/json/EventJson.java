package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

/**
 * JSON format of this record:
 * <p>
 * <code>
 *{
 * "name" : "name of task",
 * "description" : "description of task",
 * "dayOfWeek" : TUESDAY,
 * "startTime" : 0000
 * "duration" : 60
 * }
 * </code>
 * </p>
 *
 * @param name the name of the event
 * @param description the description of the event
 * @param dayOfWeek the day of the week for the event
 * @param startTime the start time of the event
 * @param duration the duration of the event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("startTime") int startTime,
    @JsonProperty("duration") int duration) {
}
