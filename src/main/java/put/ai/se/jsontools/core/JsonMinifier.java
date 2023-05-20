package put.ai.se.jsontools.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonMinifier extends JsonFormattableDecorator {
    public JsonMinifier(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(value, JsonElement.class);
        value = gson.toJson(jsonElement);
        return value;
    }
}
