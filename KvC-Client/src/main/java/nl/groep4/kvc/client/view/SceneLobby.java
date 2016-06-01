package nl.groep4.kvc.client.view;

import javafx.scene.Scene;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.controller.PlayerController;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.LobbyPlayer;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

/**
 * Builds scene settings menu
 * 
 * @version 1.0
 * @author Luc
 */

public class SceneLobby implements SceneHolder {

    private LobbyController lobby;

    private LobbyPlayer[] players = new LobbyPlayer[Color.values().length];

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane lobbyPane = new Pane();
	GridPane lobbyGrid = new GridPane();
	lobbyGrid.setLayoutX(200);
	lobbyGrid.setLayoutY(200);
	/* Build the settings menu in lobby */
	Text lobbyLabel = new Text(838, 200, "Lobby");
	lobbyLabel.setFont(ViewMaster.FONT);
	lobbyLabel.setFill(javafx.scene.paint.Color.WHITE);
	lobbyLabel.setStroke(javafx.scene.paint.Color.BLACK);
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

	for (int i = 0; i < Color.values().length; i++) {
	    players[i] = new LobbyPlayer(Color.values()[i]);
	    lobbyGrid.getChildren().add(players[i]);
	}

	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getMenuBrazier(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame, backButton,
		saveButton);

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), SceneUtil.getMenuBrazier(), SceneUtil.getCornerShield(),
		lobbyLabel, lobbyGrid, startGame, backButton, saveButton);

	return scene;

	// TODO: SoundUtil.stopTeamsong(); wanneer spel start

    }

    public void update() {
	for (Player pl : lobby.getPlayers()) {
	    for (LobbyPlayer lp : players) {
		if (pl.getColor() == lp.getColor()) {
		    lp.updatePlayer(pl);
		}
	    }
	}
    }

    public void register(LobbyController lobbyController) {
	this.lobby = lobbyController;
    }

}
