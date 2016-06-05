package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.common.interfaces.Player;

public class PlayerRefrence {

    private static Player player;

    public static Player getPlayer() {
	return player;
    }

    public static void setPlayer(Player player) {
	PlayerRefrence.player = player;
    }
}
