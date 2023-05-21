package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import put.ai.se.jsontools.core.JsonFormatter;

public class FormatHandler {
    public static void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        InputStream reqBody = exchange.getRequestBody();
        OutputStream resBody = exchange.getResponseBody();

        Scanner scanner = new Scanner(reqBody).useDelimiter("\\A");
        String plainReqBody = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        FormatRequest parsedReq = new Gson().fromJson(plainReqBody, FormatRequest.class);

        try {
            String result = JsonFormatter.format(parsedReq.getSource().toString(), parsedReq.getParams());
            byte[] resultBytes = result.getBytes();
            exchange.sendResponseHeaders(200, resultBytes.length);
            resBody.write(resultBytes);
        } catch (IllegalArgumentException e) {
            byte[] errorBytes = e.getMessage().getBytes();
            exchange.sendResponseHeaders(400, errorBytes.length);
            resBody.write(errorBytes);
        } catch (Throwable e) {
            // TODO: log

            byte[] errorBytes = "Unexpected server error. Please, contact the support.".getBytes();
            exchange.sendResponseHeaders(500, errorBytes.length);
            resBody.write(errorBytes);
        }

        resBody.flush();
        exchange.close();
    }
}