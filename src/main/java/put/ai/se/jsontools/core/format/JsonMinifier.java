package put.ai.se.jsontools.core.format;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * The JsonMinifier class is responsible for minifying JSON data by removing
 * whitespace and unnecessary characters.
 * It decorates a JsonFormattable object and provides the minified JSON
 * representation.
 */
public class JsonMinifier extends JsonFormatter {
    /**
     * Constructs a JsonMinifier object with the specified source.
     *
     * @param source the JsonFormattable object to be decorated and minified
     */
    public JsonMinifier(FormattableJson source) {
        super(source);
    }

    /**
     * Gets the minified JSON representation from the decorated source.
     *
     * @param arguments the JsonFormatParams object containing formatting parameters
     *                  (ignored in minification)
     * @return the minified JSON representation
     */
    @Override
    public String getProcessed(FormatArguments arguments) {
        String processed = super.getProcessed(arguments);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(processed, JsonElement.class);
        processed = gson.toJson(jsonElement);
        return processed;
    }
}
