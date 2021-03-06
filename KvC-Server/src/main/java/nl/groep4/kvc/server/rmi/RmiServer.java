package nl.groep4.kvc.server.rmi;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.server.ServerStarter;
import nl.groep4.kvc.server.controller.ServerLobbyController;
import nl.groep4.kvc.server.model.ServerLobby;

/**
 * RMI Server class
 * 
 * @author Tim
 * @version 1.1
 */
public class RmiServer {

    /**
     * Create RMI registry and place KolonistenVanCatan object.
     * 
     * @param port
     *            Port which the server listens to.
     * @param host
     *            The host name where the server will register to.
     */
    public static void init(int port, String host) {
	try {
	    System.out.printf("Server ip: %s\n", host == null ? InetAddress.getLocalHost() : host);
	    System.out.printf("Server port: %d\n", port);
	    File rmilog = new File("rmilog.latest");
	    rmilog.delete();
	    RemoteServer.setLog(new FileOutputStream(rmilog));
	    ServerLobby lobby = new ServerLobby();
	    ServerLobbyController lobbyController = new ServerLobbyController(lobby);
	    lobby.setController(lobbyController);
	    ServerStarter.setLobby(lobby);
	    Remote lobbySkeleton = UnicastRemoteObject.exportObject(lobby, 0);
	    Registry registry = LocateRegistry.createRegistry(port);
	    registry.rebind(KvCStatics.LOBBY_KEY, lobbySkeleton);
	    System.out.println("Server online!");
	} catch (Exception ex) {
	    System.err.println(ex);
	    System.exit(1);
	}
    }

}
