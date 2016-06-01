package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Register players, generates a list of player which are connected, starts the
 * game, loads a saved game, set the color of a player
 * 
 * @version 1.0
 * @author Tim
 */
public interface Lobby extends Remote {

    public List<Player> getConnectedPlayers() throws RemoteException;

    /**
     * 
     * @param pl
     * @return a list of players which are connected to the lobby
     * @throws RemoteException
     * @version 1.0
     * @author Tim
     */
    public Player registerPlayer(Player pl) throws RemoteException;

    /**
     * 
     * @throws RemoteException
     * @version 1.0
     * @author Tim
     */
    public void startSpel() throws RemoteException;

    /**
     * 
     * @param safeFile
     * @throws RemoteException
     * @version 1.0
     * @author Tim
     */
    public void loadSafe(String safeFile) throws RemoteException;

    /**
     * 
     * @param pl
     * @param color
     * @throws RemoteException
     * @version 1.0
     * @author Tim
     */
    public void setColor(Player pl, Color color) throws RemoteException;

    /**
     * 
     * @throws RemoteException
     * @version 1.0
     * @author Tim
     */
    public void update() throws RemoteException;
    /**
     * 
     * @throws RemoteException
     * @version 1.0
     * @author Tim
     */
}
