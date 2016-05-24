package nl.groep4.kvc.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.Client;

public class KolonistenVanCatan implements nl.groep4.kvc.common.KolonistenVanCatan {

	private List<Client> clients = new ArrayList<>();

	@Override
	public void registerClient(Client client) throws RemoteException {
		clients.add(client);
		client.logFine("Registerd client");
	}

	@Override
	public void unregisterClient(Client client) throws RemoteException {
		clients.remove(client);
		client.logFine("Unregisterd client");
	}

}
