package fi.utu.tech.gui.javafx.assignment3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApp3  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Noise generator");

        BorderPane bPane = new BorderPane();
        Label helloTextLabel = new Label("Scene says hello!");
        Label seedTextLabel = new Label("Seed:");
        TextField textField = new TextField("No seed available");

        Button settingsButton = new Button("⟳");
        Button previousButton = new Button("⤌");
        Button nextButton = new Button("⤍");

        HBox topRow = new HBox();
        topRow.getChildren().addAll(seedTextLabel,textField, settingsButton);

        HBox bottomRow = new HBox();
        bottomRow.getChildren().addAll(previousButton, nextButton);

        bPane.setTop(topRow);
        bPane.setCenter(helloTextLabel);
        bPane.setBottom(bottomRow);

        Scene scene = new Scene(bPane, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}