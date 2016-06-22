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
 * The interfaces of the ServerKolonistenVanCatan
 * 
 * @author Tim
 * @version
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
     * Moves the bandit / rover
     * 
     * @throws RemoteException
     *             signals when exception occurs
     */
    public boolean isMovingRover() throws RemoteException;

    /**
     * Gets the state of the game
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

    public void updateMap() throws RemoteException;

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

    /**
     * Execute the trade
     * 
     * @param tradeKey
     *            a unique key to validate the trade
     * @param with
     *            the player with whom the trade will be done
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void trade(UUID tradeKey, Player with) throws RemoteException;

    /**
     * The trades will be added to the TradePane so other players can respond on
     * them
     * 
     * @param player
     *            the owner of the trade
     * @param request
     *            the request of the player
     * @param reward
     *            the reward for the request
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void addTrade(Player player, java.util.Map<Resource, Integer> request,
	    java.util.Map<Resource, Integer> reward) throws RemoteException;

    /**
     * When the trade is done, the trade will be removed from the TradePane
     * 
     * @param key
     *            the unique key of the trade
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void remvoeTrade(UUID key) throws RemoteException;

    /**
     * The pause pane will be opened
     * 
     * @param requester
     *            the player who wants to pause the game
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void openPausePane(Player requester) throws RemoteException;

    /**
     * The pause pane will be closed
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void closePausePane() throws RemoteException;

    /**
     * The player reconnects with the server
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void reconnect() throws RemoteException;

    /**
     * The player who uses the card
     * 
     * @param who
     *            the player who uses the card
     * @param resource
     *            the resource that is chosen
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void targetInvention(Player who, Resource resource) throws RemoteException;

    /**
     * The player who uses the card
     * 
     * @param who
     *            the player who uses the card
     * @param resource
     *            the resource that is chosen
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void targetMonopoly(Player who, Resource resource) throws RemoteException;

    /**
     * Takes the Bandit from the tile
     * 
     * @param turn
     *            the player who takes the bandit
     * @param position
     *            the position of where the bandit is on
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void moveFromRover(Player turn, Coordinate position) throws RemoteException;

    /**
     * Places the Bandit on a tile
     * 
     * @param turn
     *            the player who places the bandit
     * @param position
     *            the position of where the bandit will be placed on
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void moveToRover(Player turn, Coordinate position) throws RemoteException;
}
