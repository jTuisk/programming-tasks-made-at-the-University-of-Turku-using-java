package fi.utu.tech.gui.javafx.assignment5;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp5 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Setting up the application layout and components
        stage.setTitle("Roll");
        VBox root = new VBox();

        List<TextField> fields = new ArrayList<TextField>();
        for (int i=0; i<10; i++) {
            fields.add(new TextField());
        }

        // Do not even think about modifying the event handlers for each and every component
        root.getChildren().addAll(fields);
        root.getChildren().add(new Button("Hello!"));
        Scene scene = new Scene(root, 500, 300);

        scene.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            System.out.println("Event: "+event.getEventType()+ " key:"+event.getCode());
            if (event.getCharacter().toLowerCase().equals("x")) {
                doABarrelRoll(root);
                event.consume();
            }
        });

        stage.setScene(scene);
        stage.show();

    }


    public void doABarrelRoll(Node n) {
        RotateTransition rotate = new RotateTransition(
        Duration.millis(2500), n);
        rotate.setToAngle(0 + 360);
        rotate.setFromAngle(0);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();
    }


}
