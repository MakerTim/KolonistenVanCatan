package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.groep4.kvc.common.map.Map;

/**
 * Handles registration for user and retrieves map
 * 
 * @version 1.0
 * @author Tim
 */
public interface KolonistenVanCatan extends Remote {
    /**
     * Registers client machine to server
     * 
     * @version 1.0
     * @author Tim
     */
    public void registerClient(Client client) throws RemoteException;

    /**
     * Unregisters client machine from the server
     * 
     * @version 1.0
     * @author Tim
     */
    public void unregisterClient(Client client) throws RemoteException;

    /**
     * Retrieves map data from Map and displays it to player
     * 
     * @version 1.0
     * @author Tim
     */
    public Map getMap() throws RemoteException;

}
