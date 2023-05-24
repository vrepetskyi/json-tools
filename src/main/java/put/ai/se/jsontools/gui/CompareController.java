package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import put.ai.se.jsontools.core.compare.CompareArguments;
import put.ai.se.jsontools.core.compare.StringComparer;

public class CompareController {
    @FXML
    TextArea s1, s2, resultOutput, resultMarks;
    @FXML
    RadioButton EqualityCheck, DifferenceCheck;
    @FXML
    ToggleGroup CompareMode = new ToggleGroup();
    @FXML
    Label resultLabel = new Label("Result");
    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - compare lines");
        EqualityCheck.setToggleGroup(CompareMode);
        DifferenceCheck.setToggleGroup(CompareMode);
    }

    @FXML
    private void compare() throws IOException {
        CompareArguments args = new CompareArguments();

        args.setString1(s1.getText());
        args.setString2(s2.getText());

        if (EqualityCheck.isSelected()) {
            resultLabel.setText("Equal lines");
            args.setReturnIdentical(true);
        } else if (DifferenceCheck.isSelected()) {
            resultLabel.setText("Different lines");
            args.setReturnIdentical(false);
        }

        LinkedHashSet<Integer> diffs = StringComparer.getLineNumbers(args);

        StringBuilder marks = new StringBuilder();
        int i = 1;
        for (int index : diffs) {
            while (i != index) {
                marks.append("\n");
                i++;
            }
            if (args.isReturnIdentical()) {
                marks.append("E");
            } else {
                marks.append("D");
            }
        }
        resultMarks.setText(marks.toString());

        System.out.println(diffs);

        resultOutput.setText(diffs.toString());
    }

    @FXML
    private void back() throws IOException {
        GuiController.setRoot("menu");
    }
}