package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.ColonneRemplieException;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;

import java.util.InputMismatchException;
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
    public String envoyer() {
        int colonne = -1;

        int ligne=-1;
do {
    try {
colonne=remplirColonne();
        ligne = remplirLigne(colonne);

    } catch (ColonneRemplieException e) {

        e.printStackTrace();

    }
}while(ligne==-1 || colonne==-1);
        board.getJetons()[ligne][colonne].setColor(color);
        return ligne + "," + colonne + "," + defCouleur();

    }

    public String defCouleur() {
        if (color.equals(Color.RED)) {
            return "RED";
        } else {
            return "YELLOW";
        }
    }

    //Le systeme demande de choisir une case a jouer
    public int remplirColonne() throws InputMismatchException {
        int a=-1;


        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Veuillez entrer un numero de colonne entre 1 et 7: ");
                a = scan.nextInt() - 1;

            } catch (InputMismatchException e) {
                System.out.println("veuillez entrer un nombre valide !");
                a=-1;
            }
        } while (!verifColonne(a));

        return a;
    }

    //le systeme vérifie si la case est vide ou pas
    public int remplirLigne(int colonne) throws ColonneRemplieException {

        int ligne;

        for (ligne = -1; ligne < board.getJetons().length - 1; ligne++) {

            if (!board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT)) {
                if (ligne == -1) {
                    System.out.println("Colonne reservé déja !!");
                    throw new ColonneRemplieException();

                }

                return ligne;

            }

        }

        return ligne;

    }

    /*public boolean estGagne(Joueur joueur) {

        return true;
    }*/
    public boolean verifColonne(int colonne) {

        if (colonne >-1 && colonne < 7) {
            return true;
        } else
            return false;

    }

    public boolean verifColonnePleine(int ligne, int colonne) {
        if (!board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT)) {
            return true;
        } else return false;
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
