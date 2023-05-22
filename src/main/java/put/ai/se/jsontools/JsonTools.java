package put.ai.se.jsontools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import put.ai.se.jsontools.api.ApiController;
import put.ai.se.jsontools.gui.GuiController;

public class JsonTools {
    private static final Logger logger = LoggerFactory.getLogger(JsonTools.class);

    public static void main(String[] args) {
        try {
            new Thread(new ApiController()).start();
        } catch (Throwable e) {
            logger.error("Error in ApiController", e);
        }

        try {
            new Thread(new GuiController()).start();
        } catch (Throwable e) {
            logger.error("Error in GuiController", e);
        }
    }
}
