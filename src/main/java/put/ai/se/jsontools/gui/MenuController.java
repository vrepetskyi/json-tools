package put.ai.se.jsontools.gui;

import java.io.IOException;
import javafx.fxml.FXML;

public class MenuController {
    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools");
    }

    @FXML
    private void format() throws IOException {
        GuiController.setRoot("format");
    }

    @FXML
    private void compare() throws IOException {
        GuiController.setRoot("compare");
    }
}
