package nl.groep4.kvc.client.view;

import java.util.List;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.controller.PlayerController;
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
	Pane lobbyGrid = new Pane();
	lobbyGrid.setLayoutX(200);
	lobbyGrid.setLayoutY(200);
	/* Build the settings menu in lobby */
	Text lobbyLabel = new Text(873, 150, "Lobby");
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
	    lobby.disconnect(PlayerController.getThePlayer());
	});

	for (int i = 0; i < Color.values().length; i++) {
	    LobbyPlayer lobbyPlayer = new LobbyPlayer(Color.values()[i]);
	    lobbyPlayer.setLayoutX(i % 3 * 215);
	    lobbyPlayer.setLayoutY((i / 3) * 150);
	    lobbyGrid.getChildren().add(lobbyPlayer);
	    lobbyPlayer.registerClick(() -> {
		lobby.changeColor(PlayerController.getThePlayer(), lobbyPlayer.getColor());
	    });
	    players[i] = lobbyPlayer;
	}

	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getMenuBrazier(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame, backButton,
		saveButton);

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame,
		backButton, saveButton);

	return scene;

    }

    public void update() {
	for (LobbyPlayer lp : players) {
	    List<Player> players = lobby.getPlayers();
	    Optional<Player> player = players.stream().filter(pl -> pl.getColor() == lp.getColor()).findAny();
	    if (player.isPresent()) {
		lp.updatePlayer(player.get());
	    } else {
		lp.updatePlayer(null);
	    }
	}
    }

    public void register(LobbyController lobbyController) {
	this.lobby = lobbyController;
	this.lobby.registerScene(this);
    }

}
