package put.ai.se.jsontools.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiController extends Application implements Runnable {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void run() {
        javafx.application.Application.launch(GuiController.class);
    }

    public void closeWindow() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void start(Stage stage) throws IOException {
        GuiController.stage = stage;
        scene = new Scene(loadFXML("menu"), 640, 480);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> closeWindow());
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setTitle(String title) {
        if (stage == null)
            return;

        stage.setTitle(title);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
