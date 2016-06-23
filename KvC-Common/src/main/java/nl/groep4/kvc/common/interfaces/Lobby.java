package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Gets game, state, players. Sets game, state, color. Starts game , disconnects
 * player, registers player and loads save.
 * 
 * @author Tim
 * @version 1.1
 */
public interface Lobby extends Remote {

    /**
     * All states.
     */
    public static enum State {
	LOBBY, STARTING, IN_GAME
    }

    /**
     * Gets game.
     * 
     * @return The game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public KolonistenVanCatan getGame() throws RemoteException;

    /**
     * Sets game.
     * 
     * @param kvc
     *            Variable of KolonistenVanCatan.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setGame(KolonistenVanCatan kvc) throws RemoteException;

    /**
     * Sets state.
     * 
     * @param state
     *            Current state of the game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setState(State state) throws RemoteException;

    /**
     * Gets state.
     * 
     * @return State of the game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public State getState() throws RemoteException;

    /**
     * Registers player.
     * 
     * @param player
     *            Player to be register.
     * @return Registered player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public Player registerPlayer(String player) throws RemoteException;

    /**
     * Gets players
     * 
     * @return List with players.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public List<Player> getPlayers() throws RemoteException;

    /**
     * Disconnects players.
     * 
     * @param pl
     *            Player to disconnect.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void disconnect(Player pl) throws RemoteException;

    /**
     * Sets color.
     * 
     * @param pl
     *            Player to set the color.
     * @param newColor
     *            New color set to player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setColor(Player pl, Color newColor) throws RemoteException;

    /**
     * Start the game.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void startGame() throws RemoteException;

    /**
     * Loads save.
     * 
     * @param save
     *            Saves the game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void loadSave(String save) throws RemoteException;

}
