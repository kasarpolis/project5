import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import com.sun.net.httpserver.HttpServer;
/**
 * CS180 - Project 05
 * <p>
 * ReservationServer.java Class; Handles Reservation Server
 * Received some help from past homeworks and labs
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */


public class ReservationServer {

        private ServerSocket ss;
        private ArrayList<String> fileContents;

        public ReservationServer() throws IOException {
        this.ss = new ServerSocket(0);
        }
        public void serveClients() {
            Socket clientSocket;
            ClientHandler handler;
            Thread handlerThread;
            int connectionCount = 0;

            System.out.printf("<Now serving clients on port %d...>%n", this.ss.getLocalPort());

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

                System.out.printf("<Client %d connected...>%n", connectionCount);

                connectionCount++;
            } //end while
            /*
            File f = new File("reservations.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            fileContents = new ArrayList<>();

            while (true)
            {
                String line = bfr.readLine();
                if (line == null)
                    break;
                fileContents.add(line);
            }

            bfr.close();
            */

        } //serveClients


        @Override
        public int hashCode() {
            int result = 23;

            result = result * 31 * Objects.hashCode(this.ss);

            return result;
        } //hashCode


        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (object instanceof ReservationServer) {
                return Objects.equals(this.ss, ((ReservationServer) object).ss);
            } else {
                return false;
            } //end if
        } //equals

        @Override
        public String toString() {
            String format = "ReservationServer[%s]";

            return String.format(format, this.ss);
        }
}


