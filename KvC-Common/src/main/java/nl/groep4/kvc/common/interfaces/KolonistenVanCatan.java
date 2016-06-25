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
 * The interfaces of the ServerKolonistenVanCatan.
 * 
 * @author Tim
 * @version 1.1
 */
public interface KolonistenVanCatan extends Remote {

    /**
     * Creates the map.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void createMap() throws RemoteException;

    /**
     * Gets the round of the Game.
     * 
     * @return Gets current round.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getRound() throws RemoteException;

    /**
     * The game goes to the next round.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void nextRound() throws RemoteException;

    /**
     * The turn of the next player.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void nextTurn() throws RemoteException;

    /**
     * The game starts.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void start() throws RemoteException;

    /**
     * Gets true or false on if the bandit is moving.
     * 
     * @return True or false on isMovingRover.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public boolean isMovingRover() throws RemoteException;

    /**
     * Gets the state of the game.
     * 
     * @return The state of the game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public GameState getState() throws RemoteException;

    /**
     * Gets the Map of the game.
     * 
     * @return The Map.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public Map getMap() throws RemoteException;

    /**
     * Updates the Map of the game.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateMap() throws RemoteException;

    /**
     * Gets a list of the players in the game.
     * 
     * @return List of players.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public List<Player> getPlayers() throws RemoteException;

    /**
     * Gets the order of the players in the game.
     * 
     * @return The order of the players.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public List<Player> getPlayersOrded() throws RemoteException;

    /**
     * Gets whose turn it is.
     * 
     * @return Whose turn it is.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public Player getTurn() throws RemoteException;

    /**
     * Places a building.
     * 
     * @param newOwner
     *            The player who places the building.
     * @param coord
     *            The coordinates where the building will come.
     * @param type
     *            The type of building.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void placeBuilding(Player newOwner, Coordinate coord, BuildingType type) throws RemoteException;

    /**
     * Places a street.
     * 
     * @param newOwner
     *            The player who places the street.
     * @param coord
     *            The coordinates where the street will come.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void placeStreet(Player newOwner, Coordinate coord) throws RemoteException;

    /**
     * Throws the dices in the game.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void throwDices() throws RemoteException;

    /**
     * Gives the right amount of resources to the right players.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void distribute() throws RemoteException;

    /**
     * The player buys a street.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void buyStreet() throws RemoteException;

    /**
     * The player buys a village.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void buyVillage() throws RemoteException;

    /**
     * The player buy a city.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void buyCity() throws RemoteException;

    /**
     * The player buys a card.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void buyCard() throws RemoteException;

    /**
     * The player uses a card.
     * 
     * @param card
     *            The card that the player will use.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void useCard(Player player, Card card) throws RemoteException;

    /**
     * Execute the trade.
     * 
     * @param tradeKey
     *            A unique key to validate the trade.
     * @param with
     *            The player with whom the trade will be done.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void trade(UUID tradeKey, Player with) throws RemoteException;

    /**
     * The trades will be added to the TradePane so other players can respond on
     * them.
     * 
     * @param player
     *            The owner of the trade.
     * @param request
     *            The request of the player.
     * @param reward
     *            The reward for the request.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void addTrade(Player player, java.util.Map<Resource, Integer> request,
	    java.util.Map<Resource, Integer> reward) throws RemoteException;

    /**
     * When the trade is done, the trade will be removed from the TradePane.
     * 
     * @param key
     *            The unique key of the trade.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void remvoeTrade(UUID key) throws RemoteException;

    /**
     * The pause pane will be opened.
     * 
     * @param requester
     *            The player who wants to pause the game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void openPausePane(Player requester) throws RemoteException;

    /**
     * The pause pane will be closed.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void closePausePane() throws RemoteException;

    /**
     * The player reconnects with the server.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void reconnect() throws RemoteException;

    /**
     * The player who uses the card.
     * 
     * @param who
     *            The player who uses the card.
     * @param resource
     *            The resource that is chosen.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void targetInvention(Player who, Resource resource) throws RemoteException;

    /**
     * The player who uses the card.
     * 
     * @param who
     *            The player who uses the card.
     * @param resource
     *            The resource that is chosen.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void targetMonopoly(Player who, Resource resource) throws RemoteException;

    /**
     * Takes the Bandit from the tile.
     * 
     * @param turn
     *            The player who takes the bandit.
     * @param position
     *            The position of where the bandit is on.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void moveFromRover(Player turn, Coordinate position) throws RemoteException;

    /**
     * Places the Bandit on a tile.
     * 
     * @param turn
     *            The player who places the bandit.
     * @param position
     *            The position of where the bandit will be placed on.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void moveToRover(Player turn, Coordinate position) throws RemoteException;

    /**
     * Gets last throw.
     * 
     * @return Last throw.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public Throw getLastThrow() throws RemoteException;

    /**
     * Updates resources.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateResources() throws RemoteException;
}
