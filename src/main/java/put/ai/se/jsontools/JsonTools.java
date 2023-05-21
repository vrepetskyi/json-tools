package put.ai.se.jsontools;

import put.ai.se.jsontools.api.ApiController;
import put.ai.se.jsontools.gui.GuiController;

public class JsonTools {
    public static void main(String[] args) {
       Thread api_thread = new Thread() {
            @Override
            public void run() {
                ApiController api = new ApiController();
                api.run();
            }
       };
       api_thread.start();

        Thread app = new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(GuiController.class);
            }
        };
        app.start();
    }
}
