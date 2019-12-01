import java.io.Serializable;
/**
 * CS180 - Project 05
 * <p>
 * BoardingPass.java Class; Information about Boarding Pass
 * Didn't receive help from anywhere
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */
public class BoardingPass implements Serializable {
    private Passenger passenger;
    private Airline air;

    public BoardingPass(Passenger passenger, Airline air) {
        this.passenger = passenger;
        this.air = air;
    }

    public String toString() {
        return "<html>-----------------------------------------------------"
                + "<br>BOARDING PASS FOR FLIGHT 18000 WITH " + air.getName().toUpperCase() + " AIRLINES<br>" +
                "PASSENGER FIRST NAME: " + passenger.getFirstName().toUpperCase() + "<br>"
                + "PASSENGER LAST NAME: " + passenger.getLastName().toUpperCase() + "<br>PASSENGER AGE: " +
                passenger.getAge() +
                "<br>You can now begin boarding at Gate A16"
                + "<br>-----------------------------------------------------";
    }
}

