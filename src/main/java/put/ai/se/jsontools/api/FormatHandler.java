package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import put.ai.se.jsontools.core.JsonFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatHandler {
    private static final Logger logger = LoggerFactory.getLogger(FormatHandler.class);

    public static void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            logger.warn("Invalid request method: {}", exchange.getRequestMethod());
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        InputStream reqBody = exchange.getRequestBody();
        OutputStream resBody = exchange.getResponseBody();

        Scanner scanner = new Scanner(reqBody).useDelimiter("\\A");
        String plainReqBody = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        try {
            FormatRequest parsedReq;
            try {
                parsedReq = new Gson().fromJson(plainReqBody, FormatRequest.class);
            } catch (Throwable e) {
                throw new IllegalArgumentException("\"source\" and \"params\" should be JSON objects", e);
            }

            if (parsedReq.getSource() == null)
                throw new IllegalArgumentException("\"source\" is not specified");

            if (parsedReq.getParams() == null)
                throw new IllegalArgumentException("\"params\" are not specified");

            String result = JsonFormatter.format(parsedReq.getSource().toString(), parsedReq.getParams());

            byte[] resultBytes = result.getBytes();
            exchange.sendResponseHeaders(200, resultBytes.length);
            resBody.write(resultBytes);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid request: {}", e.getMessage());

            byte[] errorBytes = e.getMessage().getBytes();
            exchange.sendResponseHeaders(400, errorBytes.length);
            resBody.write(errorBytes);
        } catch (Throwable e) {
            logger.error("Unexpected error occurred", e);

            byte[] errorBytes = "Unexpected error. Please, contact the support".getBytes();
            exchange.sendResponseHeaders(500, errorBytes.length);
            resBody.write(errorBytes);
        }

        resBody.flush();
        exchange.close();
    }
}