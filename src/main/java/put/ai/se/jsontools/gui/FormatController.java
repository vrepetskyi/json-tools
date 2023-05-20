package put.ai.se.jsontools.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.JsonFilterMode;
import put.ai.se.jsontools.core.JsonFormatParamsBuilder;
import put.ai.se.jsontools.core.JsonFormatter;
import put.ai.se.jsontools.core.JsonStyleMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class FormatController {
    @FXML
    TextArea originalJson;
    @FXML
    TextArea prettyJson;

    @FXML
    public void prettifyJson() {
        prettyJson.setText("");
        String source = originalJson.getText();

        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();

        // TODO: further params are for testing only
        params.setStyleMode(JsonStyleMode.Prettify);
        // params.setStyleMode(JsonStyleMode.Minify);
        params.setFilterMode(JsonFilterMode.Include);
        // params.setFilterMode(JsonFilterMode.Exclude);
        params.setFilterKeys(new LinkedHashSet<>(Arrays.asList("1")));

        try {
            prettyJson.setText(JsonFormatter.format(source, params));
        } catch (IllegalArgumentException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Formatting error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void switchToMain() throws IOException {
        GuiController.setRoot("main");
    }
}
