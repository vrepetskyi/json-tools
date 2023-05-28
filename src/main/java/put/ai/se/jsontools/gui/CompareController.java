package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.LinkedHashSet;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import put.ai.se.jsontools.core.compare.CompareArguments;
import put.ai.se.jsontools.core.compare.StringComparer;

public class CompareController {
    @FXML
    TextArea s1, s2, resultOutput, resultMarks;
    @FXML
    RadioButton identical;
    @FXML
    RadioButton different;
    @FXML
    ToggleGroup compareMode;

    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - compare lines");
    }

    @FXML
    private void compare() throws IOException {
        CompareArguments args = new CompareArguments();

        args.setString1(s1.getText());
        args.setString2(s2.getText());
        args.setReturnIdentical(identical.isSelected());

        LinkedHashSet<Integer> diffs = StringComparer.getLineNumbers(args);

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
