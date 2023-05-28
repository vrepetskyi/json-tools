package put.ai.se.jsontools.core.format;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonPrettifier extends JsonFormatter {
    public JsonPrettifier(FormattableJson source) {
        super(source);
    }

    @Override
    public String getProcessed(FormatArguments arguments) {
        String processed = super.getProcessed(arguments);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(processed);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
