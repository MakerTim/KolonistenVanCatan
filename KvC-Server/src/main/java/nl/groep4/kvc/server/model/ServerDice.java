package nl.groep4.kvc.server.model;

import java.io.Serializable;

/**
 * Dice class that takes care of the outcome of the thrown dice
 * 
 * @author Luc
 * @author Tim
 * @version 1.0
 */
public class ServerDice implements Serializable {

    private static final long serialVersionUID = 1111993L;
    private int dice;

    /**
     * Throws the dice by generating a random number between 1 and 6
     */
    public void throwDice() {
	dice = 1 + (int) (Math.random() * 6D);
    }

    /**
     * Gets the value of the thrown dice
     * 
     * @return the value of the thrown dice
     */
    public int getValue() {
	return dice;
    }

}
