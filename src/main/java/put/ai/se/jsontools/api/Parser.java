package put.ai.se.jsontools.api;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;

public class Parser {
    public static JsonCommand parse(String json) {
        Gson gson = new Gson();
        JsonCommand command = gson.fromJson(json, JsonCommand.class);

        return command;
    }
}