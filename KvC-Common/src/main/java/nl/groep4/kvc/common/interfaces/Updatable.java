package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Sets updatable for players closing and ingame pop-up parameters.
 * 
 * @author Tim
 * @version 1.1
 * @param <T>
 *            Pane class which is used
 */
public interface Updatable<T> extends Remote {

    /**
     * Sets model.
     * 
     * @param model
     *            The model.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setModel(T model) throws RemoteException;

    /**
     * Used for closing.
     * 
     * @param key
     *            The item that needs to be closed.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void close(String key) throws RemoteException;

    /**
     * Shows pop-ups with messages.
     * 
     * @param key
     *            The item that needs a popup.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void popup(String key) throws RemoteException;

    /**
     * A test connection.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void testConnection() throws RemoteException {
    }
}
