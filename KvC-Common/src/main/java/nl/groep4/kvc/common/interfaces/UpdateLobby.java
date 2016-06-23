package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Skeleton for the Lobby.
 * 
 * @author Tim
 * @version 1.1
 */
public interface UpdateLobby extends Updatable<Lobby> {

    /**
     * Updates the color of the player.
     * 
     * @param pl
     *            The player.
     * @param newColor
     *            The color from player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updatePlayerColor(Player pl, Color newColor) throws RemoteException;

    /**
     * Starts the game in the lobby.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void start() throws RemoteException;

}
