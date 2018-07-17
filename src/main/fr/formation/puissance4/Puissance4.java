package fr.formation.puissance4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Puissance4 extends Application {
    List<Circle> circleList = new ArrayList<>();
    private int r;
    private int c;


    @Override
    public void start(Stage primaryStage) {

        final BorderPane root = new BorderPane();
        final GridPane gridpane = new GridPane();
        primaryStage.setTitle("Puissance 4");
        primaryStage.setResizable(true);

        final Button addCellButton = new Button("Add Grids");

        Scene scene = new Scene(root, 900, 900, true);
        scene.setFill(Color.BLACK);
        scene.getStylesheets().add("net/glyphsoft/styles.css");

        gridpane.setTranslateY(20);
        gridpane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 7; i++) {
            if (i < 6) {
                gridpane.getColumnConstraints().add(new ColumnConstraints(100, 100, Double.MAX_VALUE));
                gridpane.getRowConstraints().add(new RowConstraints(100, 100, Double.MAX_VALUE));
            } else {
                gridpane.getColumnConstraints().add(new ColumnConstraints(100, 100, Double.MAX_VALUE));
            }

        }

        createGrids(gridpane);

        root.setCenter(gridpane);

        DropShadow effect = new DropShadow();
        effect.setColor(Color.BLUE);


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        try {
            Game game = new Game(circleList);
            game.start();
        }catch (Exception e){
            System.exit(1);
        }
    }


    //Create Grids
    private void createGrids(final GridPane gridpane) {
        gridpane.getChildren().clear();
        for (r = 0; r < gridpane.getRowConstraints().size(); r++) {
            for (c = 0; c < gridpane.getColumnConstraints().size(); c++) {

                Rectangle rect = new Rectangle(100, 100);
                Circle circ = new Circle(47);
                circ.centerXProperty().set(50);
                circ.centerYProperty().set(50);
                Shape cell = Path.subtract(rect, circ);
                cell.setFill(Color.BLUE);
                cell.setStroke(Color.BLUE);
                cell.setOpacity(.8);
                DropShadow effect = new DropShadow();
                effect.setSpread(.2);
                effect.setRadius(25);
                effect.setColor(Color.BLUE);
                cell.setEffect(effect);


                final Circle diskPreview = new Circle(40);
                diskPreview.setOpacity(1);
                diskPreview.setFill(Color.TRANSPARENT);



                StackPane stack = new StackPane();

                stack.getChildren().addAll(cell, diskPreview);
                circleList.add(((Circle) stack.getChildren().get(1)));
                gridpane.add(stack, c, r);

                if (r == gridpane.getColumnConstraints().size() - 1) {
                    stack.setEffect(new Reflection());
                }
            }

        }
    }
}
