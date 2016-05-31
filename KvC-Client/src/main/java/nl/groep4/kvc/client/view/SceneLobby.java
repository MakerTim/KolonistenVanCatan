package nl.groep4.kvc.client.view;

import javafx.scene.Scene;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.controller.PlayerController;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.MenuButton;

/**
 * Builds scene settings menu
 * 
 * @version 1.0
 * @author Luc
 */

public class SceneLobby implements SceneHolder {
    GridPane lobbyGrid = new GridPane();

    private LobbyController lobby;

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane lobbyPane = new Pane();

	/* Build the settings menu in lobby */
	Text lobbyLabel = new Text(838, 200, "Lobby");
	lobbyLabel.setFont(ViewMaster.FONT);
	lobbyLabel.setFill(Color.WHITE);
	lobbyLabel.setStroke(Color.BLACK);
	MenuButton startGame = new MenuButton(415, 550, "Start Game");
	startGame.setFont(ViewMaster.FONT);
	MenuButton backButton = new MenuButton(215, 550, "Back");
	backButton.setFont(ViewMaster.FONT);
	MenuButton saveButton = new MenuButton(615, 550, "Load");
	saveButton.setFont(ViewMaster.FONT);

	startGame.registerClick(() -> {
	    SoundUtil.stopThemesong();
	});

	backButton.registerClick(() -> {
	    ViewMaster.setScene(new SceneLogin().getScene());
	    lobby.discontect(PlayerController.getThePlayer());
	});

	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getLobbyBrazier(), SceneUtil.getCornerShield(), lobbyLabel, startGame, backButton,
		saveButton);

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), SceneUtil.getLobbyBrazier(), SceneUtil.getCornerShield(),
		lobbyLabel, startGame, backButton, saveButton);

	return scene;

	// TODO: SoundUtil.stopTeamsong(); wanneer spel start

    }

    public void update() {
	System.out.println(lobby.getPlayers().size());
	// TODO: Everytime there is a update - then this method gets called
    }

    public void register(LobbyController lobbyController) {
	this.lobby = lobbyController;
    }

}
