package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Skeleton for the Lobby
 * 
 * @author Tim
 * @version 1.0
 */
public interface UpdateLobby extends Updatable<Lobby> {

    /**
     * Updates the color of the player
     * 
     * @param pl
     *            the player
     * @param newColor
     *            the color from player
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void updatePlayerColor(Player pl, Color newColor) throws RemoteException;

    /**
     * Starts the game in the lobby
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void start() throws RemoteException;

}
