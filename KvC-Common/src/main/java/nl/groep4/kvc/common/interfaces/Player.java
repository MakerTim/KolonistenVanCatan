package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;

/**
 * Interface of everything the player can do
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Remote {

    /**
     * Gets the amount of remaining streets that are available
     * 
     * @return the amount of remaining available streets
     * @throws RemoteException
     *             any remotely invoked method
     */
    public int getRemainingStreets() throws RemoteException;

    /**
     * Adds a street to the amount of remaining streets
     * 
     * @param streets
     *            streets on the map
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void addRemainingStreets(int streets) throws RemoteException;

    /**
     * Removes a street of the amount of remaining streets
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void removeRemainingStreet() throws RemoteException {
	addRemainingStreets(-1);
    }

    /**
     * Returns true if there are still streets available on the map, otherwise
     * returns a false
     * 
     * @return true or false on available streets
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default boolean hasRemainingStreets() throws RemoteException {
	return getRemainingStreets() > 0;
    }

    /**
     * Gets the amount of remaining streets that are available
     * 
     * @return the amount of remaining available streets
     * @throws RemoteException
     *             any remotely invoked method
     */
    public int getRemainingVillages() throws RemoteException;

    /**
     * Adds a village to the amount of remaining villages
     * 
     * @param villages
     *            villages on the map
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void addRemainingVillages(int villages) throws RemoteException;

    /**
     * Removes a village of the amount of remaining villages
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void removeRemainingVillage() throws RemoteException {
	addRemainingVillages(-1);
    }

    /**
     * Returns true if there are still villages available on the map, otherwise
     * returns a false
     * 
     * @return true or false on available villages
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default boolean hasRemainingVillages() throws RemoteException {
	return getRemainingVillages() > 0;
    }

    /**
     * Gets the amount of remaining cities that are available
     * 
     * @return the amount of remaining available cities
     * @throws RemoteException
     *             any remotely invoked method
     */
    public int getRemainingCitys() throws RemoteException;

    /**
     * Adds a city to the amount of remaining cities
     * 
     * @param citys
     *            cities on the map
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void addRemainingCitys(int citys) throws RemoteException;

    /**
     * Removes a city of the amount of remaining cities
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void removeRemainingCity() throws RemoteException {
	addRemainingCitys(-1);
    }

    /**
     * Returns true if there are still cities available on the map, otherwise
     * returns a false
     * 
     * @return true or false on available cities
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default boolean hasRemainingCitys() throws RemoteException {
	return getRemainingCitys() > 0;
    }

    /**
     * Checks if there is a connection with a player
     * 
     * @return true or false on if there is a view or not
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public default boolean hasConnectionErrors() throws RemoteException {
	return getUpdateable() == null;
    }

    /**
     * Gets the username of the player
     * 
     * @return the username of the player
     * @throws RemoteException
     *             any remotely invoked method
     */
    public String getUsername() throws RemoteException;

    /**
     * Registers if the is a view
     * 
     * @param updatable
     *            the view of the map
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void registerUpdateable(Updatable<?> updatable) throws RemoteException;

    /**
     * Gets the view of the game
     * 
     * @return the view
     * @throws RemoteException
     *             any remotely invoked method
     */
    public Updatable<?> getUpdateable() throws RemoteException;

    /**
     * General method to update.
     * 
     * @param <T>
     *            Pane class which is used.
     * @param type
     *            The updatable type.
     * @return The view of the game.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default <T extends Updatable<?>> T getUpdateable(Class<T> type) throws RemoteException {
	return (T) getUpdateable();
    }

    /**
     * Makes the items on the map whom are selectable, selectable
     * 
     * @param selectables
     *            the items
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void setSelectable(SelectState selectables) throws RemoteException {
	getUpdateable(UpdateMap.class).setSelectable(selectables);
    }

    public Color getColor() throws RemoteException;

    /**
     * Gets the color of the player in the game
     * 
     * @param color
     *            color of the player
     * 
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void setColor(Color color) throws RemoteException;

    /**
     * Adds a card to the player
     * 
     * @param drawnCard
     *            the card that will be added to the player
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void addCard(Card drawnCard) throws RemoteException;

    /**
     * The player uses the selected card
     * 
     * @param usedCard
     *            the selected card
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void useCard(Card usedCard) throws RemoteException;

    /**
     * Gets a list of all the cards from the player
     * 
     * @return a list of cards from the player
     * @throws RemoteException
     *             any remotely invoked method
     */
    public List<Card> getCards() throws RemoteException;

    /**
     * Gets a map with the resources and the amount of resources
     * 
     * @return a map with the resources and the amount of resources
     * @throws RemoteException
     *             any remotely invoked method
     */
    public EnumMap<Resource, Integer> getResources() throws RemoteException;

    /**
     * Gets the amount of a resource
     * 
     * @param resource
     *            the chosen resource
     * @return the amount of a resource
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default int getResourceAmount(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	return getResources().get(resource);
    }

    /**
     * Gives resources with the right amount
     * 
     * @param resource
     *            the resource of choice
     * @param amount
     *            the amount of the resource
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void giveResource(Resource resource, int amount) throws RemoteException {
	for (int i = 0; i < amount; i++) {
	    giveResource(resource);
	}
    }

    /**
     * Give the right resource to the player
     * 
     * @param resource
     *            the resource
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void giveResource(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	getResources().put(resource, getResources().get(resource) + 1);
    }

    /**
     * Takes a resource
     * 
     * @param resource
     *            the resource that will be taken
     * @param amount
     *            the amount of resources that will be taken
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void takeResource(Resource resource, int amount) throws RemoteException {
	for (int i = 0; i < amount; i++) {
	    takeResource(resource);
	}
    }

    /**
     * Takes a resource
     * 
     * @param resource
     *            the resource that will be taken
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default void takeResource(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	getResources().put(resource, Math.max(0, getResources().get(resource) - 1));
    }

    /**
     * True or false on if the player has the chosen resource
     * 
     * @param resource
     *            the chosen resource
     * @param amount
     *            the amount of the resource
     * @return true or false on if the player has the chosen resource
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default boolean hasResource(Resource resource, int amount) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	return getResources().get(resource) >= amount;
    }

    /**
     * True or false on if the player has the chosen resource
     * 
     * @param resource
     *            the chosen resource
     * @return true or false on if the player has the chosen resource
     * @throws RemoteException
     *             any remotely invoked method
     */
    public default boolean hasResource(Resource resource) throws RemoteException {
	return hasResource(resource, 1);
    }

    /**
     * Gets the points of the player
     * 
     * @return the points of the player
     * @throws RemoteException
     *             any remotely invoked method
     */
    public int getPoints() throws RemoteException;

    /**
     * Sets the points of the player
     * 
     * @param score
     *            the score of the player
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void setPoints(int score) throws RemoteException;

    /**
     * True or false on if the player has the most knights
     * 
     * @return true or false on if the player has the most knights
     * @throws RemoteException
     *             any remotely invoked method
     */
    public boolean hasMostRidders() throws RemoteException;

    /**
     * Sets that the player has the most knights
     * 
     * @param hasMost
     *            true or false on if the player has the most knights
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void setMostRidder(boolean hasMost) throws RemoteException;

    /**
     * True or false on if the player has the longest road
     * 
     * @return true or false on if the player has the longest road
     * @throws RemoteException
     *             any remotely invoked method
     */
    public boolean hasLongestRoad() throws RemoteException;

    /**
     * Sets that the player has the longest road
     * 
     * @param hasLongest
     *            true or false on if the player has the longest road
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void setHasLongestRoad(boolean hasLongest) throws RemoteException;
}
