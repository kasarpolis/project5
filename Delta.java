import java.util.ArrayList;

/**
 * CS180 - Project 05
 * <p>
 * Delta Class; Characteristics for Delta flight
 * Didn't receive help from anywhere
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */
public class Delta implements Airline {

    private String name;
    private String info;
    private int spotsTaken = 0;
    private ArrayList<Passenger> passengers;
    private Gate gate;

    public Delta() {
        name = "Delta";
        info = "<html>Delta Airlines is proud to serve BoilerMakers from Purdue University.<br>We fly all over the country " +
                "with stops from New York to California.<br>We have both first class amenities and more affordable " +
                "options.<br>" +
                "We also have comfortable seats and free Wifi for all of our passengers.<br>" +
                "We hope you choose Delta Airlines for your next trip!";
        passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public int getSpotsTaken() {
        return spotsTaken;
    }

    public void updateSpotsTaken() {
        spotsTaken++;
    }

    public ArrayList<Passenger> getPassengers()
    {
        return passengers;
    }

    public void setGate(Gate gate)
    {
        this.gate = gate;
    }

    public Gate getGate()
    {
        return gate;
    }

}
