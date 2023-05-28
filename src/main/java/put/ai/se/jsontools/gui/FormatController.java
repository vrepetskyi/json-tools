package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import put.ai.se.jsontools.core.JsonFormatParamsBuilder;
import put.ai.se.jsontools.core.JsonFormatter;

public class FormatController {
    @FXML
    TextArea source;
    @FXML
    TextArea result;
    @FXML
    ToggleGroup groupPrettify;
    @FXML
    RadioButton prettify;
    @FXML
    RadioButton minify;
    @FXML
    ToggleGroup groupExclude;
    @FXML
    RadioButton exclude;
    @FXML
    RadioButton include;
    @FXML
    TextField keysTextField;

    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - format");
    }

    @FXML
    public void format() {
        result.setText("");

        List<String> separated = Arrays.asList(keysTextField.getText().split("\\s*,\\s*"));
        LinkedHashSet<String> keys = new LinkedHashSet<>(separated);

        JsonFormatParamsBuilder params = new JsonFormatParamsBuilder();

        params.setFilterKeys(keys);
        params.setExclude(exclude.isSelected());
        params.setPrettify(prettify.isSelected());

        try {
            if (params.getFilter() == null) {
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setHeaderText("You need to choose the key first.");
                warningAlert.setContentText("Choose the key in the list on the right.");
                warningAlert.showAndWait();
            } else {
                result.setText(JsonFormatter.format(source.getText(), params));
            }
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
