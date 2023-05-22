package put.ai.se.jsontools.api;

import com.google.gson.JsonObject;

import put.ai.se.jsontools.core.JsonFormatParams;

public class FormatRequest {
    private JsonObject source;
    private JsonFormatParams params;

    public JsonObject getSource() {
        return source;
    }

    public JsonFormatParams getParams() {
        return params;
    }
}
