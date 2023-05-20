package put.ai.se.jsontools.core;

import com.google.gson.*;

public class JsonPrettifier extends JsonFormattableDecorator {
    public JsonPrettifier(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(value);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
