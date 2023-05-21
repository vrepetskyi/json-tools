package put.ai.se.jsontools.core;

import java.util.LinkedHashSet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonFilter extends JsonFormattableDecorator {
    public JsonFilter(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);

        JsonFilterParams filter = params.getFilter();
        if (filter == null)
            return value;

        Gson gson = new Gson();
        JsonObject filtered = gson.fromJson(value, JsonObject.class);

        LinkedHashSet<String> filterKeys = filter.getKeys();
        if (filterKeys == null)
            return filter.getExclude() ? value : "{}";

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
