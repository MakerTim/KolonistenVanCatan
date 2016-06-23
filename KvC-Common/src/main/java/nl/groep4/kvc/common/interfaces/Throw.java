package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Gets values from dices checks when bandit is thrown.
 * 
 * @author Tim
 * @version 1.0
 */
public interface Throw extends Remote {

    /**
     * Generates random number 1 till 6
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public void throwDice() throws RemoteException;

    /**
     * Gets total value
     * 
     * @return total value
     * @throws RemoteException
     *             signals when exception occurs
     */
    public int getValue() throws RemoteException;

    /**
     * Gets value from left dice
     * 
     * @return value from left dice
     * @throws RemoteException
     *             signals when exception occurs
     */
    public int getDiceLeft() throws RemoteException;

    /**
     * Gets value from right dice
     * 
     * @return value from right dice
     * @throws RemoteException
     *             signals when exception occurs
     */
    public int getDiceRight() throws RemoteException;

    /**
     * Checks if bandit is thrown
     * 
     * @return true when bandit is thrown if not it returns false
     * @throws RemoteException
     *             signals when exception occurs
     */
    public boolean isBanditThrow() throws RemoteException;
}
