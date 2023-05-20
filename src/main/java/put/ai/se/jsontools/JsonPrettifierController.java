package put.ai.se.jsontools;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.JsonFormatParamsBuilder;
import put.ai.se.jsontools.core.JsonFormatter;
import put.ai.se.jsontools.core.JsonStyleMode;

import java.io.IOException;

public class JsonPrettifierController {
    @FXML
    TextArea originalJson;
    @FXML
    TextArea prettyJson;

    @FXML
    public void prettifyJson() {
        prettyJson.setText("");
        String source = originalJson.getText();

        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();
        params.setStyleMode(JsonStyleMode.Prettify);

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
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
