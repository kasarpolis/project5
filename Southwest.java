import java.util.ArrayList;

/**
 * CS180 - Project 05
 * <p>
 * Southwest.java Class; Characteristics from Southwest flight
 * Didn't receive help from anywhere
 * *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */
public class Southwest implements Airline {
    private String name;
    private String info;
    private int spotsTaken = 0;
    private ArrayList<Passenger> passengers;
    private Gate gate;


    public Southwest() {
        name = "Southwest";
        info = "<html>Southwest Airlines is proud to serve BoilerMakers from Purdue University.<br>We primarily fly " +
                "southwest, often" +
                "with stops in Texas and Nevada.<br>We have amenities to fit people from all walks of life.<br>" +
                "We provide gourmet snacks and beverages for our passengers.<br>" +
                "We also have comfortable seats with lots of leg room.<br>" +
                "We hope you choose Southwest Airlines for your next flight!<br>";
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
