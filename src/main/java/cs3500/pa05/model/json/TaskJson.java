package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "name" : "name of task",
 * "description" : "description of task",
 * "dayOfWeek" : TUESDAY,
 * "complete" : true
 * }
 * </code>
 * </p>
 *
 * @param name        the name of the task
 * @param description the description of the task
 * @param dayOfWeek   the day of the week
 * @param done        if the task is complete
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("done") boolean done) {
}


