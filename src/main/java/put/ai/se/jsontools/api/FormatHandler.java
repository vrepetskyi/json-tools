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

    private static void sendResponse(HttpExchange exchange, int resCode, String plainResBody, String ip)
            throws IOException {
        OutputStream resBody = exchange.getResponseBody();

        byte[] resBodyBytes = plainResBody.getBytes();
        exchange.sendResponseHeaders(resCode, resBodyBytes.length);
        resBody.write(resBodyBytes);
        resBody.flush();
        exchange.close();

        logger.info("{} /api/format response {}\n{}", ip, resCode, plainResBody);
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

        logger.info("{} /api/format request {}\n{}", ip, exchange.getRequestMethod(), plainReqBody);

        if (!"POST".equals(exchange.getRequestMethod())) {
            sendResponse(exchange, 405, "Invalid request method", ip);
            return;
        }

        String plainResBody;
        int resCode;

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

            resCode = 200;
            plainResBody = JsonFormatter.format(parsedReq.getSource().toString(), parsedReq.getParams());
        } catch (IllegalArgumentException e) {
            resCode = 400;
            plainResBody = e.getMessage();
        } catch (Throwable e) {
            logger.error("{} /api/format error\n{}", ip, e);

            resCode = 500;
            plainResBody = "Unexpected error. Please, contact the support";
        }

        sendResponse(exchange, resCode, plainResBody, ip);
    }
}