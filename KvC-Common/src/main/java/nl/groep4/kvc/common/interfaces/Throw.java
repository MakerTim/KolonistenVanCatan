package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Gets values from dices checks when bandit is thrown.
 * 
 * @author Tim
 * @version 1.1
 */
public interface Throw extends Remote {

    /**
     * Generates random number 1 till 6.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void throwDice() throws RemoteException;

    /**
     * Gets total value.
     * 
     * @return Total value.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getValue() throws RemoteException;

    /**
     * Gets value from left dice.
     * 
     * @return Value from left dice.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getDiceLeft() throws RemoteException;

    /**
     * Gets value from right dice.
     * 
     * @return Value from right dice.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getDiceRight() throws RemoteException;

    /**
     * Checks if bandit is thrown.
     * 
     * @return True when bandit is thrown if not it returns false.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public boolean isBanditThrow() throws RemoteException;
}
