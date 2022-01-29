package fi.utu.tech.gui.javafx.assignment3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MainApp3 extends Application {

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
            if(event.getButton() == MouseButton.PRIMARY){
                pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.BLACK);
                gc.moveTo(event.getX(), event.getY());
            }
            if(event.getButton() == MouseButton.SECONDARY){
                pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.WHITE);
            }
            System.out.println("MousePressed - x: "+event.getX()+" y:"+event.getY());
        });

        canvas.setOnMouseReleased(event -> {
            if(event.getButton() == MouseButton.PRIMARY){
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
            System.out.println("MouseReleased - x: "+event.getX()+" y:"+event.getY());
        });

        canvas.setOnMouseDragged(event -> {
            if(event.getButton() == MouseButton.PRIMARY){
                pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.BLACK);
            }
            if(event.getButton() == MouseButton.SECONDARY){
                pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.WHITE);
            }
            System.out.println("MouseDrag - x: "+event.getX()+" y:"+event.getY());
        });
    }

}
