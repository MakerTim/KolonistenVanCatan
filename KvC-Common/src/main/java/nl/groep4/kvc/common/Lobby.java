package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

/**
 * Register players, generates a list of player which are connected, 
 * starts the game, loads a saved game, set the color of a player
 * 
 * @version 1.0
 * @author Tim
 */

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Updatable;

public interface Lobby extends Remote {

    public List<Player> getConnectedPlayers() throws RemoteException;

    public void registerPlayer(String username) throws RemoteException;

    public void unregisterPlayer(Player pl) throws RemoteException;

    public void startGame() throws RemoteException;

    public void loadSafe(String safeFile) throws RemoteException;

    public void setColor(Player pl, Color color) throws RemoteException;

    public default void update() throws RemoteException {
	for (Iterator<Updatable<Lobby>> updateableIt = getUpdatable().iterator(); updateableIt.hasNext();) {
	    Updatable<Lobby> updatable = updateableIt.next();
	    try {
		updatable.update(this);
	    } catch (Exception ex) {
		updateableIt.remove();
	    }
	}
    }

    public List<Updatable<Lobby>> getUpdatable() throws RemoteException;

    public void registerUpdateable(Updatable<Lobby> updateable) throws RemoteException;
}
