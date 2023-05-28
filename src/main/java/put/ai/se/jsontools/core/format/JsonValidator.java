package put.ai.se.jsontools.core.format;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

/**
 * Checks if given input is in correct JSON format.
 */
public class JsonValidator implements FormattableJson {
    private String source;

    /**
     * Checks whether the provided string is a valid JSON string.
     *
     * @param source A string to be checked.
     * @return {@code true} if the provided string is a valid JSON string,
     *         {@code false} otherwise.
     */
    public static boolean isValid(String source) {
        try {
            try (JsonReader reader = new JsonReader(new StringReader(source))) {
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
     * @param source The JSON string value to be assigned.
     * @throws IllegalArgumentException If the provided value is not a valid JSON
     *                                  string.
     */
    public void setSource(String source) throws IllegalArgumentException {
        if (!isValid(source)) {
            throw new IllegalArgumentException("The source is not in JSON format");
        }
        this.source = source;
    }

    /**
     * Constructs a new `JsonString` object with the provided value.
     *
     * @param value The JSON string value.
     * @throws IllegalArgumentException If the provided value is not a valid JSON
     *                                  string.
     */
    public JsonValidator(String source) throws IllegalArgumentException {
        setSource(source);
    }

    @Override
    public String getProcessed(FormatArguments arguments) {
        return source;
    }
}
