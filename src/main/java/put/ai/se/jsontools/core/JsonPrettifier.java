package put.ai.se.jsontools.core;

import com.google.gson.*;

public class JsonPrettifier extends JsonFormattableDecorator {
    private String prettyJsonString;
    private JsonString jsonString;

    public JsonPrettifier(JsonFormattable source) {
        super(source);
    }

    public String prettifyJson(String value) {
        jsonString = new JsonString(value);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(jsonString.getValue());
        prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
