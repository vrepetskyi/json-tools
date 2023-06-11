package put.ai.se.jsontools.core.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * The JsonPrettifier class provides functionality to prettify (format with
 * indentation) JSON data.
 * It acts as a decorator for a JsonFormattable object and modifies its behavior
 * accordingly.
 */
public class JsonPrettifier extends JsonFormatter {
    /**
     * Constructs a new JsonPrettifier object with the specified source.
     *
     * @param source the JsonFormattable object to be prettified
     */
    public JsonPrettifier(FormattableJson source) {
        super(source);
    }

    /**
     * Returns the prettified JSON value.
     *
     * @param params the JsonFormatParams (not used in this implementation)
     * @return the prettified JSON value
     */
    @Override
    public String getProcessed(FormatArguments arguments) {
        String processed = super.getProcessed(arguments);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(processed);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
