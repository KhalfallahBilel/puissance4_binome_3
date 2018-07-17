package fr.formation.puissance4.Socket;

import fr.formation.puissance4.Joueur.Joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client extends Lanceur {

    public Client(Joueur joueur) {
        super(joueur);
    }

    @Override
    public void start() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez entrez l'adresse ip du serveur");
            String ip = scanner.nextLine();
            clientSocket = new Socket(ip, 5000);
            try {
                out = new PrintWriter(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (isNotFinish) {
                System.out.println("A vous de jouer :");
                msg = joueur.envoyer();
                out.println(msg);
                out.flush();
                if (msg.equals("Fin"))
                    break;
                msg = null;
            attendreMessage();
            }
            System.out.println("Serveur déconecté");
            out.close();

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}