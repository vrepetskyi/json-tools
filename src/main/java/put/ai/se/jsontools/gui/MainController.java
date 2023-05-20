package put.ai.se.jsontools.gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController {
    @FXML
    private void switchToFormat() throws IOException {
        GuiController.setRoot("format");
    }

    @FXML
    private void switchToCompare() throws IOException {
        GuiController.setRoot("compare");
    }
}
