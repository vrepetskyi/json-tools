package put.ai.se.jsontools.core;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonFilter extends JsonFormattableDecorator {
    public JsonFilter(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);

        JsonFilterParams filter = params.getFilter();
        if (filter == null || filter.getMode() == null)
            return value;

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(value, JsonElement.class);
        JsonElement clone = JsonParser.parseString(jsonElement.toString());

        LinkedHashSet<String> filterKeys = filter.getKeys();
        if (filterKeys == null) {
            filterKeys = new LinkedHashSet<>();
        }

        switch (filter.getMode()) {
            case Include:
                Set<String> keys = new HashSet<>(clone.getAsJsonObject().keySet());
                for (String entry : keys) {
                    if (!filterKeys.contains(entry)) {
                        clone.getAsJsonObject().remove(entry);
                    }
                }
                value = gson.toJson(clone);
                break;
            case Exclude:
                for (String key : filterKeys) {
                    if (clone.getAsJsonObject().has(key)) {
                        clone.getAsJsonObject().remove(key);
                    }
                }
                value = gson.toJson(clone);
                break;
        }

        return value;
    }
}
