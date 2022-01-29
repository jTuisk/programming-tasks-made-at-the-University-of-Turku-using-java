package fi.utu.tech.gui.javafx.assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp2  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Noise generator");
        Scene scene = new Scene(new Label("Moikka täältä scenestä!"));
        stage.setScene(scene);
        stage.show();
    }
}