package put.ai.se.jsontools;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.JsonFormatParams;
import put.ai.se.jsontools.core.JsonFormattable;
import put.ai.se.jsontools.core.JsonPrettifier;

import java.io.IOException;

public class JsonPrettifierController {
    @FXML
    TextArea originalJson;
    @FXML
    TextArea prettyJson;

    @FXML
    public void prettifyJson() {
        prettyJson.setText("");
        JsonPrettifier prettifier = new JsonPrettifier(new JsonFormattable() {
            @Override
            public String getValue(JsonFormatParams params) {
                return null;
            }
        });
        prettyJson.setText(prettifier.prettifyJson(originalJson.getText()));
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
