package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 *{
 * "quotes" : "quotes go here",
 * "notes" : notes go here"
 * }
 * </code>
 * </p>
 *
 * @param quotes the quotes for the week
 * @param notes the notes for the week
 */
public record TextJson(
    @JsonProperty("quotes") String quotes,
    @JsonProperty("notes") String notes) {
}
