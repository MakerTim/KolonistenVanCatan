package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.common.Lobby;

public class LobbyController {

    public Lobby lobby;

    public LobbyController(Lobby lobby) {
	this.lobby = lobby;
    }

    public Lobby getLobby() {
	return this.lobby;
    }

}
