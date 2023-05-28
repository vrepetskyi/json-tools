package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

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

    public static void sendResponse(HttpExchange exchange, int resCode, String plainResBody, String ip)
            throws IOException {
        OutputStream resBody = exchange.getResponseBody();

        byte[] resBodyBytes = plainResBody.getBytes();
        try {
            exchange.sendResponseHeaders(resCode, resBodyBytes.length);
            resBody.write(resBodyBytes);
            resBody.flush();
        } catch (Exception e) {
            throw e;
        }
        exchange.close();
        // TODO: typo
        logger.info("{} /api/format response {}\n{}", ip, resCode, plainResBody);
    }

    private static HttpHandler createHandler(String path, EndpointResolver resolver) {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                sendResponse(exchange, 0, resolver.resolve(path), path);
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
            logger.error("An unhandled API error occurred", e);
        }
    }
}
