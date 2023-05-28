package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class ApiController implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Override
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/api/format", FormatHandler::handle);
            server.createContext("/api/compare-lines", CompareHandler::handle);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            logger.error("An unhandled API error occurred", e);
        }
    }

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

        logger.info("{} /api/format response {}\n{}", ip, resCode, plainResBody);
    }
}
