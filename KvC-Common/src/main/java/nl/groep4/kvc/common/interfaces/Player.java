package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;

/**
 * Interface of everything the player can do.
 * 
 * @version 1.2
 * @author Tim
 */
public interface Player extends Remote {

    /**
     * Gets the amount of remaining streets that are available.
     * 
     * @return The amount of remaining available streets.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getRemainingStreets() throws RemoteException;

    /**
     * Adds a street to the amount of remaining streets.
     * 
     * @param streets
     *            Streets on the map.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void addRemainingStreets(int streets) throws RemoteException;

    /**
     * Removes a street of the amount of remaining streets.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void removeRemainingStreet() throws RemoteException {
	addRemainingStreets(-1);
    }

    /**
     * Returns true if there are still streets available on the map, otherwise
     * returns a false.
     * 
     * @return True or false on available streets.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default boolean hasRemainingStreets() throws RemoteException {
	return getRemainingStreets() > 0;
    }

    /**
     * Gets the amount of remaining streets that are available.
     * 
     * @return The amount of remaining available streets.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getRemainingVillages() throws RemoteException;

    /**
     * Adds a village to the amount of remaining villages.
     * 
     * @param villages
     *            Villages on the map.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void addRemainingVillages(int villages) throws RemoteException;

    /**
     * Removes a village of the amount of remaining villages.
     * 
     * @throws RemoteException
     *             Any remotely invoked method
     */
    public default void removeRemainingVillage() throws RemoteException {
	addRemainingVillages(-1);
    }

    /**
     * Returns true if there are still villages available on the map, otherwise
     * returns a false.
     * 
     * @return True or false on available villages.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default boolean hasRemainingVillages() throws RemoteException {
	return getRemainingVillages() > 0;
    }

    /**
     * Gets the amount of remaining cities that are available.
     * 
     * @return The amount of remaining available cities.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getRemainingCitys() throws RemoteException;

    /**
     * Adds a city to the amount of remaining cities.
     * 
     * @param citys
     *            Cities on the map.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void addRemainingCitys(int citys) throws RemoteException;

    /**
     * Removes a city of the amount of remaining cities.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void removeRemainingCity() throws RemoteException {
	addRemainingCitys(-1);
    }

    /**
     * Returns true if there are still cities available on the map, otherwise
     * returns a false.
     * 
     * @return True or false on available cities.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default boolean hasRemainingCitys() throws RemoteException {
	return getRemainingCitys() > 0;
    }

    /**
     * Checks if there is a connection with a player.
     * 
     * @return True or false on if there is a view or not.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default boolean hasConnectionErrors() throws RemoteException {
	return getUpdateable() == null;
    }

    /**
     * Gets the username of the player.
     * 
     * @return The username of the player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public String getUsername() throws RemoteException;

    /**
     * Registers if the is a view.
     * 
     * @param updatable
     *            The view of the map.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void registerUpdateable(Updatable<?> updatable) throws RemoteException;

    /**
     * Gets the view of the game.
     * 
     * @return The view.
     * @throws RemoteException
     *             Any remotely invoked method.
     * @throws NullPointerException
     *             When the player has no view open.
     */
    public Updatable<?> getUpdateable() throws RemoteException, NullPointerException;

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
     * @throws ClassCastException
     *             When the client has not that view open.
     * @throws NullPointerException
     *             When the player has no view open.
     */
    public default <T extends Updatable<?>> T getUpdateable(Class<T> type)
	    throws RemoteException, NullPointerException, ClassCastException {
	return (T) getUpdateable();
    }

    /**
     * Makes the items on the map whom are selectable, selectable.
     * 
     * @param selectables
     *            The items.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void setSelectable(SelectState selectables) throws RemoteException {
	getUpdateable(UpdateMap.class).setSelectable(selectables);
    }

    public Color getColor() throws RemoteException;

    /**
     * Gets the color of the player in the game.
     * 
     * @param color
     *            Color of the player.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setColor(Color color) throws RemoteException;

    /**
     * Adds a card to the player.
     * 
     * @param drawnCard
     *            The card that will be added to the player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void addCard(Card drawnCard) throws RemoteException;

    /**
     * True or false about whether the player uses a card.
     * 
     * @param usedCard
     *            The selected card.
     * @return True or false about whether the player uses a card.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public boolean useCard(Card usedCard) throws RemoteException;

    /**
     * Gets a list of all the cards from the player.
     * 
     * @return A list of cards from the player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public List<Card> getCards() throws RemoteException;

    /**
     * Gets a map with the resources and the amount of resources.
     * 
     * @return A map with the resources and the amount of resources.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public Map<Resource, Integer> getResources() throws RemoteException;

    /**
     * Gets the amount of a resource.
     * 
     * @param resource
     *            The chosen resource.
     * @return The amount of a resource.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default int getResourceAmount(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	return getResources().get(resource);
    }

    /**
     * Gives resources with the right amount.
     * 
     * @param resource
     *            The resource of choice.
     * @param amount
     *            The amount of the resource.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void giveResource(Resource resource, int amount) throws RemoteException {
	for (int i = 0; i < amount; i++) {
	    giveResource(resource);
	}
    }

    /**
     * Give the right resource to the player.
     * 
     * @param resource
     *            The resource.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void giveResource(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	getResources().put(resource, getResources().get(resource) + 1);
    }

    /**
     * Takes a resource.
     * 
     * @param resource
     *            The resource that will be taken.
     * @param amount
     *            The amount of resources that will be taken.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void takeResource(Resource resource, int amount) throws RemoteException {
	for (int i = 0; i < amount; i++) {
	    takeResource(resource);
	}
    }

    /**
     * Takes a resource.
     * 
     * @param resource
     *            The resource that will be taken.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default void takeResource(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	getResources().put(resource, Math.max(0, getResources().get(resource) - 1));
    }

    /**
     * True or false on if the player has the chosen resource.
     * 
     * @param resource
     *            The chosen resource.
     * @param amount
     *            The amount of the resource.
     * @return True or false on if the player has the chosen resource.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default boolean hasResource(Resource resource, int amount) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	return getResources().get(resource) >= amount;
    }

    /**
     * True or false on if the player has the chosen resource.
     * 
     * @param resource
     *            The chosen resource.
     * @return True or false on if the player has the chosen resource.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public default boolean hasResource(Resource resource) throws RemoteException {
	return hasResource(resource, 1);
    }

    /**
     * Gets the points of the player.
     * 
     * @return The points of the player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public int getPoints() throws RemoteException;

    /**
     * Sets the points of the player.
     * 
     * @param score
     *            The score of the player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setPoints(int score) throws RemoteException;

    /**
     * True or false on if the player has the most knights.
     * 
     * @return True or false on if the player has the most knights.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public boolean hasMostKnights() throws RemoteException;

    /**
     * Sets that the player has the most knights.
     * 
     * @param hasMost
     *            True or false on if the player has the most knights.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setMostRidder(boolean hasMost) throws RemoteException;

    /**
     * True or false on if the player has the longest road.
     * 
     * @return True or false on if the player has the longest road.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public boolean hasLongestRoad() throws RemoteException;

    /**
     * Sets that the player has the longest road.
     * 
     * @param hasLongest
     *            True or false on if the player has the longest road.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setHasLongestRoad(boolean hasLongest) throws RemoteException;

    /**
     * Sets the cards to use.
     * 
     * @param cards
     *            Cards that this player has.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setCards(List<Card> cards) throws RemoteException;

    /**
     * Sets all resources that this player has
     * 
     * @param resources
     *            The new mapping for the resources.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setResources(Map<Resource, Integer> resources) throws RemoteException;

    /**
     * Sets the amount that this player can build.
     * 
     * @param villages
     *            The amount of villages.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setVillagesToBuild(int villages) throws RemoteException;

    /**
     * Sets the amount that this player can build.
     * 
     * @param citys
     *            The amount of citys.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setCitysToBuild(int citys) throws RemoteException;

    /**
     * Sets the amount that this player can build.
     * 
     * @param streets
     *            The amount of streets.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setStreetsToBuild(int streets) throws RemoteException;
}
