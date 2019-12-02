import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

/**
 * CS180 - Project 05
 * <p>
 * ReservationGui.java Class; Creates complex gui
 * Received some ideas from HW13
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */

public class ReservationGui {

    private static int typeOfFlight = -1;
    private static Airline air;
    private static Delta d;
    private static Alaska a;
    private static Southwest s;
    private static Passenger p;


    public static void getIntroFlightSemantics(String hostname, String port) throws IOException {
        try
        {
            Socket socket = new Socket(hostname, Integer.parseInt(port));
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            a = (Alaska) ois.readObject();
            d = (Delta) ois.readObject();
            s = (Southwest) ois .readObject();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();

        JFrame frame = new JFrame("Purdue University Flight Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        JLabel label = new JLabel("<html>Welcome to Purdue University Flight " +
                "<br>Reservation Management System!</html>");
        JButton button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton button1 = new JButton("Book a Flight");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                flightBox();
            }
        });
        panel.add(button);
        panel.add(button1);
        panel1.add(label);
        frame.getContentPane().add(BorderLayout.NORTH, panel1);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
        JLabel label2 = new JLabel();
        JPanel panel2 = new JPanel();
        label2.setIcon(new ImageIcon("image1.png"));// your image here
        panel2.add(label2);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void flightBox() {
        JPanel txtPanel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel info = new JLabel("Please Select a Flight Below");
        info.setFont(info.getFont().deriveFont(Font.BOLD, 16f));
        JFrame jf = new JFrame("Purdue University Flight Reservation System");

        GridLayout grid = new GridLayout(4, 1);
        jf.setLayout(grid);
        String[] flightList = {"Delta", "Southwest", "Alaska"};
        JLabel lblText = new JLabel();
        JComboBox<String> flightBox = new JComboBox<>(flightList);
        flightBox.setSelectedIndex(-1);
        Dimension size = lblText.getPreferredSize();
        flightBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == flightBox) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String msg = (String) cb.getSelectedItem();
                    assert msg != null;
                    switch (msg) {
                        case "Delta":
                            lblText.setText("");
                            air = d;
                            Gate deltaGate = new Gate('A', 16);
                            air.setGate(deltaGate);
                            lblText.setText(air.getInfo());
                            lblText.setLocation(250, 250);
                            lblText.setBounds(0, 50, size.width, size.height);
                            break;
                        case "Alaska":
                            lblText.setText("");
                            air = a;
                            Gate alaskaGate = new Gate('B', 12);
                            air.setGate(alaskaGate);
                            lblText.setText(air.getInfo());
                            lblText.setLocation(250, 250);
                            lblText.setBounds(0, 50, size.width, size.height);
                            break;
                        case "Southwest":
                            lblText.setText("");
                            air = s;
                            Gate southwestGate = new Gate('C', 7);
                            air.setGate(southwestGate);
                            lblText.setText(air.getInfo());
                            lblText.setLocation(250, 250);
                            lblText.setBounds(0, 50, size.width, size.height);
                            break;
                        default:
                            lblText.setText("");
                            lblText.setText("We have an error");
                    }
                }
            }
        });
        txtPanel.add(flightBox);
        panel2.add(lblText);
        panel3.add(info);

        JButton exit = new JButton("Exit");
        JButton book = new JButton("Choose this flight");

        JPanel buttons = new JPanel();
        buttons.add(exit);
        buttons.add(book);


        jf.add(panel3);
        jf.add(txtPanel);
        jf.add(panel2);
        jf.getContentPane().add(BorderLayout.SOUTH, buttons);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(500, 500);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeOfFlight = flightBox.getSelectedIndex();
                if (typeOfFlight == -1)
                    JOptionPane.showMessageDialog(null, "Please select a flight!",
                            "Error", JOptionPane.WARNING_MESSAGE);
                else {
                    jf.setVisible(false);
                    confirmationGUI();
                }

            }
        });
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    if (air == null)
                        JOptionPane.showMessageDialog(null, "Please select a flight!",
                                "Error!", JOptionPane.WARNING_MESSAGE);
                    else
                        getFlightInfoGUI();
                }
            }


            public void keyReleased(KeyEvent e) {
            }
        };
        flightBox.addKeyListener(listener);
    }

    public static void getFlightInfoGUI() {
        JFrame flights = new JFrame();
        flights.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flights.setSize(250, 250);
        flights.setResizable(false);
        flights.setVisible(true);
        JLabel delta = new JLabel(air.getName() + " Airlines.");
        delta.setFont(delta.getFont().deriveFont(Font.BOLD, 14f));
        JLabel passenger = new JLabel(air.getSpotsTaken() + ":100");

        passenger.setFont(passenger.getFont().deriveFont(Font.PLAIN, 14f));
        JPanel passengerList = new JPanel();
        passengerList.add(delta);
        passengerList.add(passenger);
        flights.getContentPane().add(BorderLayout.NORTH, passengerList);

        JButton exit = new JButton("Close");
        JPanel exitPanel = new JPanel();
        exitPanel.add(exit);
        flights.getContentPane().add(BorderLayout.SOUTH, exitPanel);

        String[] passengersOnFlight = new String[air.getPassengers().size()];
        for (int i = 0; i < air.getPassengers().size(); i++) {

            if (i == air.getPassengers().size() - 1) {
                passengersOnFlight[i] = air.getPassengers().get(i);
            } else {
                passengersOnFlight[i] = air.getPassengers().get(i) + "\n";
            }
        }
        JList<String> list = new JList<>(passengersOnFlight);
        JScrollPane scrollableList = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        flights.getContentPane().add(BorderLayout.CENTER, scrollableList);

        flights.setVisible(true);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flights.setVisible(false);
            }
        });
        passengerList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    flights.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        passengerList.setFocusable(true);
        passengerList.requestFocusInWindow();
    }


    public static void confirmationGUI() {
        JFrame confirmation = new JFrame("Purdue University Flight Reservation System");
        confirmation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmation.setSize(500, 500);
        JLabel confirm = new JLabel("<html>Are you sure that you want to book a flight on" +
                "<br> " + air.getName() + " Airlines?</html>");
        confirm.setFont(confirm.getFont().deriveFont(Font.BOLD, 16f));
        JPanel label = new JPanel();
        label.add(confirm);
        confirmation.getContentPane().add(BorderLayout.CENTER, label);

        JButton exit = new JButton("Exit");
        JButton no = new JButton("No, I want a different flight.");
        JButton yes = new JButton("Yes, I want this flight.");
        JPanel buttons = new JPanel();
        buttons.add(exit);
        buttons.add(no);
        buttons.add(yes);
        buttons.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    getFlightInfoGUI();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        buttons.setFocusable(true);
        buttons.requestFocusInWindow();


        confirmation.getContentPane().add(BorderLayout.SOUTH, buttons);
        confirmation.setLocationRelativeTo(null);
        confirmation.setVisible(true);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation.setVisible(false);
                flightBox();
            }
        });

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passengerInfoGUI();
                confirmation.setVisible(false);
            }
        });

    }


    public static void passengerInfoGUI() {
        JFrame frame = new JFrame("Purdue University Flight Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JLabel info = new JLabel("Please input your information below.");
        info.setFont(info.getFont().deriveFont(Font.BOLD, 16f));
        JPanel labelPanel = new JPanel();
        labelPanel.add(info);


        JLabel first = new JLabel("What is your first name?");
        JLabel last = new JLabel("What is your last name?");
        JLabel age = new JLabel("What is your age?");

        JTextArea firstName = new JTextArea();
        firstName.setLineWrap(true);
        firstName.getDocument().putProperty("filterNewlines",
                Boolean.TRUE);
        firstName.setVisible(true);
        JTextArea lastName = new JTextArea();
        lastName.setLineWrap(true);
        lastName.getDocument().putProperty("filterNewlines",
                Boolean.TRUE);
        lastName.setVisible(true);
        JTextArea ageOf = new JTextArea();
        ageOf.setLineWrap(true);
        ageOf.getDocument().putProperty("filterNewlines",
                Boolean.TRUE);
        ageOf.setVisible(true);


        JPanel one = new JPanel();
        one.add(first);
        JPanel two = new JPanel();
        two.add(firstName);
        JPanel three = new JPanel();
        three.add(last);
        JPanel four = new JPanel();
        four.add(lastName);
        JPanel five = new JPanel();
        five.add(age);
        JPanel six = new JPanel();
        six.add(ageOf);
        JPanel seven = new JPanel();
        JButton exit = new JButton("Exit");
        JButton next = new JButton("Next");
        seven.add(exit);
        seven.add(next);

        GridLayout grid = new GridLayout(8, 1);
        frame.setLayout(grid);
        frame.add(labelPanel);
        frame.add(one);
        frame.add(two);
        frame.add(three);
        frame.add(four);
        frame.add(five);
        frame.add(six);
        frame.add(seven);

        labelPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SLASH) {
                    getFlightInfoGUI();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        labelPanel.setFocusable(true);
        labelPanel.requestFocusInWindow();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int digitsInFirst = 0;
                int digitsInLast = 0;
                int ageLetters = 0;
                for (int i = 0; i < firstName.getText().length(); i++) {
                    if (!Character.isLetter(firstName.getText().charAt(i)))
                        digitsInFirst++;
                }
                if (digitsInFirst != 0 || firstName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid First Name!", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    for (int i = 0; i < lastName.getText().length(); i++) {
                        if (!Character.isLetter(lastName.getText().charAt(i)))
                            digitsInLast++;
                    }

                    if (digitsInLast != 0 || lastName.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Invalid Last Name!", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (int i = 0; i < ageOf.getText().length(); i++) {
                            if (!Character.isDigit(ageOf.getText().charAt(i)))
                                ageLetters++;
                        }
                        if (ageLetters != 0 || Integer.parseInt(ageOf.getText()) < 0 || ageOf.getText().replaceAll("\\s+", "").equals("")) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid" +
                                            " age!", "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            int option = JOptionPane.showConfirmDialog(null, "Are all the" +
                                            " details" + " you entered correct?\nThe passenger's name is "
                                            + firstName.getText() + " " + lastName.getText() + " and their age is "
                                            + ageOf.getText() + ".\n" + "If all the information shown is correct, " +
                                            "select the Yes button.\nOtherwise," + " select the No button",
                                    "Confirm Information", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                p = new Passenger(firstName.getText(), lastName.getText(),
                                        Integer.parseInt(ageOf.getText()));
                                frame.setVisible(false);
                                flightInfoGUI();
                            }

                        }
                    }
                }
            }

        });


    }

    public static void flightInfoGUI() {
        JFrame frame = new JFrame("Purdue University Flight Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JLabel airline = new JLabel("<html>Flight Information Displayed for " + air.getName() + " Airlines"
                + "<br>Enjoy your flight!<br>Flight is now boarding at Gate " + air.getGate() +
                "<br><br>" + air.getSpotsTaken() + " : 100");
        airline.setHorizontalTextPosition(JLabel.CENTER);
        airline.setFont(airline.getFont().deriveFont(Font.BOLD, 16f));


        JPanel one = new JPanel();
        one.add(airline);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < air.getPassengers().size(); i++) {

            if (i == air.getPassengers().size() - 1) {
                listModel.addElement(air.getPassengers().get(i));
            } else {
                listModel.addElement(air.getPassengers().get(i) + "\n");
            }
        }
        JList<String> list = new JList<String>(listModel);
        JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        air.updateSpotsTaken();
        air.getPassengers().add(p.toString());

        JButton exit = new JButton("Exit");
        JButton refresh = new JButton("Refresh Flight Status");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using the Purdue" +
                        " University Airline Management System!", "Thank you!", JOptionPane.PLAIN_MESSAGE);
                {
                    frame.setVisible(false);
                }

            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> newPassengers = new ArrayList<>();
                int difference = air.getPassengers().size() - listModel.getSize();
                for (int i = air.getPassengers().size() - difference; i < air.getPassengers().size(); i++) {
                    listModel.addElement(air.getPassengers().get(i));
                }
                airline.setText("<html>Flight Information Displayed for " + air.getName() + " Airlines"
                        + "<br>Enjoy your flight!<br>Flight is now boarding at Gate " + air.getGate() +
                        "<br><br>" + air.getSpotsTaken() + " : 100");

            }
        });

        JPanel buttons = new JPanel();
        buttons.add(exit);
        buttons.add(refresh);

        JPanel five = new JPanel();
        five.add(scrollPane);



        frame.getContentPane().add(BorderLayout.NORTH, one);
        frame.getContentPane().add(BorderLayout.CENTER, five);
        frame.getContentPane().add(BorderLayout.SOUTH, buttons);

        frame.setVisible(true);
    }

}

