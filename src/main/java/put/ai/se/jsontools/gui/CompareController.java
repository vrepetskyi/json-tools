package put.ai.se.jsontools.gui;

import java.io.IOException;
import java.util.LinkedHashSet;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import put.ai.se.jsontools.core.DiffFinder;

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
        LinkedHashSet<Integer> diffs = DiffFinder.getLineNumbers(s1.getText(), s2.getText());

        System.out.println(diffs);
    }

    @FXML
    private void back() throws IOException {
        GuiController.setRoot("menu");
    }
}
