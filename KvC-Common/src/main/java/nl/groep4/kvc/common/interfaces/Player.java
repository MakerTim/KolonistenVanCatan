package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;

/**
 * Stores username and colour
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Remote {

    public int getRemainingStreets() throws RemoteException;

    public void addRemainingStreets(int streets) throws RemoteException;

    public int getRemainingBuidlings() throws RemoteException;

    public void addRemainingBuidlings(int buildings) throws RemoteException;

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
     * @return Gets colour
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public default <T extends Updatable<?>> T getUpdateable(Class<T> type) throws RemoteException {
	return (T) getUpdateable();
    }

    public Color getColor() throws RemoteException;

    /**
     * 
     * @param color
     *            Refers to colour set by {@link Lobby}
     * @throws RemoteException
     *             in case connection between RMI and client is lost
     */
    public void setColor(Color color) throws RemoteException;

    public List<Card> getCards() throws RemoteException;

    public EnumMap<Resource, Integer> getResources() throws RemoteException;
}
