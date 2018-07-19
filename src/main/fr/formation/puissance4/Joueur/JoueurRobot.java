package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.ColonneRemplieException;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JoueurRobot extends Joueur {
    private Jeton jeton;

    public JoueurRobot(Color yellow, Board board) {
        super(yellow, board);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public JoueurRobot(Color color, Board board, Jeton jeton) {
        super(color, board);
    }

    @Override
    public String envoyer() {
        int colonne;
        int ligne = -1;
        do {
            colonne=remplirColonne();
            try {
                ligne=remplirLigne(colonne);
            } catch (ColonneRemplieException e) {
                e.printStackTrace();
            }
        }while (ligne==-1|| colonne==0);
        board.getJetons()[ligne][colonne].setColor(color);
        return ligne + "," + colonne + "," +defCouleur();
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }
    public String defCouleur() {
        if (color.equals(Color.RED)) {
            return "RED";
        } else {
            return "YELLOW";
        }
    }
    //Le systeme va choisir une colonne aléatoire
    public int remplirColonne() {
            Random random = new Random();
        int a = random.nextInt(7-1)+1;
        do {

            System.out.println("le Robot joue dans la colonne : "+a);
        } while (!verifColonne(a));

        return a;
    }
    //le systeme vérifie si la case est vide ou pas
    public int remplirLigne(int colonne) throws ColonneRemplieException {
        int ligne;
        for (ligne = -1; ligne < board.getJetons().length - 1; ligne++) {
            if (!board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT)) {
                try {
                    if (ligne == -1) {
                        throw new ColonneRemplieException("Colonne reservé déja !!");
                    }
                } catch (ColonneRemplieException e) {
                    System.out.println("Colonne pleine !!");
                }
                return ligne;
            }
        }
        return ligne;
    }
    public boolean verifColonne(int colonne) {
        if (colonne > -1 && colonne < 7) {
            return true;
        } else
            return false;
    }
}
