package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * Displays all client-side information
 * 
 * @author Tim
 * @version 1.0
 */
public final class ClientRefrence {

    private static Player thePlayer;

    private ClientRefrence() {
    }

    /**
     * Gets the player name
     * 
     * @return the player
     */
    public static Player getThePlayer() {
	return thePlayer;
    }

    /**
     * sets the player
     * 
     * @param player
     *            Refers to the player in this class
     */
    public static void setThePlayer(Player player) {
	ClientRefrence.thePlayer = player;
    }

    /**
     * returns the port for RMI to connect with
     * 
     * @return the port for RMI
     */
    public static int getPort() {
	return 100 + new Random().nextInt(10000);
    }

    public static void registerUpdateable(Updatable<?> updatable) {
	try {
	    getThePlayer().registerUpdateable(
		    (Updatable<Lobby>) UnicastRemoteObject.exportObject(updatable, ClientRefrence.getPort()));
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }
}
