import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CS180 - Project 05
 * <p>
 * ClientHandler.java Class; Handles Clients
 * Received some help from past homeworks and labs
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */

public final class ClientHandler extends Thread implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        /*
        try {

            PrintWriter pw = new PrintWriter("reservations.txt");
            ReservationGui.getIntroFlightSemantics();

            while (ReservationGui.getPassenger() != null) {
                String line = in.nextLine();
                System.out.printf("%s says: %s\n", clientSocket, line);
                pw.printf("echo: %s\n", line);
                pw.flush();
            }

            pw.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */

    }

}




