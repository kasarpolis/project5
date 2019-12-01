import java.util.ArrayList;

/**
 * CS180 - Project 05
 * <p>
 * Alaska.java Class; Characteristics for Alaska flight
 * Didn't receive help from anywhere
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 11/21/2019
 */
public class Alaska implements Airline {

    private String name;
    private String info;
    private int spotsTaken = 0;
    private ArrayList<Passenger> passengers;
    private Gate gate;


    public Alaska() {
        name = "Alaska";
        info = "<html>Alaska Airlines is proud to serve BoilerMakers from Purdue University.<br>We primarily fly westward, " +
                "often" +
                "with stops in Alaska and California.<br>We have first class amenities, even in coach class.<br>" +
                "We provide fun snacks, such as pretzels and goldfish.<br> We also have comfortable seats and free Wifi." +
                "<br>We hope you choose Alaska Airlines for your next itinerary!";
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
