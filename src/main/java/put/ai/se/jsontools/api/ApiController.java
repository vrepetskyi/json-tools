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

    private static final String API_PREFIX = "/api";
    private static final String FORMAT_ENDPOINT = API_PREFIX + "/format-json";
    private static final String COMPARE_ENDPOINT = API_PREFIX + "/compare-strings";

    private static HttpHandler createHandler(String path, EndpointResolver resolver) {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) {
                String ip = "unknown";
                int resCode = 200;
                String plainResBody = "";

                try {
                    // Parse
                    String temp = null;
                    if (exchange.getRequestHeaders() != null)
                        temp = exchange.getRequestHeaders().getFirst("X-FORWARDED-FOR");
                    if (temp == null && exchange.getRemoteAddress() != null)
                        temp = exchange.getRemoteAddress().toString();
                    if (temp != null)
                        ip = temp;

                    InputStream reqBody = exchange.getRequestBody();

                    String plainReqBody;
                    try (Scanner scanner = new Scanner(reqBody).useDelimiter("\\A")) {
                        plainReqBody = scanner.hasNext() ? scanner.next() : "";
                    }

                    logger.info("{} " + path + " {}\n{}", ip, exchange.getRequestMethod(), plainReqBody);

                    if ("POST".equals(exchange.getRequestMethod())) {
                        // Resolve
                        try {
                            plainResBody = resolver.resolve(plainReqBody);
                        } catch (IllegalArgumentException e) {
                            resCode = 400;
                            plainResBody = e.getMessage();
                        }
                    } else {
                        resCode = 405;
                        plainResBody = "Invalid request method";
                    }
                } catch (Throwable e) {
                    resCode = 500;
                    plainResBody = "Failed to process the request";
                    logger.error(ip + " " + path + " " + plainResBody, e);
                }

                try {
                    // Send
                    OutputStream resBody = exchange.getResponseBody();
                    byte[] resBodyBytes = plainResBody.getBytes();
                    exchange.sendResponseHeaders(resCode, resBodyBytes.length);
                    resBody.write(resBodyBytes);
                    resBody.flush();
                    logger.info("{} " + path + " {}\n{}", ip, resCode, plainResBody);
                } catch (Throwable e) {
                    resCode = 500;
                    plainResBody = "Failed to send the response";
                    logger.error(ip + " " + path + " " + plainResBody, e);
                }

                // Close
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
            logger.error("Failed to start an HTTP server", e);
        }
    }
}
