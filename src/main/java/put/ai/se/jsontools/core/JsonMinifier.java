package put.ai.se.jsontools.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * The JsonMinifier class is responsible for minifying JSON data by removing whitespace and unnecessary characters.
 * It decorates a JsonFormattable object and provides the minified JSON representation.
 */
public class JsonMinifier extends JsonFormattableDecorator {

    /**
     * Constructs a JsonMinifier object with the specified source.
     *
     * @param source the JsonFormattable object to be decorated and minified
     */
    public JsonMinifier(JsonFormattable source) {
        super(source);
    }

    /**
     * Gets the minified JSON representation from the decorated source.
     *
     * @param params the JsonFormatParams object containing formatting parameters (ignored in minification)
     * @return the minified JSON representation
     */
    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(value, JsonElement.class);
        value = gson.toJson(jsonElement);
        return value;
    }
}
