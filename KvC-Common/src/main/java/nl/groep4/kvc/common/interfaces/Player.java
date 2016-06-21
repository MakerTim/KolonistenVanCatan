package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;

/**
 * Stores username and color
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Remote {

    public int getRemainingStreets() throws RemoteException;

    public void addRemainingStreets(int streets) throws RemoteException;

    public default void removeRemainingStreet() throws RemoteException {
	addRemainingStreets(-1);
    }

    public default boolean hasRemainingStreets() throws RemoteException {
	return getRemainingStreets() > 0;
    }

    public int getRemainingVillages() throws RemoteException;

    public void addRemainingVillages(int villages) throws RemoteException;

    public default void removeRemainingVillage() throws RemoteException {
	addRemainingVillages(-1);
    }

    public default boolean hasRemainingVillages() throws RemoteException {
	return getRemainingVillages() > 0;
    }

    public int getRemainingCitys() throws RemoteException;

    public void addRemainingCitys(int citys) throws RemoteException;

    public default void removeRemainingCity() throws RemoteException {
	addRemainingCitys(-1);
    }

    public default boolean hasRemainingCitys() throws RemoteException {
	return getRemainingCitys() > 0;
    }

    /**
     * 
     * @return get updates from {@link Lobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public default boolean hasConnectionErrors() throws RemoteException {
	return getUpdateable() == null;
    }

    /**
     * 
     * @return gets the name of a player
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public String getUsername() throws RemoteException;

    /**
     * 
     * @param updatable
     *            reference to update the {@link Lobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void registerUpdateable(Updatable<?> updatable) throws RemoteException;

    /**
     * 
     * @return Gets update for the {@link Lobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public Updatable<?> getUpdateable() throws RemoteException;

    /**
     * 
     * @return Gets color
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public default <T extends Updatable<?>> T getUpdateable(Class<T> type) throws RemoteException {
	return (T) getUpdateable();
    }

    public default void setSelectable(SelectState selectables) throws RemoteException {
	getUpdateable(UpdateMap.class).setSelectable(selectables);
    }

    public Color getColor() throws RemoteException;

    /**
     * 
     * @param color
     *            Refers to color set by {@link Lobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void setColor(Color color) throws RemoteException;

    public void addCard(Card drawnCard) throws RemoteException;

    public void useCard(Card usedCard) throws RemoteException;

    public List<Card> getCards() throws RemoteException;

    public EnumMap<Resource, Integer> getResources() throws RemoteException;

    public default int getResourceAmount(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	return getResources().get(resource);
    }

    public default void giveResource(Resource resource, int amount) throws RemoteException {
	for (int i = 0; i < amount; i++) {
	    giveResource(resource);
	}
    }

    public default void giveResource(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	getResources().put(resource, getResources().get(resource) + 1);
    }

    public default void takeResource(Resource resource, int amount) throws RemoteException {
	for (int i = 0; i < amount; i++) {
	    takeResource(resource);
	}
    }

    public default void takeResource(Resource resource) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	getResources().put(resource, Math.max(0, getResources().get(resource) - 1));
    }

    public default boolean hasResource(Resource resource, int amount) throws RemoteException {
	if (!getResources().containsKey(resource)) {
	    getResources().put(resource, 0);
	}
	return getResources().get(resource) >= amount;
    }

    public default boolean hasResource(Resource resource) throws RemoteException {
	return hasResource(resource, 1);
    }

}
