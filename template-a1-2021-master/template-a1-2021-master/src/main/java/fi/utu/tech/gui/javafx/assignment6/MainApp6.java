package fi.utu.tech.gui.javafx.assignment6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MainApp6  extends Application {

    String imageFileName = "juustokakku.png";
    String imagePath = System.getProperty("user.dir")+ "/src/main/java/fi/utu/tech/gui/javafx/assignment6/"+imageFileName;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Noise generator");

        /*Labels*/
        Label seedTextLabel = new Label("Seed:");


        /*Images*/
        Image cheesCakeImage = new Image(new FileInputStream(imagePath));
        ImageView cheeseCakeView = new ImageView(cheesCakeImage);
        cheeseCakeView.setFitWidth(200);
        cheeseCakeView.setFitHeight(200);


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
        bPane.setCenter(cheeseCakeView);
        bPane.setMargin(bottomRowBox, new Insets(0,0,5,0));
        bPane.setBottom(bottomRowBox);

        Scene scene = new Scene(bPane, 500, 300);
        stage.setScene(scene);
        stage.show();
    }
}