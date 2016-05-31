package nl.groep4.kvc.client.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;
import nl.groep4.kvc.common.Lobby;

/**
 * 
 * 
 * @version 1.0
 * @author Luc
 */

public class SceneLobby implements SceneHolder {

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane lobbyPane = new Pane();

	/* Build the settings menu in lobby */
	Text lobby = new Text(838, 200, "Lobby");
	lobby.setFont(ViewMaster.FONT);
	lobby.setFill(Color.WHITE);
	lobby.setStroke(Color.BLACK);
	LobbyButton startGame = new LobbyButton(415, 550, "Start Game");
	startGame.setFont(ViewMaster.FONT);

	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getLobbyBrazier(), SceneUtil.getCornerShield(), lobby, startGame);

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbySettings());

	return scene;

	// TODO: SoundUtil.stopTeamsong(); wanneer spel start

    }

    public void update(Lobby lobby) {
	// TODO: Everytime there is a update - then this method gets called
    }

}
