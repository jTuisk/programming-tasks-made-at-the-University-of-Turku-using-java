package fi.utu.tech.gui.javafx.assignment1;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp1  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Noise generator");
        stage.show();
    }
}
