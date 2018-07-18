package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Exception.ColonneRemplieException;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;

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
        int colonne = remplirColonne();

        int ligne =0;

        try {

            ligne = remplirLigne(colonne);

        } catch (ColonneRemplieException e) {

            e.printStackTrace();

        }
     board.getJetons()[ligne][colonne].setColor(color);
        return ligne+","+colonne+","+defCouleur();

    }

    public String defCouleur(){
        if (color.equals(Color.RED)){
            return "RED";}

        else {return "YELLOW";}
    }

    //Le systeme demande de choisir une case a jouer
    public int remplirColonne() {

        System.out.println("Veuillez entrer un numero de colonne entre 1 et 6: ");

        Scanner scan = new Scanner(System.in);

        return scan.nextInt() - 1;


    }

    //le systeme vérifie si la case est vide ou pas
    public int remplirLigne(int colonne) throws ColonneRemplieException {
        int ligne;
        for (ligne = -1; ligne < board.getJetons().length-1; ligne++) {
            if (!board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT)) {
                if (ligne == -1) {
                    throw new ColonneRemplieException();
                }
                return ligne;
            }
        }
        return ligne;
    }

    public boolean estGagne(Joueur joueur) {

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
