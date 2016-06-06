package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.common.interfaces.Player;

/**
 * Displays all client-side information
 * 
 * @author Tim
 * @version 1.0
 */
public final class ClientRefrence {

    private static Player thePlayer;
    private static int rmiport = 0;

    private ClientRefrence() {
    }

    /**
     * returns the player name
     * 
     * @return gets player
     */
    public static Player getThePlayer() {
	return thePlayer;
    }

    /**
     * sets the player
     * 
     * @param player
     *            references to ClientReference()
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
	return rmiport++;
    }
}
