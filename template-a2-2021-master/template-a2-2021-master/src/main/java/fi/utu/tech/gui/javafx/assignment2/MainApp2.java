package fi.utu.tech.gui.javafx.assignment2;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Canvas drawCanvas = new Canvas(1000,800);
        BorderPane root = new BorderPane();
        root.setCenter(drawCanvas);

        drawOnCanvasMouseActions(drawCanvas);

        stage.setScene(new Scene(root));
        stage.show();
    }

    private void drawOnCanvasMouseActions(Canvas canvas){
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(event -> {
            pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.BLACK);
            gc.moveTo(event.getX(), event.getY());
            System.out.println("MousePressed - x: "+event.getX()+" y:"+event.getY());
        });

        canvas.setOnMouseReleased(event -> {
            gc.lineTo(event.getX(), event.getY());
            gc.stroke();
            System.out.println("MouseReleased - x: "+event.getX()+" y:"+event.getY());
        });

        canvas.setOnMouseDragged(event -> {
                pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.BLACK);
                System.out.println("MouseDrag - x: "+event.getX()+" y:"+event.getY());
        });
    }
}
