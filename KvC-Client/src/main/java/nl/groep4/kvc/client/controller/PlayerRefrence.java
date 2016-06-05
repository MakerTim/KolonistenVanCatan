package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.common.interfaces.Player;

/**
 * Displays all client-side information
 * 
 * @author Tim
 * @version 1.0
 */
public final class PlayerRefrence {

    private static Player thePlayer;

    private PlayerRefrence() {
    }

    /**
     * 
     * @return gets player
     */
    public static Player getThePlayer() {
	return thePlayer;
    }

    /**
     * 
     * @param username
     */
    public static void setThePlayer(Player player) {
	PlayerRefrence.thePlayer = player;
    }
}
