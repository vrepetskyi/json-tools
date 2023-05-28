package put.ai.se.jsontools.core.format;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonMinifier extends JsonFormatter {
    public JsonMinifier(FormattableJson source) {
        super(source);
    }

    @Override
    public String getProcessed(FormatArguments arguments) {
        String processed = super.getProcessed(arguments);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(processed, JsonElement.class);
        processed = gson.toJson(jsonElement);
        return processed;
    }
}
