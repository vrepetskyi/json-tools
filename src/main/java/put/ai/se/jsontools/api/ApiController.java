package put.ai.se.jsontools.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

public class ApiController implements Runnable {
    @Override
    public void run() {
        try {
            int serverPort = 8080;

            HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
            server.createContext("/api/format", Handler::handle);
            server.setExecutor(null); // creates a default executor
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
