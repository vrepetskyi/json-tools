package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.format.FilterArguments;
import put.ai.se.jsontools.core.format.FormatArguments;
import put.ai.se.jsontools.core.format.FormatDirector;

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
        FormatArguments arguments = new FormatArguments();
        FilterArguments filterArguments = arguments.getFilter();

        LinkedHashSet<String> filterKeys = new LinkedHashSet<>(Arrays.asList(keysTextField.getText().split("\n")));
        filterArguments.setKeys(filterKeys.isEmpty() ? null : filterKeys);
        filterArguments.setExclude(exclude.isSelected());
        arguments.setPrettify(prettify.isSelected());

        try {
            result.setText(FormatDirector.formatJson(source.getText(), arguments));
        } catch (IllegalArgumentException e) {
            result.setText(null);
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
