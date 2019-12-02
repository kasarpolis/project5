import java.io.Serializable;
/**
 * CS180 - Project 05
 * <p>
 * Gate.java Class; Characteristics for the Gate
 * Didn't receive help from anywhere
 *
 * @author Alexandra Sarpolis, asarpoli@purdue.edu; Ray Ye, yer@purdue.edu
 * @version 12/2/2019
 */
public class Gate implements Serializable {

    private char terminal;
    private int gate;

    public Gate() {
        char[] letters = {'A', 'B', 'C'};
        terminal = letters[(int) (Math.random() * 2)];
        gate = (int) (Math.random() * 18) + 1;
    }

    public Gate(char terminal, int gate)
    {
        this.terminal = terminal;
        this.gate = gate;
    }

    public char getTerminal() {
        return terminal;
    }

    public int getGate() {
        return gate;
    }

    public void setTerminal(char terminal) {
        this.terminal = terminal;
    }

    public String toString()
    {
        return terminal + Integer.toString(gate);
    }

}
