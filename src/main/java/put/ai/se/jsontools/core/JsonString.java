package put.ai.se.jsontools.core;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

/**
 * Checks if given input is in correct JSON format.
 */
public class JsonString implements JsonFormattable {
    private String value;

    /**
     * Checks whether the provided string is a valid JSON string.
     *
     * @param value The string to be checked.
     * @return {@code true} if the provided string is a valid JSON string, {@code false} otherwise.
     */
    public static boolean isValid(String value) {
        try {
            try (JsonReader reader = new JsonReader(new StringReader(value))) {
                new Gson().getAdapter(JsonObject.class).read(reader);
                reader.hasNext();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Sets the value of the JSON string.
     *
     * @param value The JSON string value to be assigned.
     * @throws IllegalArgumentException If the provided value is not a valid JSON string.
     */
    public void setValue(String value) throws IllegalArgumentException {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Provided value is not a JSON string");
        }
        this.value = value;
    }
    /**
     * Constructs a new `JsonString` object with the provided value.
     *
     * @param value The JSON string value.
     * @throws IllegalArgumentException If the provided value is not a valid JSON string.
     */
    public JsonString(String value) throws IllegalArgumentException {
        setValue(value);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        return value;
    }

    public String getValue() {
        return getValue(null);
    }
}
