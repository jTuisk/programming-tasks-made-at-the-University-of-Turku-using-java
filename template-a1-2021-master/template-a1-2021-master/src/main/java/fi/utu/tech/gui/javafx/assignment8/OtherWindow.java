package fi.utu.tech.gui.javafx.assignment8;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class OtherWindow  {

    final int outerCircleRadius = 100;
    final int totalCircles = 20;

    public OtherWindow() {
        initializeStage();
    }


    private void initializeStage(){
        Stage stage = new Stage();
        Group circleGroup = new Group();
        drawCircles(circleGroup, stage);
        Scene scene = new Scene(circleGroup, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void drawCircles(Group circleGroup, Stage stage){
        for(int i = 0; i < totalCircles; i++){
            int singleCircleRadius = (int) Math.ceil((double)outerCircleRadius/totalCircles);
            int currentRadius = outerCircleRadius - singleCircleRadius*i;
            circleGroup.getChildren().add(createCircle(currentRadius, Color.color(Math.random(), Math.random(), Math.random()),
                                    new Dimension2D(100, 100)));
        }
    }


    private Circle createCircle(double radius, Color color, Dimension2D pos) {
        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setFill(color);
        circle.setCenterX(pos.getWidth());
        circle.setCenterY(pos.getHeight());
        return circle;
    }
}
