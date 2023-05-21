package put.ai.se.jsontools.api;

import java.io.*;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;

public class Handler {
    public static void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            String respText = "Invalid JSON";
            InputStream input = exchange.getRequestBody();
            OutputStream output = exchange.getResponseBody();

            Scanner s = new Scanner(input).useDelimiter("\\A");
            String json = s.hasNext() ? s.next() : "";

            JsonCommand jsonCommand = Parser.parse(json);
            respText = jsonCommand.processData();

            exchange.sendResponseHeaders(200, respText.getBytes().length);
            output.write(respText.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}