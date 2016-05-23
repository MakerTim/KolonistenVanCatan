package nl.groep4.kvk.server.rmi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer {

	/**
	 * Maak een RMI registery aan en plaats daarin een KolonistenVanCatan object
	 * 
	 * @param port
	 *            De port waar de server op luistert
	 * @author MakerTim
	 */
	public static void init(int port) {
		try {
			Remote kvcSkeleton = UnicastRemoteObject.exportObject(new nl.groep4.kvk.server.KolonistenVanCatan(), 0);
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind("kvc", kvcSkeleton);
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
