package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Stores username and color
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Remote {

    public default boolean hasConnectionErrors() throws RemoteException {
	return getUpdateable() == null;
    }

    /**
     * 
     * @return gets the name of a player
     */
    public String getUsername() throws RemoteException;

    /**
     * 
     * @param updatable
     *            reference to update the server lobby
     */
    public void registerUpdateable(Updatable<?> updatable) throws RemoteException;

    /**
     * 
     * @return gets update for the server lobby
     */
    public Updatable<?> getUpdateable() throws RemoteException;

    public default <T extends Updatable<?>> T getUpdateable(Class<T> type) throws RemoteException {
	return (T) getUpdateable();
    }

    public Color getColor() throws RemoteException;

    public void setColor(Color color) throws RemoteException;
}
