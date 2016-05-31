package nl.groep4.kvc.client.view;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;
import nl.groep4.kvc.common.Lobby;

/**
 * 
 * 
 * @version 1.0
 * @author Luc
 */

public class SceneLobby implements SceneHolder {
    GridPane lobbyGrid = new GridPane();

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
	LobbyButton backButton = new LobbyButton(215, 550, "Back");
	backButton.setFont(ViewMaster.FONT);
	LobbyButton saveButton = new LobbyButton(615, 550, "Use Save");
	saveButton.setFont(ViewMaster.FONT);

	startGame.registerClick(() -> {
	    SoundUtil.stopThemesong();
	});

	backButton.registerClick(() -> {
	    ViewMaster.setScene(new SceneLogin().getScene());
	    // Something to disconnect
	});

	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getLobbyBrazier(), SceneUtil.getCornerShield(), lobby, startGame, backButton, saveButton);

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), SceneUtil.getLobbyBrazier(), SceneUtil.getCornerShield(),
		lobby, startGame, backButton, saveButton);

	return scene;

	// TODO: SoundUtil.stopTeamsong(); wanneer spel start

    }

    public void update(Lobby lobby) {
	// TODO: Everytime there is a update - then this method gets called
    }

}
