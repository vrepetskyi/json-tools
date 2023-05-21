package put.ai.se.jsontools.api;

import com.google.gson.JsonObject;

import put.ai.se.jsontools.core.JsonFilterMode;
import put.ai.se.jsontools.core.JsonFormatParamsBuilder;
import put.ai.se.jsontools.core.JsonFormatter;
import put.ai.se.jsontools.core.JsonString;
import put.ai.se.jsontools.core.JsonStyleMode;

public class JsonCommand {
    private JsonObject source;
    private Options options;

    public String processData() {
        JsonString jsonString = new JsonString(source.toString());
        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();

        if (options.isMinifyOrPrettify().equals("minify")) {
            params.setStyleMode(JsonStyleMode.Minify);
        } else if (options.isMinifyOrPrettify().equals("prettify")) {
            params.setStyleMode(JsonStyleMode.Prettify);
        } else {
            return "\"minifyOrPrettify\" can only be set to \"minify\" or \"prettify\"";
        }

        if (options.getFilter().getMode().equals("include")) {
            params.setFilterMode(JsonFilterMode.Include);
        } else if (options.getFilter().getMode().equals("exclude")) {
            params.setFilterMode(JsonFilterMode.Exclude);
        } else {
            return "\"mode\" can only be set to \"include\" or \"exclude\"";
        }

        params.setFilterKeys(options.getFilter().getKeys());

        try {
            return JsonFormatter.format(jsonString.getValue(), params);
        } catch (IllegalArgumentException e) {
            return "Something is wrong with provided JSON";
        }
    }
}
