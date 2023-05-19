package put.ai.se.jsontools.core;

import com.google.gson.*;
import java.util.HashSet;
import java.util.Set;

public class JsonFilter extends JsonFormattableDecorator {
    
    public JsonFilter(JsonFormattable source) {
        super(source);
    }

    @Override
    public String getValue(JsonFormatParams params) {

        String value = super.getValue(params); 
        String jsonString = "{\n" + // to be replaced
        "    \"name\": \"John\",\n" +
        "    \"age\": 30,\n" +
        "    \"city\": \"New York\",\n" +
        "    \"country\": \"USA\",\n" +
        "    \"attributes\": {\n" +
        "        \"height\": 180,\n" +
        "        \"weight\": 75\n" +
        "    }\n" +
        "}\n";
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
        JsonElement clone = JsonParser.parseString(jsonElement.toString());

        if (params.getFilterMode() == FilterMode.Include) {
            Set<String> keys = new HashSet<>(clone.getAsJsonObject().keySet());
            for (String entry: keys) {
                if (!params.getFilterKeys().contains(entry)) {
                    clone.getAsJsonObject().remove(entry);
                }
            }
            value = gson.toJson(clone);
        } 
        else if (params.getFilterMode() == FilterMode.Exclude) 
        {
            for (String key : params.getFilterKeys()) {
                if (clone.getAsJsonObject().has(key)) {
                    clone.getAsJsonObject().remove(key);
                }
            }
            value = gson.toJson(clone);
        } else {
            System.out.println("Invalid filter mode"); // it's rather impossible to get to this part of code
        }
        
        return value;
    }
}
