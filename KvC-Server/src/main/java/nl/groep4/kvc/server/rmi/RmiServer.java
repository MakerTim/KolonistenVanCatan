package nl.groep4.kvc.server.rmi;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.server.model.ServerLobby;

public class RmiServer {

    /**
     * Create RMI registry and place KolonistenVanCatan object
     * 
     * @param port
     *            Port which the server listens to
     * @author MakerTim
     */
    public static void init(int port) {
	try {
	    System.out.printf("Server ip: %s\n", InetAddress.getLocalHost());
	    System.out.printf("Server port: %d\n", 1099);
	    Remote lobbySkeleton = UnicastRemoteObject.exportObject(new ServerLobby(), KvCStatics.RMI_OBJ++);
	    Registry registry = LocateRegistry.createRegistry(1099);
	    registry.rebind(KvCStatics.LOBBY_KEY, lobbySkeleton);
	    System.out.println("Server online!");
	} catch (Exception ex) {
	    System.err.println(ex);
	}
    }

}
