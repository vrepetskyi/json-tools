package put.ai.se.jsontools.core;

import java.io.IOException;
import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class JsonString implements JsonFormattable {
    private String value;

    public static boolean isValid(String value) {
        try {
            try (JsonReader reader = new JsonReader(new StringReader(value))) {
                new Gson().getAdapter(JsonObject.class).read(reader);
                reader.hasNext();
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public void setValue(String value) throws IllegalArgumentException {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Provided value is not a JSON string");
        }

        this.value = value;
    }

    public JsonString(String value) throws IllegalArgumentException {
        setValue(value);
    }

    @Override
    public String getValue(JsonFormatParams params) {
        return value;
    }

    public String getValue() {
        return getValue(null);
    }
}
