package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public abstract class Joueur {
    protected Color color;
    protected Board board;

    public Joueur(Color color, Board board) {
        this.color = color;
        this.board = board;
    }

    public abstract String remplirCase();
    public abstract boolean verifDispo();


    public abstract String envoyer();

    public abstract String remplir();

    public abstract void recevoir(String messageRecu);
}