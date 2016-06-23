package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Updates in what order the players are displayed.
 * 
 * @author Tim
 * @version 1.1
 */
public interface UpdatePlayerOrder {

    /**
     * Updates the player list.
     * 
     * @param order
     *            The sequence players are in the list.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updatePlayerOrder(List<Player> order) throws RemoteException;

}
