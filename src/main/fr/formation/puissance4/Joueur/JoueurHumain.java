package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur{
        public JoueurHumain(Color color, Board board) {
            super(color, board);
        }

    @Override
    public String remplirCase() {
        return null;
    }

    @Override
    public boolean verifDispo() {
        return false;
    }

    @Override
    public String envoyer() {
        if (Color.RED.equals(color))
            return "4,4,RED";
        else
            return "4,4,YELLOW";
    }
    @Override
    public String remplir(){
        System.out.println("Choisir la case a jouer !");
        Scanner sc = new Scanner(System.in);
        sc.nextInt();



            return "";
    }


    @Override
    public void recevoir(String messageRecu) {
        String [] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
