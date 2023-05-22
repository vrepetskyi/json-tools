package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import put.ai.se.jsontools.core.DiffFinder;

public class CompareHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
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

        CompareRequest parsedReq = new Gson().fromJson(plainReqBody, CompareRequest.class);

        try {
            String result = DiffFinder.getLineNumbers(parsedReq.getS1(), parsedReq.getS2()).toString();
            byte[] resultBytes = result.getBytes();
            exchange.sendResponseHeaders(200, resultBytes.length);
            resBody.write(resultBytes);
        } catch (Throwable e) {
            logger.error("Unexpected server error occurred", e);

            byte[] errorBytes = "Unexpected server error. Please, contact the support".getBytes();
            exchange.sendResponseHeaders(500, errorBytes.length);
            resBody.write(errorBytes);
        }

        resBody.flush();
        exchange.close();
    }
}