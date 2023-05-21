package put.ai.se.jsontools;

import put.ai.se.jsontools.api.ApiController;
import put.ai.se.jsontools.gui.GuiController;

public class JsonTools {
    public static void main(String[] args) {
        try {
            new Thread(new ApiController()).start();
        } catch (Throwable e) {
            // TODO: log api start error
        }

        try {
            new Thread(new GuiController()).start();
        } catch (Throwable e) {
            // TODO: log gui start error
        }
    }
}
