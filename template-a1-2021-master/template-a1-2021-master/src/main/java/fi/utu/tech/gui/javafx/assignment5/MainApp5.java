package fi.utu.tech.gui.javafx.assignment5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class MainApp5  extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Noise generator");

        /*Labels*/
        Label helloTextLabel = new Label("Scene says hello!");
        Label seedTextLabel = new Label("Seed:");

        /*TextFields*/
        TextField textField = new TextField("No seed available");
        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setDisable(true);

        /*Buttons*/
        Button settingsButton = new Button("⟳");
        Button previousButton = new Button("⤌");
        Button nextButton = new Button("⤍");


        HBox topRowBox = new HBox();
        topRowBox.setSpacing(5);
        topRowBox.getChildren().addAll(seedTextLabel,textField, settingsButton);
        topRowBox.setHgrow(textField, Priority.ALWAYS);


        HBox bottomRowBox = new HBox();
        bottomRowBox.setSpacing(5);
        bottomRowBox.getChildren().addAll(previousButton, nextButton);
        bottomRowBox.setAlignment(Pos.CENTER);

        BorderPane bPane = new BorderPane();
        bPane.setTop(topRowBox);
        bPane.setCenter(helloTextLabel);
        bPane.setMargin(bottomRowBox, new Insets(0,0,5,0));
        bPane.setBottom(bottomRowBox);

        Scene scene = new Scene(bPane, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}