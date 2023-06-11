package put.ai.se.jsontools;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import put.ai.se.jsontools.api.ApiController;
import put.ai.se.jsontools.gui.GuiController;

public class JsonTools {
    private static final Logger logger = LoggerFactory.getLogger(JsonTools.class);

    private static Thread startThread(Runnable controller, String name) {
        Thread thread = new Thread(controller);
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.error("An unhandled " + name + " exception", e);
            }
        });
        thread.start();
        return thread;
    }

    public static void main(String[] args) {
        startThread(new ApiController(), "API");
        startThread(new GuiController(), "GUI");
    }
}
