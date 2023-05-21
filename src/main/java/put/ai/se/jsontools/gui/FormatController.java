package put.ai.se.jsontools.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.JsonFormatParamsBuilder;
import put.ai.se.jsontools.core.JsonFormatter;

public class FormatController {
    @FXML
    TextArea source;
    @FXML
    TextArea result;

    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - format");
    }

    @FXML
    public void format() {
        result.setText("");

        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();

        // TODO: further params are for testing only
        params.setPrettify(true);
        // params.setFilterMode(JsonFilterMode.Include);
        // params.setFilterMode(JsonFilterMode.Exclude);
        // params.setFilterKeys(new LinkedHashSet<>(Arrays.asList("source")));

        try {
            result.setText(JsonFormatter.format(source.getText(), params));
        } catch (IllegalArgumentException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Formatting error");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void back() throws IOException {
        GuiController.setRoot("menu");
    }
}
