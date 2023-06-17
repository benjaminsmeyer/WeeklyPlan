package cs3500.pa05.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 *{
 * "days" : [
 *  * {
 *  * "coord": {"x": 0, "y": 0},
 *  * "length": 6,
 *  * "direction": "VERTICAL"
 *  * },
 *  ]
 * "name" : "my Week",
 * "maxEvents" : 4,
 * "maxTasks" : 4
 * }
 * </code>
 * </p>
 *
 * @param days the Days of this Week
 * @param name the name of this week
 * @param maxEvents the max nums of events per day
 * @param maxTasks the max nums of tasks per day
 */
public record WeekJson(
    @JsonProperty("days") DayJson[] days,
    @JsonProperty("name") String name,
    @JsonProperty("maxEvents") int maxEvents,
    @JsonProperty("maxTasks") int maxTasks) {
}