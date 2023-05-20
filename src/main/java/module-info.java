module put.ai.se.jsontools {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens put.ai.se.jsontools to javafx.fxml;

    exports put.ai.se.jsontools;
    exports put.ai.se.jsontools.core;
    opens put.ai.se.jsontools.core to javafx.fxml;
}
