package put.ai.se.jsontools.core;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import javafx.scene.control.Alert;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static com.google.gson.stream.JsonToken.END_DOCUMENT;

public class JsonValidator {
    private static final Gson gson = new Gson();

    private JsonValidator(){}

    public static boolean isJsonValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch(com.google.gson.JsonSyntaxException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Something is wrong with the JSON that you've provided");
            errorAlert.showAndWait();
            return false;
        }
    }
}
