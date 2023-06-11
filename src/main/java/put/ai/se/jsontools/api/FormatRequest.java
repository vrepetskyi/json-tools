package put.ai.se.jsontools.api;

import com.google.gson.JsonObject;

import put.ai.se.jsontools.core.format.FormatArguments;

public class FormatRequest {
    private JsonObject source;
    private FormatArguments arguments;

    public JsonObject getSource() {
        return source;
    }

    public FormatArguments getArguments() {
        return arguments;
    }
}
