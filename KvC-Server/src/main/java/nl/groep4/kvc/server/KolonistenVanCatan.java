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
     * 
     */
    public void registerClient(Player client) throws RemoteException {
	players.add(client);
    }

    @Override
    public void unregisterClient(Player client) throws RemoteException {
	players.remove(client);
    }

    @Override
    public Map getMap() throws RemoteException {
	return null;
    }

}
