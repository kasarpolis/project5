import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CS180 - Project 05
 * <p>
 * ReservationServer.java Class; Handles Reservation Server
 * Received some help from past homeworks and labs
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 12/2/2019
 */

public class ReservationServer {

        private ServerSocket ss;

        public ReservationServer() throws IOException {
        this.ss = new ServerSocket(18000);
        }
        public void serveClients() throws InterruptedException {
            Socket clientSocket;
            ClientHandler handler;
            Thread handlerThread;
            int connectionCount = 0;

            while (true) {
                try {
                    clientSocket = this.ss.accept();
                } catch (IOException e) {
                    e.printStackTrace();

                    break;
                } //end try catch

                handler = new ClientHandler(clientSocket);

                handlerThread = new Thread((Runnable) handler);

                handlerThread.start();


            } //end while



        } //serveClients


}


