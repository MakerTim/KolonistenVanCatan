package nl.groep4.kvc.server.rmi;

import java.net.InetAddress;
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
	    System.out.printf("Server ip: %s\n", InetAddress.getLocalHost());
	    System.out.printf("Server port: %d\n", port);
	    Remote lobbySkeleton = UnicastRemoteObject.exportObject(new ServerLobby(), 0);
	    Registry registry = LocateRegistry.createRegistry(port);
	    registry.rebind(KvCStaticNaming.LOBBY_KEY, lobbySkeleton);
	    System.out.println("Server online!");
	} catch (Exception ex) {
	    System.err.println(ex);
	}
    }

}
