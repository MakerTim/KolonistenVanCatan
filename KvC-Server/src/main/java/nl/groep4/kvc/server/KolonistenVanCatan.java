package nl.groep4.kvc.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.map.Map;

/**
 * Client registration and map retrieval
 * 
 * @version 1.0/
 * @author Tim
 *
 */
public class KolonistenVanCatan implements nl.groep4.kvc.common.KolonistenVanCatan {

    private List<Player> players = new ArrayList<>();

    @Override
    /**
     * @throws remoteException
     */
    public void registerClient(Player player) throws RemoteException {
	players.add(player);
    }

    @Override
    /**
     * @throws remoteException
     */
    public void unregisterClient(Player player) throws RemoteException {
	players.remove(player);
    }

    @Override
    /**
     * @return
     * @throws remoteException
     * 
     */
    public Map getMap() throws RemoteException {
	return null;
    }

}
