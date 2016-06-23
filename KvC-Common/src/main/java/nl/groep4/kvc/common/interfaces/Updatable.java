package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Sets updatable for players closing and ingame pop-up parameters
 * 
 * @author Tim
 * @version 1.0
 * @param <T>
 *            Pane class which used
 */
public interface Updatable<T> extends Remote {

    /**
     * Sets model
     * 
     * @param model
     *            Refers to T to set model
     * @throws RemoteException
     *             In case connection between RMI and client is lost
     */
    public void setModel(T model) throws RemoteException;

    /**
     * Used for closing
     * 
     * @param key
     *            Refers to closing settings
     * @throws RemoteException
     *             In case connection between RMI and client is lost
     */
    public void close(String key) throws RemoteException;

    /**
     * Shows pop-ups with messages
     * 
     * @param key
     *            Refers to ingame pop-up
     * @throws RemoteException
     *             In case connection between RMI and client is lost
     */
    public void popup(String key) throws RemoteException;

    /**
     * A test connection
     * 
     * @throws RemoteException
     *             Signals when exception occurs
     */
    public default void testConnection() throws RemoteException {
    }
}
