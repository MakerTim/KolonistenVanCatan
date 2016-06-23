package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Updates trades.
 * 
 * @author Tim
 * @version 1.0
 */
public interface UpdateTrade {

    /**
     * Updates all trades.
     * 
     * @param allTrades
     *            List of trades.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateTrades(List<Trade> allTrades) throws RemoteException;

}
