package fi.utu.tech.gui.javafx.assignment1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp1 extends Application {




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

        canvas.setOnMouseClicked(event -> {
            pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.BLACK);
            System.out.println("Drawing to x: "+event.getX()+" y:"+event.getY());
        });

        canvas.setOnMouseDragged(event -> {
            pixelWriter.setColor((int) event.getX(), (int) event.getY(), Color.BLACK);
            System.out.println("Drawing to x: "+event.getX()+" y:"+event.getY());
        });
    }
    
}
