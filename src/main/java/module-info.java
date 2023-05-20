module put.ai.se.jsontools {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens put.ai.se.jsontools.gui to javafx.fxml;

    exports put.ai.se.jsontools;
    exports put.ai.se.jsontools.core;
    // exports put.ai.se.jsontools.api;
    exports put.ai.se.jsontools.gui;
}
