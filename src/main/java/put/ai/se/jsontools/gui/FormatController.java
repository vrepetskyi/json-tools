package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.JsonFormatParamsBuilder;
import put.ai.se.jsontools.core.JsonFormatter;

public class FormatController {
    @FXML
    TextArea source;
    @FXML
    TextArea result;
    @FXML
    TextArea keysTextField;
    @FXML
    RadioButton exclude;
    @FXML
    RadioButton prettify;

    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - format");
    }

    @FXML
    public void format() {
        result.setText("");

        List<String> separated = Arrays.asList(keysTextField.getText().split("\n"));
        LinkedHashSet<String> keys = new LinkedHashSet<>(separated);

        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();

        params.setFilterKeys(keys);
        params.setExclude(exclude.isSelected());
        params.setPrettify(prettify.isSelected());

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
