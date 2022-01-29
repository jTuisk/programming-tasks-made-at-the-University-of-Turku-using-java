package fi.utu.tech.gui.javafx.assignment8;

import fi.utu.tech.gui.javafx.ImageBlackBox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class MainApp8 extends Application {

    private ImageBlackBox imageBlackBox = new ImageBlackBox();

    BorderPane bPane;

    ImageView noiseImage;

    Label seedTextLabel;

    TextField textField;

    Button settingsButton;
    Button previousButton;
    Button nextButton;

    HBox topRowBox;
    HBox bottomRowBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Noise generator");

        /*Labels*/
        seedTextLabel = new Label("Seed:");

        /*Images*/
        noiseImage = new ImageView(imageBlackBox.current());
        noiseImage.setFitWidth(200);
        noiseImage.setFitHeight(200);

        /*TextFields*/
        textField = new TextField(""+imageBlackBox.getCurrentSeed());
        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setDisable(true);

        /*Buttons*/
        settingsButton = new Button("⟳");
        previousButton = new Button("⤌");
        nextButton = new Button("⤍");
        buttonActionListener();

        topRowBox = new HBox();
        topRowBox.setSpacing(5);
        topRowBox.getChildren().addAll(seedTextLabel,textField, settingsButton);
        topRowBox.setHgrow(textField, Priority.ALWAYS);

        bottomRowBox = new HBox();
        bottomRowBox.setSpacing(5);
        bottomRowBox.getChildren().addAll(previousButton, nextButton);
        bottomRowBox.setAlignment(Pos.CENTER);

        bPane = new BorderPane();
        bPane.setTop(topRowBox);
        bPane.setCenter(noiseImage);
        bPane.setMargin(bottomRowBox, new Insets(0,0,5,0));
        bPane.setBottom(bottomRowBox);

        Scene scene = new Scene(bPane, 500, 300);
        stage.setScene(scene);
        stage.show();
    }


    private void buttonActionListener(){
        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new OtherWindow();
            }
        } );

        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                noiseImage.setImage(imageBlackBox.previous());
                textField.setText(""+imageBlackBox.getCurrentSeed());
            }
        } );

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                noiseImage.setImage(imageBlackBox.next());
                textField.setText(""+imageBlackBox.getCurrentSeed());
            }
        } );
    }
}