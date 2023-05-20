package put.ai.se.jsontools.core;

import com.google.gson.*;
import java.util.HashSet;
import java.util.Set;

public class JsonFilter extends JsonFormattableDecorator {

    public JsonFilter(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(value, JsonElement.class);
        JsonElement clone = JsonParser.parseString(jsonElement.toString());

        if (params.getFilterMode() == JsonFilterMode.Include) {
            Set<String> keys = new HashSet<>(clone.getAsJsonObject().keySet());
            for (String entry : keys) {
                if (!params.getFilterKeys().contains(entry)) {
                    clone.getAsJsonObject().remove(entry);
                }
            }
            value = gson.toJson(clone);
        } else if (params.getFilterMode() == JsonFilterMode.Exclude) {
            for (String key : params.getFilterKeys()) {
                if (clone.getAsJsonObject().has(key)) {
                    clone.getAsJsonObject().remove(key);
                }
            }
            value = gson.toJson(clone);
        } else {
            throw new IllegalArgumentException("Invalid filter mode");
        }

        return value;
    }
}
