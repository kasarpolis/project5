import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * CS180 - Project 05
 * <p>
 * ReservationClient.java Class; Handles reservationClient
 * Received some help from past homeworks and labs
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 12/2/2019
 */
public class ReservationClient {

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    }

    public static void main(String[] args) throws InterruptedException {

        String hostname;
        String portString;

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

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  }

