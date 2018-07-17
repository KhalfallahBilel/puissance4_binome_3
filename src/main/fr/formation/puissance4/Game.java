package fr.formation.puissance4;

import fr.formation.puissance4.Joueur.JoueurHumain;
import fr.formation.puissance4.Socket.Client;
import fr.formation.puissance4.Socket.Serveur;
import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Scanner;

public class Game extends Thread {
    private Jeton[][] jetons;

    public Game(List<Circle> circles) {
        this.jetons = new Jeton[6][7];
        int cpt = 0;
        for (int ligne = 0; ligne < this.jetons.length; ligne++) {
            for (int colonne = 0; colonne < this.jetons[ligne].length; colonne++) {
                this.jetons[ligne][colonne] = new Jeton(circles.get(cpt));
                cpt++;
            }
        }
    }


    @Override
    public void run() {
        System.out.println("Etes vous serveur ou client ?");
        System.out.println("1 - Client 2-Serveur");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1) {
            new Client(new JoueurHumain(Color.YELLOW, new Board(jetons))).start();
        } else
            new Serveur(new JoueurHumain(Color.RED, new Board(jetons))).start();
    }
}