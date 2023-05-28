package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import put.ai.se.jsontools.core.format.FormatDirector;

public class FormatHandler {
    private static final Logger logger = LoggerFactory.getLogger(FormatHandler.class);

    public static void handle(HttpExchange exchange) throws IOException {
        String ip = exchange.getRequestHeaders().getFirst("X-FORWARDED-FOR");
        if (ip == null) {
            ip = exchange.getRemoteAddress().toString();
        }

        InputStream reqBody = exchange.getRequestBody();

        String plainReqBody;
        try (Scanner scanner = new Scanner(reqBody).useDelimiter("\\A")) {
            plainReqBody = scanner.hasNext() ? scanner.next() : "";
        }

        logger.info("{} /api/format request {}\n{}", ip, exchange.getRequestMethod(), plainReqBody);

        if (!"POST".equals(exchange.getRequestMethod())) {
            ApiController.sendResponse(exchange, 405, "Invalid request method", ip);
            return;
        }

        String plainResBody;
        int resCode;

        try {
            FormatRequest parsedReq;
            try {
                parsedReq = new Gson().fromJson(plainReqBody, FormatRequest.class);
            } catch (Throwable e) {
                throw new IllegalArgumentException("Both \"source\" and \"arguments\" keys should be JSON objects", e);
            }

            if (parsedReq.getSource() == null)
                throw new IllegalArgumentException("Key \"source\" is required");

            if (parsedReq.getArguments() == null)
                throw new IllegalArgumentException("Key \"arguments\" is required");

            resCode = 200;
            plainResBody = FormatDirector.formatJson(parsedReq.getSource(), parsedReq.getArguments());
        } catch (IllegalArgumentException e) {
            resCode = 400;
            plainResBody = e.getMessage();
        } catch (Throwable e) {
            logger.error(ip + " /api/format error", e);
            resCode = 500;
            plainResBody = "Unexpected error. Please, contact the support";
        }

        ApiController.sendResponse(exchange, resCode, plainResBody, ip);
    }
}