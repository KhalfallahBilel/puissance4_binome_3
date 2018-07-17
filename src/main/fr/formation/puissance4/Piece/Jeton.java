package fr.formation.puissance4.Piece;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Jeton {
    Circle  circle;

    public Jeton(Circle circle) {
        this.circle = circle;
    }
     public void setColor(Color color){
        this.circle.setFill(color);
     }
}
