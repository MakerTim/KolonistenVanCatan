package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Resource;

/**
 * Updates stock with all resources.
 * 
 * @author Tim
 * @version 1.0
 */
public interface UpdateStock {

    /**
     * Updates resources of the selected player.
     * 
     * @param pl
     *            The player
     * @param resources
     *            Resources which the player haves.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateStock(Player pl, EnumMap<Resource, Integer> resources) throws RemoteException;

    /**
     * Updates development cards of the selected player.
     * 
     * @param pl
     *            The player
     * @param cards
     *            List of development cards.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateStock(Player pl, List<Card> cards) throws RemoteException;
}
