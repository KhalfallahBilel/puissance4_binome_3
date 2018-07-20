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
        int ligne = -1;
        do {
            try {
                colonne = remplirColonne();
                ligne = remplirLigne(colonne);
                board.getJetons()[ligne][colonne].setColor(color);
                if (gagne(ligne, colonne)) {
                    return "Fin";
                }
            } catch (ColonneRemplieException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } while (ligne == -1 || colonne == -1);
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
        int a = -1;
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Veuillez entrer un numero de colonne entre 1 et 7: ");
                a = scan.nextInt() - 1;

            } catch (InputMismatchException e) {
                System.out.println("veuillez entrer un nombre valide !");
                a = -1;
            }
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

    public boolean checkHor(int ligne, int colonne) {
        int cpt = 0;
        int i = 1, j = 1;

        do {
            if (colonne + i < board.getJetons()[ligne].length &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne][colonne + i].getColor())) {
                i++;
                cpt++;
            } else if (colonne - j >= 0 &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne][colonne - j].getColor())) {
                j++;
                cpt++;
            } else return false;
        } while (cpt < 3);
        return true;
    }

    public boolean checkVer(int ligne, int colonne) {
        int cpt = 0;
        int i = 1;
        do {
            if (ligne + i < board.getJetons().length &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne + i][colonne].getColor())) {
                cpt++;
                i++;
            } else return false;

        } while (cpt < 3);
        return true;
    }

    public boolean checkDiag1(int ligne, int colonne) {
        int cpt = 0;
        int i = 1, j = 1;

        do {
            if (colonne + i < board.getJetons()[ligne].length && ligne + i < board.getJetons().length &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne + i][colonne + i].getColor())) {
                i++;

                cpt++;
            } else if (colonne - j >= 0 && ligne - j >= 0 &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne - j][colonne - j].getColor())) {
                j++;
                cpt++;
           
            } else return false;
        } while (cpt < 3);
        return true;
    }

    public boolean checkDiag2(int ligne, int colonne) {
        int cpt = 0;
        int i = 1, j = 1;

        do {
            if (colonne - i >= 0 && ligne + i < board.getJetons().length &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne + i][colonne - i].getColor())) {
                i++;

                cpt++;
            } else if (colonne + j < board.getJetons()[ligne].length && ligne - j >= 0 &&
                    board.getJetons()[ligne][colonne].getColor().equals(board.getJetons()[ligne - j][colonne + j].getColor())) {
                j++;
                cpt++;

            } else return false;
        } while (cpt < 3);
        return true;
    }

    public boolean gagne(int ligne, int colonne) {
        if (checkHor(ligne, colonne) || checkVer(ligne, colonne) || checkDiag1(ligne, colonne)|| checkDiag2(ligne, colonne)) {
            System.out.println("vous avez gagné");
            return true;

        }
        return false;
    }

    @Override
    public void recevoir(String messageRecu) {
        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
