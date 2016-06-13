package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.groep4.kvc.server.model.ServerLobby;

/**
 * Sets updatable for players closing and ingame pop-up parameters
 * 
 * @author Tim
 * @version 1.0
 * @param <T>
 */
public interface Updatable<T> extends Remote {

    /**
     * 
     * @param model
     *            Refers to T to set model
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void setModel(T model) throws RemoteException;

    /**
     * 
     * @param key
     *            Refers to closing settings in {@link ServerLobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void close(String key) throws RemoteException;

    /**
     * 
     * @param key
     *            Refers to ingame pop-up in {@link ServerLobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void popup(String key) throws RemoteException;

    public default void testConnection() throws RemoteException {
    }
}
