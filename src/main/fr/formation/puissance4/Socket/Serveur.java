package fr.formation.puissance4.Socket;

import fr.formation.puissance4.Joueur.Joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Serveur extends Lanceur {

    ServerSocket serveurSocket;

    public Serveur(Joueur joueur) {
        super(joueur);
    }

    public void start() {

        try {
            serveurSocket = new ServerSocket(5000);
            clientSocket = serveurSocket.accept();
            try {
                out = new PrintWriter(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (isNotFinish) {
                Thread.sleep(1000);
               super.attendreMessage();

                System.out.println("A vous de jouer :");
                msg = joueur.envoyer();
                out.println(msg);
                out.flush();
                if (msg.equals("Fin"))
                    break;
                msg = null;

            }
            System.out.println("Serveur déconecté");
            out.close();

            clientSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}