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

    private static void sendResponse(HttpExchange exchange, int resCode, String plainResBody, String ip)
            throws IOException {
        OutputStream resBody = exchange.getResponseBody();

        byte[] resBodyBytes = plainResBody.getBytes();
        exchange.sendResponseHeaders(resCode, resBodyBytes.length);
        resBody.write(resBodyBytes);
        resBody.flush();
        exchange.close();

        logger.info("{} /api/compare response {}\n{}", ip, resCode, plainResBody);
    }

    public static void handle(HttpExchange exchange) throws IOException {
        String ip = exchange.getRequestHeaders().getFirst("X-FORWARDED-FOR");
        if (ip == null) {
            ip = exchange.getRemoteAddress().toString();
        }

        InputStream reqBody = exchange.getRequestBody();

        Scanner scanner = new Scanner(reqBody).useDelimiter("\\A");
        String plainReqBody = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        logger.info("{} /api/compare request {}\n{}", ip, exchange.getRequestMethod(), plainReqBody);

        if (!"POST".equals(exchange.getRequestMethod())) {
            sendResponse(exchange, 405, "Invalid request method", ip);
            return;
        }

        CompareRequest parsedReq = new Gson().fromJson(plainReqBody, CompareRequest.class);

        String plainResBody;
        int resCode;

        try {
            resCode = 200;
            plainResBody = DiffFinder.getLineNumbers(parsedReq.getS1(), parsedReq.getS2()).toString();
        } catch (Throwable e) {
            logger.error("{} /api/compare error\n{}", ip, e);

            resCode = 500;
            plainResBody = "Unexpected error. Please, contact the support";
        }

        sendResponse(exchange, resCode, plainResBody, ip);
    }
}