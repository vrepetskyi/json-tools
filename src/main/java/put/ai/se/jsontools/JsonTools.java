package put.ai.se.jsontools;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import put.ai.se.jsontools.api.ApiController;
import put.ai.se.jsontools.gui.GuiController;

public class JsonTools {
    private static final Logger logger = LoggerFactory.getLogger(JsonTools.class);

    private static UncaughtExceptionHandler createExceptionHandler(String message) {
        return new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.error(message, e);
            }
        };
    }

    public static void main(String[] args) {
        Thread api = new Thread(new ApiController());
        api.setUncaughtExceptionHandler(createExceptionHandler("An unhandled API exception"));
        api.start();

        Thread gui = new Thread(new GuiController());
        gui.setUncaughtExceptionHandler(createExceptionHandler("An unhandled GUI exception"));
        gui.start();
    }
}
