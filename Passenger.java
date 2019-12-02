import java.io.Serializable;
/**
 * CS180 - Project 05
 * <p>
 * Passenger.java Class; Creates passenger objects
 * Didn't receive help from anywhere
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 12/2/2019
 */
public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private Gate gate;
    private BoardingPass pass;

    public Passenger(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gate getGate() {
        return gate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public void setBoardingPass(BoardingPass pass)
    {
        this.pass = pass;
    }

    public BoardingPass getBoardingPass()
    {
        return pass;
    }



    public String toString() {
        String first = firstName.toUpperCase().substring(0, 1);
        String last = lastName.toUpperCase();
        return first + "." + " " + last + ", " + age;
    }

}
