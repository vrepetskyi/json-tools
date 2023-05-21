package put.ai.se.jsontools.api;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class ApiController implements Runnable {
    @Override
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/api/format", FormatHandler::handle);
            server.createContext("/api/compare", CompareHandler::handle);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            // TODO: log error
        }
    }
}
