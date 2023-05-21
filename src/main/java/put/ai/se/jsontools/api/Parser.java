package put.ai.se.jsontools.api;

import com.google.gson.Gson;

public class Parser {
    public static JsonCommand parse(String json) {
        Gson gson = new Gson();
        JsonCommand command = gson.fromJson(json, JsonCommand.class);

        return command;
    }
}