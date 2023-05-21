package put.ai.se.jsontools.gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class CompareController {
    @FXML
    private void switchToMain() throws IOException {
        GuiController.setRoot("main");
    }
}
