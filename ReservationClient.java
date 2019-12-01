import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * CS180 - Project 05
 * <p>
 * ReservationClient.java Class; Handles reservationclient
 * Received some help from past homeworks and labs
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */
public class ReservationClient {

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    }

    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String hostname;
        String portString;
        int port;
        Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;
        String request;
        String response;

        try {
            hostname = (String) JOptionPane.showInputDialog(null, "Enter the sever's hostname: ",
                    "Hostname?", JOptionPane.QUESTION_MESSAGE);


            if (hostname == null) {
                JOptionPane.showMessageDialog(null, "Invalid Hostname! Goodbye!",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                portString = (String) JOptionPane.showInputDialog(null, "Enter the sever's port: ",
                        "Port?", JOptionPane.QUESTION_MESSAGE);

                if (portString == null) {
                    JOptionPane.showMessageDialog(null, "Invalid Hostname! Goodbye!",
                            "Error", JOptionPane.WARNING_MESSAGE);

                } else if (!isParsable(portString)) {
                    JOptionPane.showMessageDialog(null, "The specified port must be a " +
                                    "non-negative integer! Goodbye!",
                            "Error", JOptionPane.WARNING_MESSAGE);

                } else {
                    ReservationGui.getIntroFlightSemantics(hostname, portString);


                    socket = new Socket(hostname, Integer.parseInt(portString));
                    socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println();
                    request = userInputReader.readLine();

                    socketWriter.write(request);
                    socketWriter.newLine();
                    socketWriter.flush();
                    response = socketReader.readLine();
                    System.out.println();
                    System.out.printf("Response from server: %s%n", response);
                    System.out.println();
                    System.out.print("Enter your request: ");
                    request = userInputReader.readLine();




                    JOptionPane.showMessageDialog(null, "Goodbye!", "Goodbye!",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  }

