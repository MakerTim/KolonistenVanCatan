package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.EnumMap;

import nl.groep4.kvc.common.enumeration.Resource;

/**
 * Gets the updates of the costs.
 * 
 * @author Tim
 * @version 1.1
 */
public interface UpdateCosts {

    /**
     * Updates the costs for the streets.
     * 
     * @param resources
     *            The costs of a street are the resources and the amount of it.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

    /**
     * Updates the costs of the villages.
     * 
     * @param resources
     *            The costs of a village are the resources and the amount of it.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

    /**
     * Updates the costs of the cities.
     * 
     * @param resources
     *            The costs of a city are the resources and the amount of it.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

    /**
     * Updates the costs of the cards.
     * 
     * @param resources
     *            The costs of a card are the resources and the amount of it.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateCardCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

}
