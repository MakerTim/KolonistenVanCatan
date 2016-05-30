package nl.groep4.kvc.server.rmi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import nl.groep4.kvc.common.KvCStaticNaming;
import nl.groep4.kvc.server.model.ServerLobby;

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
	    Remote lobbySkeleton = UnicastRemoteObject.exportObject(new ServerLobby(), 0);
	    Registry registry = LocateRegistry.createRegistry(port);
	    registry.rebind(KvCStaticNaming.LOBBY_KEY, lobbySkeleton);
	} catch (Exception ex) {
	    System.err.println(ex);
	}
    }

}
