package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.LinkedHashSet;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.compare.CompareArguments;
import put.ai.se.jsontools.core.compare.CompareBuilder;
import put.ai.se.jsontools.core.compare.StringComparer;

public class CompareController {
    @FXML
    TextArea s1;
    @FXML
    TextArea s2;

    @FXML
    public void initialize() {
        GuiController.setTitle("JSON tools - compare lines");
    }

    @FXML
    private void compare() throws IOException {
        CompareArguments args = new CompareArguments();

        args.setString1(s1.getText());
        args.setString2(s2.getText());

        LinkedHashSet<Integer> diffs = StringComparer.getLineNumbers(args);

        System.out.println(diffs);
    }

    @FXML
    private void back() throws IOException {
        GuiController.setRoot("menu");
    }
}
