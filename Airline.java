import java.io.Serializable;
import java.util.ArrayList;

/**
 * CS180 - Project 05
 * <p>
 * Airline.java Class; Interface for Airline
 * Didn't receive help from anywhere
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */

public interface Airline extends Serializable {

    String getName();

    String getInfo();

    int getSpotsTaken();

    void updateSpotsTaken();

    ArrayList<Passenger> getPassengers();

    void setGate(Gate gate);

    Gate getGate();
}
