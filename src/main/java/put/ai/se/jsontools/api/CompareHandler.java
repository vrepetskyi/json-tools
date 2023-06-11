package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import put.ai.se.jsontools.core.compare.CompareArguments;
import put.ai.se.jsontools.core.compare.StringComparer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompareHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

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

        String reqMethod = exchange.getRequestMethod();

        logger.info("{} /api/compare-lines request {}\n{}", ip, reqMethod, plainReqBody);

        if (!"POST".equals(reqMethod)) {
            ApiController.sendResponse(exchange, 405, "Invalid request method", ip);
            return;
        }

        CompareArguments parsedReq = new Gson().fromJson(plainReqBody, CompareArguments.class);

        String plainResBody;
        int resCode;

        try {
            resCode = 200;
            plainResBody = StringComparer.getLineNumbers(parsedReq).toString();
        } catch (IllegalArgumentException e) {
            resCode = 400;
            plainResBody = e.getMessage();
        } catch (Throwable e) {
            logger.error(ip + " /api/compare-lines error", e);
            resCode = 500;
            plainResBody = "Unexpected error. Please, contact the support";
        }

        ApiController.sendResponse(exchange, resCode, plainResBody, ip);
    }
}