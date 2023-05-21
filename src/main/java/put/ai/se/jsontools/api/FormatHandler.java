package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;

public class FormatHandler {
    public static void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
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