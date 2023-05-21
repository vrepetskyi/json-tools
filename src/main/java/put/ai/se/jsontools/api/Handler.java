package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import put.ai.se.jsontools.core.JsonString;

public class Handler {
    public static void handle(HttpExchange exchange) throws IOException {
        String response = null;

        if ("GET".equals(exchange.getRequestMethod())) {
            String respText = "Invalid JSON";
            InputStream in = exchange.getRequestBody();
            OutputStream output = exchange.getResponseBody();
            Scanner s = new Scanner(in).useDelimiter("\\A");
            respText = s.hasNext() ? s.next() : "";

            exchange.sendResponseHeaders(200, respText.getBytes().length);
            output.write(respText.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}