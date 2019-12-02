/**
 * CS180 - Project 05
 * <p>
 * ResponseListener.java Class; Listens to Responses from clients
 * Received some help from past labs and homeworks
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 12/2/2019
 */


public class ResponseListener extends ReservationClient {
    public static void main(String[] args) throws InterruptedException {
        ReservationServer server;

        try {
            server = new ReservationServer();
        } catch (Exception e) {
            e.printStackTrace();

            return;
        } //end try catch

        server.serveClients();
    }

}



