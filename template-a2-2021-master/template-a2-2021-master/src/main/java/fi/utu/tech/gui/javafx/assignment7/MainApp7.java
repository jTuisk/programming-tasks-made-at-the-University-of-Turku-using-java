package fi.utu.tech.gui.javafx.assignment7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp7 extends Application {

    /**
     * Nothing to see, nothing to change here
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Information");
        BorderPane root = new BorderPane();

        root.setCenter(new Label("This assignment does not require changes here. Only FXML."));

        stage.setScene(new Scene(root));
        stage.show();
    }

}
