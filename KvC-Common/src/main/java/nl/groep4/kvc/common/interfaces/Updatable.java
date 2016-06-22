package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Sets updatable for players closing and ingame pop-up parameters
 * 
 * @author Tim
 * @version 1.0
 * @param <T>
 */
public interface Updatable<T> extends Remote {

    /**
     * sets model
     * 
     * @param model
     *            Refers to T to set model
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void setModel(T model) throws RemoteException;

    /**
     * Used for closing
     * 
     * @param key
     *            Refers to closing settings
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void close(String key) throws RemoteException;

    /**
     * Shows pop-ups with messages
     * 
     * @param key
     *            Refers to ingame pop-up
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void popup(String key) throws RemoteException;

    /**
     * A test connection
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public default void testConnection() throws RemoteException {
    }
}
