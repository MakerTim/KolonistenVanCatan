package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Register players, generates a list of player which are connected, 
 * starts the game, loads a saved game, set the color of a player
 * 
 * @version 1.0
 * @author Tim
 */

import nl.groep4.kvc.common.enumeration.Color;

public interface Lobby extends Remote {

    public List<Player> getConnectedPlayers() throws RemoteException;

    public Player registerPlayer(Player pl) throws RemoteException;

    public void unregisterPlayer(Player pl) throws RemoteException;

    public void startSpel() throws RemoteException;

    public void loadSafe(String safeFile) throws RemoteException;

    public void setColor(Player pl, Color color) throws RemoteException;

    public void update() throws RemoteException;

}
