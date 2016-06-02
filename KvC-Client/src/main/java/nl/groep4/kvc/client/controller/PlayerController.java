package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.common.Player;

public class PlayerController {

    private static Player thePlayer;

    public static Player getThePlayer() {
	return thePlayer;
    }

    public static Player setThePlayer(Player thePlayer) {
	return PlayerController.thePlayer = thePlayer;
    }

}
