package put.ai.se.jsontools.core;

import javafx.scene.control.Alert;

import java.io.IOException;

public class JsonString implements JsonFormattable {
    private String value;

    public static boolean isValid(String value) {
        // TODO: check if value is a json string
        return true;
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
