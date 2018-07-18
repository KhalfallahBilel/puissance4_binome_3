package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Game;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    private Jeton jeton;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public JoueurHumain(Color color, Board board) {

        super(color, board);
    }

    @Override
    public String remplirCase() {
        return null;
    }

    /*@Override
    public boolean verifDispo() {
        if(board.getJetons().equals(color.TRANSPARENT))
        { return false;}
        return true;
    }*/

    @Override
    public String envoyer() {
       /* if (Color.RED.equals(color))
            return "4,4,RED";
        else
            return "4,4,YELLOW";*/
       return "";
    }

    @Override
    public String remplir(Joueur joueur) {
        Color[][] grid = new Color[6][7];
        System.out.println("Choisir la colonne a jouer entre 1 et 6!");
        Scanner sc = new Scanner(System.in);
        int b = 0;
        while (b < 7 && b > 0) {
            sc.next();
            int a = Integer.parseInt(sc.next());

            for (int i = 0; i < 6; i++) {
                if(board.getJetons()[i][a-1]==null){
                    board.getJetons()[i][a-1].setColor(color);
                }

            }
            return "YELLOW";
        }
        return "YELLOW";
    }

    public boolean estGagne(Joueur joueur){
        return true;
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
