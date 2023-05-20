package put.ai.se.jsontools.core;

import com.google.gson.*;

public class JsonMinifier extends JsonFormattableDecorator
{
    public JsonMinifier(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        String value = super.getValue(params);
        try {
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(value, JsonElement.class);
            value = gson.toJson(jsonElement);
        } catch (JsonSyntaxException e) {
            System.out.println("Invalid JSON format");
        }
        return value;
    }
}
