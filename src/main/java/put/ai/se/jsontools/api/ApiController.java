package put.ai.se.jsontools.api;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiController implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Override
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/api/format", FormatHandler::handle);
            server.createContext("/api/compare", CompareHandler::handle);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            logger.error("Error occurred while starting the server", e);
        }
    }
}
