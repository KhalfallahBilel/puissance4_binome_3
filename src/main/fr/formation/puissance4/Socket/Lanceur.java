package fr.formation.puissance4.Socket;

import fr.formation.puissance4.Joueur.Joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Lanceur {
    protected boolean isNotFinish;
    protected Socket clientSocket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected final Scanner sc = new Scanner(System.in);
    protected String msg;
    protected Joueur joueur;

    public Lanceur(Joueur joueur) {
        isNotFinish = true;
        this.joueur = joueur;
    }

    public abstract void start();

    protected void attendreMessage() throws IOException {
        System.out.println("Attente de l'adversaire");
        while (msg == null) {
            msg = in.readLine();
            if (msg!=null&&msg.equals("Fin")) {
                isNotFinish=false;
                System.out.println("Partie fini");
                return;
            }
        }
        joueur.recevoir(msg);
    }

    public void setNotFinish(boolean notFinish) {
        isNotFinish = notFinish;
    }
}
