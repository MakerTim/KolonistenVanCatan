package nl.groep4.kvc.server.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Dice class that takes care of the outcome of the thrown dice
 * 
 * @author Luc
 * @version 1.0
 */
public class ServerDice implements Remote {

    private int dice;

    /**
     * Throws the dice by generating a random number between 1 and 6
     * 
     * @throws RemoteException
     */
    public void throwDice() throws RemoteException {
	dice = 1 + (int) (Math.random() * 6D);
    }

    /**
     * Gets the value of the thrown dice
     * 
     * @return the value of the thrown dice
     * @throws RemoteException
     */
    public int getValue() throws RemoteException {
	return dice;
    }

}
