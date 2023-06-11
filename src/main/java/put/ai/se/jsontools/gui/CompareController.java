package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.LinkedHashSet;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.compare.CompareArguments;
import put.ai.se.jsontools.core.compare.CompareMode;
import put.ai.se.jsontools.core.compare.StringComparer;

public class CompareController {
    @FXML
    TextArea s1;
    @FXML
    TextArea s2;
    @FXML
    RadioButton identical;
    @FXML
    TextArea resultOutput;
    @FXML
    TextArea resultMarks;

    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - compare lines");

        resultMarks.scrollTopProperty().bindBidirectional(s1.scrollTopProperty());
        s2.scrollTopProperty().bindBidirectional(resultMarks.scrollTopProperty());
    }

    @FXML
    private void compare() {
        CompareArguments arguments = new CompareArguments();
        arguments.setMode(identical.isSelected() ? CompareMode.identical : CompareMode.different);

        LinkedHashSet<Integer> diffs = StringComparer.getLineNumbers(s1.getText(), s2.getText(), arguments);

        StringBuilder marks = new StringBuilder();
        int i = 1;
        for (int index : diffs) {
            while (i != index) {
                marks.append("\n");
                i++;
            }
            marks.append("***");
        }
        resultMarks.setText(marks.toString());

        resultOutput.setText(diffs.toString());
    }

    @FXML
    private void back() throws IOException {
        GuiController.setRoot("menu");
    }
}
