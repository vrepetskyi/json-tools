package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ApiController implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    static final String API_PREFIX = "/api";
    static final String FORMAT_ENDPOINT = API_PREFIX + "/format-json";
    static final String COMPARE_ENDPOINT = API_PREFIX + "/compare-strings";

    static final String SERVER_START_FAILURE = "Failed to start an HTTP server";
    static final String BAD_REQUEST_METHOD = "Invalid request method";
    static final String PROCESSING_FAILURE = "Failed to process the request";
    static final String RESPONSE_FAILURE = "Failed to send the response";

    static HttpHandler createHandler(String path, EndpointResolver resolver) {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) {
                String ip = exchange.getRequestHeaders().getFirst("X-FORWARDED-FOR");
                if (ip == null)
                    ip = exchange.getRemoteAddress().toString();

                int resCode;
                String plainResBody;
                try {
                    InputStream reqBody = exchange.getRequestBody();

                    String plainReqBody;
                    try (Scanner scanner = new Scanner(reqBody).useDelimiter("\\A")) {
                        plainReqBody = scanner.hasNext() ? scanner.next() : "";
                    }

                    logger.info("{} {} {}\n{}", ip, path, exchange.getRequestMethod(), plainReqBody);

                    if ("POST".equals(exchange.getRequestMethod())) {
                        try {
                            String result = resolver.resolve(plainReqBody);
                            resCode = 200;
                            plainResBody = result;
                        } catch (IllegalArgumentException e) {
                            resCode = 400;
                            plainResBody = e.getMessage();
                        }
                    } else {
                        resCode = 405;
                        plainResBody = BAD_REQUEST_METHOD;
                    }
                } catch (Throwable e) {
                    resCode = 500;
                    plainResBody = PROCESSING_FAILURE;
                    logger.error(ip + " " + path + " " + plainResBody, e);
                }

                try {
                    OutputStream resBody = exchange.getResponseBody();
                    byte[] resBodyBytes = plainResBody.getBytes();
                    exchange.sendResponseHeaders(resCode, resBodyBytes.length);
                    resBody.write(resBodyBytes);
                    resBody.flush();
                    logger.info("{} {} {}\n{}", ip, path, resCode, plainResBody);
                } catch (Throwable e) {
                    logger.error(ip + " " + path + " " + RESPONSE_FAILURE, e);
                }

                exchange.close();
            }
        };
    }

    @Override
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext(FORMAT_ENDPOINT, createHandler(FORMAT_ENDPOINT, new FormatResolver()));
            server.createContext(COMPARE_ENDPOINT, createHandler(COMPARE_ENDPOINT, new CompareResolver()));
            server.start();
        } catch (IOException e) {
            logger.error(SERVER_START_FAILURE, e);
        }
    }
}
