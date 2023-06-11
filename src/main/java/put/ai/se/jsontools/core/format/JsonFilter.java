package put.ai.se.jsontools.core.format;

import java.util.LinkedHashSet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * The JsonFilter class provides functionality to filter JSON data based on
 * specified filter parameters.
 * It acts as a decorator for a JsonFormattable object and modifies its behavior
 * accordingly.
 */
public class JsonFilter extends JsonFormatter {
    /**
     * Constructs a new JsonFilter object with the specified source.
     *
     * @param source the JsonFormattable object to be filtered
     */
    public JsonFilter(FormattableJson source) {
        super(source);
    }

    /**
     * Returns the filtered JSON value based on the provided filter parameters.
     *
     * @param params the JsonFormatParams containing the filter parameters
     * @return the filtered JSON value
     */
    @Override
    public String getProcessed(FormatArguments arguments) {
        String processed = super.getProcessed(arguments);

        FilterArguments filter = arguments.getFilter();
        if (filter == null)
            return processed;

        LinkedHashSet<String> filterKeys = filter.getKeys();
        if (filterKeys == null)
            return processed;

        Gson gson = new Gson();
        JsonObject filtered = gson.fromJson(processed, JsonObject.class);

        if (filterKeys.isEmpty())
            return filter.getExclude() ? processed : "{}";

        LinkedHashSet<String> sourceKeys = new LinkedHashSet<>(filtered.keySet());
        if (filter.getExclude()) {
            for (String sourceKey : sourceKeys) {
                if (filterKeys.contains(sourceKey)) {
                    filtered.remove(sourceKey);
                }
            }
        } else {
            for (String sourceKey : sourceKeys) {
                if (!filterKeys.contains(sourceKey)) {
                    filtered.remove(sourceKey);
                }
            }
        }

        return gson.toJson(filtered);
    }
}
