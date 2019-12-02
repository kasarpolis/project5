import java.io.*;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Objects;


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
    private ObjectInputStream inputObjectStream = null;
    private ObjectOutputStream outputObjectStream = null;

    public ClientHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        Delta d = new Delta();
        Alaska a = new Alaska();
        Southwest s = new Southwest();
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("reservations.txt"));

            while(true) {
                String line = br.readLine();
                if (line == null)
                    break;
                fileContents.add(line);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        int alaskaIndex = 0;
        int deltaIndex = 0;
        int southwestIndex = 0;
        for (int i = 0; i < fileContents.size(); i++)
        {
            if (fileContents.get(i).contains("ALASKA"))
                alaskaIndex = i;
            if (fileContents.get(i).contains("DELTA"))
                deltaIndex = i;
            if (fileContents.get(i).contains("SOUTHWEST"))
                southwestIndex = i;
        }

        String alaskaAmount = fileContents.get(alaskaIndex + 2).substring(0, 1);
        a.setSpotsTaken(Integer.parseInt(alaskaAmount));
        String deltaAmount = fileContents.get(deltaIndex + 2).substring(0, 1);
        d.setSpotsTaken(Integer.parseInt(deltaAmount));
        String southwestAmount = fileContents.get(southwestIndex + 2).substring(0, 1);
        s.setSpotsTaken(Integer.parseInt(southwestAmount));

        for (int i = alaskaIndex; i < deltaIndex; i++)
        {
            if (!fileContents.get(i).equals("") && fileContents.get(i).contains("."))
                a.getPassengers().add(fileContents.get(i));
        }

        for (int i = deltaIndex; i < southwestIndex; i++)
        {
            if (!fileContents.get(i).equals("") && fileContents.get(i).contains("."))
                d.getPassengers().add(fileContents.get(i));
        }

        for (int i = southwestIndex; i < fileContents.size(); i++)
        {
            if (!fileContents.get(i).equals("") && fileContents.get(i).contains("."))
                s.getPassengers().add(fileContents.get(i));
        }
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

        try{

            outputObjectStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputObjectStream.writeObject(a);
            outputObjectStream.flush();
            outputObjectStream.writeObject(d);
            outputObjectStream.flush();
            outputObjectStream.writeObject(s);
            outputObjectStream.flush();

            outputObjectStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}




