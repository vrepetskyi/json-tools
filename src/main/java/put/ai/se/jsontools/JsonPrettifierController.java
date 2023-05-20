package put.ai.se.jsontools;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.JsonFormatParams;
import put.ai.se.jsontools.core.JsonFormattable;
import put.ai.se.jsontools.core.JsonPrettifier;
import put.ai.se.jsontools.core.JsonString;

import java.io.IOException;
import java.util.LinkedHashSet;

import static put.ai.se.jsontools.core.FilterMode.*;

public class JsonPrettifierController {
    @FXML
    TextArea originalJson;
    @FXML
    TextArea prettyJson;

    @FXML
    public void prettifyJson() {
        prettyJson.setText("");
        String str = originalJson.getText();

        LinkedHashSet<String> lHS = new LinkedHashSet<String>();
        lHS.add(str);

        JsonFormatParams params = new JsonFormatParams(Include, lHS);

        JsonString json = new JsonString(str);
        JsonPrettifier prettifier = new JsonPrettifier(json);
        prettyJson.setText(prettifier.getValue(params));
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
