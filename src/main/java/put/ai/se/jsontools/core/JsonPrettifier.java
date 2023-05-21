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
        String outputString = gson.toJson(je);
        return outputString;
    }
}
