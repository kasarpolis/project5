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
 * @version 12/2/2019
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

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                fileContents.add(line);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        if (fileContents.size() != 0) {
            int alaskaIndex = 0;
            int deltaIndex = 0;
            int southwestIndex = 0;
            for (int i = 0; i < fileContents.size(); i++) {
                if (fileContents.get(i).contains("ALASKA"))
                    alaskaIndex = i;
                if (fileContents.get(i).contains("DELTA"))
                    deltaIndex = i;
                if (fileContents.get(i).contains("SOUTHWEST"))
                    southwestIndex = i;
            }

            String alaskaAmount = fileContents.get(alaskaIndex + 2).substring(0, 1);
            a.setSpotsTaken(Integer.parseInt(alaskaAmount));
            String alaskaCapacity = fileContents.get(alaskaIndex + 2).substring(2);
            String deltaAmount = fileContents.get(deltaIndex + 2).substring(0, 1);
            d.setSpotsTaken(Integer.parseInt(deltaAmount));
            String deltaCapacity = fileContents.get(deltaIndex + 2).substring(2);
            String southwestAmount = fileContents.get(southwestIndex + 2).substring(0, 1);
            s.setSpotsTaken(Integer.parseInt(southwestAmount));
            String southwestCapacity = fileContents.get(southwestIndex + 2).substring(2);

            for (int i = alaskaIndex; i < deltaIndex; i++) {
                if (!fileContents.get(i).equals("") && fileContents.get(i).contains("."))
                    a.getPassengers().add(fileContents.get(i));
            }

            for (int i = deltaIndex; i < southwestIndex; i++) {
                if (!fileContents.get(i).equals("") && fileContents.get(i).contains("."))
                    d.getPassengers().add(fileContents.get(i));
            }

            for (int i = southwestIndex; i < fileContents.size(); i++) {
                if (!fileContents.get(i).equals("") && fileContents.get(i).contains("."))
                    s.getPassengers().add(fileContents.get(i));
            }
            int a1 = Integer.parseInt(alaskaAmount);
            int d1 = Integer.parseInt(deltaAmount);
            int s1 = Integer.parseInt(southwestAmount);
            if (a.getPassengers().size() != a1)
            {
                a1 = a.getPassengers().size();
            }
            else if (d.getPassengers().size() != d1)
            {
                d1 = d.getPassengers().size();
            }
            else if (s.getPassengers().size() != s1)
            {
                s1 = s.getPassengers().size();
            }

            a.setSpotsTaken(a1);
            d.setSpotsTaken(d1);
            s.setSpotsTaken(s1);

            int a2 = Integer.parseInt(alaskaCapacity);
            int d2 = Integer.parseInt(deltaCapacity);
            int s2 = Integer.parseInt(southwestCapacity);

            a.setMaxCapacity(a2);
            d.setMaxCapacity(d2);
            s.setMaxCapacity(s2);
        }


        try {

            outputObjectStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputObjectStream.writeObject(a);
            outputObjectStream.flush();
            outputObjectStream.writeObject(d);
            outputObjectStream.flush();
            outputObjectStream.writeObject(s);
            outputObjectStream.flush();


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            inputObjectStream = new ObjectInputStream(clientSocket.getInputStream());
            Object o = inputObjectStream.readObject();
            if (o instanceof Alaska)
                a = (Alaska) o;
            if (o instanceof Delta)
                d = (Delta) o;
            if (o instanceof Southwest)
                s = (Southwest) o;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            outputObjectStream.close();
            inputObjectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            File tempFile = new File("reservations.txt");
                FileOutputStream fos = new FileOutputStream(tempFile, false);
                PrintWriter pw = new PrintWriter(fos);

                pw.println("ALASKA");
                pw.println("-------------------------------");
                pw.println(a.getSpotsTaken() + "/100");
                for (int i = 0; i < a.getPassengers().size(); i++) {
                    pw.println(a.getPassengers().get(i));
                }
                pw.println();
                pw.println("DELTA");
                pw.println("-------------------------------");
                pw.println(d.getSpotsTaken() + "/100");
                for (int i = 0; i < d.getPassengers().size(); i++) {
                    pw.println(d.getPassengers().get(i));
                }
                pw.println();
                pw.println("SOUTHWEST");
                pw.println("-------------------------------");
                pw.println(s.getSpotsTaken() + "/100");
                for (int i = 0; i < s.getPassengers().size(); i++) {
                    pw.println(s.getPassengers().get(i));
                }
                pw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}




