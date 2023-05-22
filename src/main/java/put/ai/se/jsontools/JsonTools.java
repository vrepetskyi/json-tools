package put.ai.se.jsontools;

import put.ai.se.jsontools.gui.GuiController;

public class JsonTools {
    public static void main(String[] args) {
        // TODO: ApiController thread

        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(GuiController.class);
            }
        }.start();
    }
}
