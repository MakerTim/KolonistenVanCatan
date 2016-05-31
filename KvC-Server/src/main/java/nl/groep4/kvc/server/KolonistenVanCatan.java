package nl.groep4.kvc.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.Client;
import nl.groep4.kvc.common.map.Map;

/**
 * Client registration and map retrieval
 * 
 * @version 1.0/
 * @author Tim
 *
 */
public class KolonistenVanCatan implements nl.groep4.kvc.common.KolonistenVanCatan {

    private List<Client> clients = new ArrayList<>();

    @Override
    /**
     * @throws remoteException
     *             In case connection between RMI and client in compromised
     */
    public void registerClient(Client client) throws RemoteException {
	clients.add(client);
	client.logFine("Registerd client");
    }

    @Override
    /**
     * @throws remoteException
     *             In case connection between RMI and client in compromised
     */
    public void unregisterClient(Client client) throws RemoteException {
	clients.remove(client);
	client.logFine("Unregisterd client");
    }

    @Override
    /**
     * @return Retrieves map
     * @throws remoteException
     *             In case connection between RMI and client in compromised
     * 
     */
    public Map getMap() throws RemoteException {
	return null;
    }

}
