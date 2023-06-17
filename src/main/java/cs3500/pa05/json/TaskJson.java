package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;

//Let the user click Save to persist the data in a Week to a file using JSON encoding in a .bujo file
/**
 * JSON format of this record:
 * <p>
 *{
 * "name" : "name of task",
 * "description" : "description of task",
 * "dayOfWeek" : TUESDAY,
 * "complete" : true
 * }
 * </code>
 * </p>
 *
 * @param name the name of the task
 * @param description the description of the task
 * @param dayOfWeek the day of the week
 * @param complete if the task is complete
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
    @JsonProperty("complete") boolean complete){
}


