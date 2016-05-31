package nl.groep4.kvc.client.view;

import javafx.scene.Scene;
import nl.groep4.kvc.client.controller.LobbyController;

/**
 * 
 * 
 * @version 1.0
 * @author Tim
 */

public class SceneLobby implements SceneHolder {

    private LobbyController lobby;

    @Override
    public Scene getScene() {
	// TODO: SoundUtil.stopTeamsong();
	return null;
    }

    public void update() {
	// TODO: Everytime there is a update - then this method gets called
    }

    public void register(LobbyController lobbyController) {
	this.lobby = lobbyController;
    }

}
