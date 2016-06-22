package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;

/**
 * Gets all activities that can be done
 * 
 * @author Tim
 * @version 1.0
 */
public interface KolonistenVanCatan extends Remote {

    /**
     * creates the map
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public void createMap() throws RemoteException;

    /**
     * gets current round
     */
    public int getRound() throws RemoteException;

    /**
     * Goes to the next round
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public void nextRound() throws RemoteException;

    /**
     * Goes to next turn
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public void nextTurn() throws RemoteException;

    /**
     * Starts the game
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public void start() throws RemoteException;

    /**
     * Gets state of the game
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public GameState getState() throws RemoteException;

    /**
     * Gets the map
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public Map getMap() throws RemoteException;

    public void updateModel() throws RemoteException;

    public List<Player> getPlayers() throws RemoteException;

    public List<Player> getPlayersOrded() throws RemoteException;

    public Player getTurn() throws RemoteException;

    public void placeBuilding(Player newOwner, Coordinate coord, BuildingType type) throws RemoteException;

    public void placeStreet(Player newOwner, Coordinate coord) throws RemoteException;

    public void throwDices() throws RemoteException;

    public void distribute() throws RemoteException;

    public void buyStreet() throws RemoteException;

    public void buyVillage() throws RemoteException;

    public void buyCity() throws RemoteException;

    public void buyCard() throws RemoteException;

    public void useCard(Card card) throws RemoteException;

    public void trade(UUID tradeKey, Player with) throws RemoteException;

    public void addTrade(Player player, java.util.Map<Resource, Integer> request,
	    java.util.Map<Resource, Integer> reward) throws RemoteException;

    public void remvoeTrade(UUID key) throws RemoteException;

    public void openPausePane(Player requester) throws RemoteException;

    public void closePausePane() throws RemoteException;

    public void reconnect() throws RemoteException;

    public void targetInvention(Player who, Resource resource) throws RemoteException;

    public void targetMonopoly(Player who, Resource resource) throws RemoteException;
}
