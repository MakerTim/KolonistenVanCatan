package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * Register players, generates a list of player which are connected, starts the
 * game, loads a saved game, set the color of a player
 * 
 * @version 1.0
 * @author Tim
 */
public interface Lobby extends Remote {

    public static enum State {
	LOBBY, LOBBY_FULL, STARTING;

	public boolean isStarting() {
	    return this == State.STARTING;
	}
    }

    /**
     * 
     * @return a list of players which are connected to the lobby
     * @throws RemoteException
     */
    public List<Player> getConnectedPlayers() throws RemoteException;

    /**
     * 
     * @param username
     * @throws RemoteException
     */
    public void registerPlayer(String username) throws RemoteException;

    /**
     * 
     * @throws RemoteException
     */
    public void startGame() throws RemoteException;

    /**
     * 
     * @param pl
     * @throws RemoteException
     */
    public void unregisterPlayer(Player pl) throws RemoteException;

    /**
     * 
     * @param safeFile
     * @throws RemoteException
     */
    public void loadSafe(String safeFile) throws RemoteException;

    /**
     * 
     * @param pl
     * @param color
     * @throws RemoteException
     */
    public void setColor(Player pl, Color color) throws RemoteException;

    /**
     * 
     * @throws RemoteException
     */
    public default void update() throws RemoteException {
	for (Iterator<Player> playerIt = getConnectedPlayers().iterator(); playerIt.hasNext();) {
	    Player pl = playerIt.next();
	    try {
		Updatable<Lobby> updatable = (Updatable<Lobby>) pl.getUpdateable();
		updatable.update(this);
	    } catch (NullPointerException ex) {
		ex.printStackTrace();
	    } catch (Exception ex) {
		System.out.printf("%s has been kicked. %s\n", pl.getUsername(), ex.toString());
		playerIt.remove();
	    }
	}
    }

    public State getState() throws RemoteException;

    public Player getServerPlayer(Player player) throws RemoteException;

    public void registerView(Player player, Updatable<Lobby> updateable) throws RemoteException;
}
