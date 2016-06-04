package nl.groep4.kvc.common.interfaces;

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

    public static enum State {
	LOBBY, LOBBY_FULL, STARTING, STARTED;

	public boolean isAfterStart() {
	    return this == State.STARTING || this == State.STARTED;
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
     * @return
     * @throws RemoteException
     */
    public Player registerPlayer(String username) throws RemoteException;

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
    public void loadSave(String safeFile) throws RemoteException;

    /**
     * 
     * @param pl
     * @param color
     * @throws RemoteException
     */
    public void setColor(Player pl, Color color) throws RemoteException;

    public State getState() throws RemoteException;

    public Player getServerPlayer(Player player) throws RemoteException;

    public void registerView(Player player, UpdatableLobby updateable) throws RemoteException;
}
