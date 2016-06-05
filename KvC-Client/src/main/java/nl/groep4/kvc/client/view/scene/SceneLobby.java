package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ConnectionController;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.LobbyPlayer;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdatableLobby;

/**
 * Builds scene settings menu
 * 
 * @version 1.0
 * @author Luc
 */
public class SceneLobby implements SceneHolder, UpdatableLobby {

    private MenuButton saveButton;
    private MenuButton backButton;
    private MenuButton startGame;
    private Text lobbyLabel;

    private LobbyPlayer[] scrolls = new LobbyPlayer[Color.values().length];
    private LobbyController lobby;

    public SceneLobby(LobbyController lobbyController) throws RemoteException {
	lobby = lobbyController;
    }

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane lobbyPane = new Pane();
	Pane lobbyGrid = new Pane();
	lobbyGrid.setLayoutX(200);
	lobbyGrid.setLayoutY(200);
	/* Build the settings menu in lobby */
	lobbyLabel = new Text(873, 150, TranslationManager.translate("lobby.shield.title"));
	lobbyLabel.setFont(ViewMaster.FONT);
	lobbyLabel.setFill(javafx.scene.paint.Color.WHITE);
	lobbyLabel.setStroke(javafx.scene.paint.Color.BLACK);
	startGame = new MenuButton(415, 550, TranslationManager.translate("lobby.button.start"));
	startGame.setFont(ViewMaster.FONT);
	backButton = new MenuButton(215, 550, TranslationManager.translate("lobby.button.back"));
	backButton.setFont(ViewMaster.FONT);
	saveButton = new MenuButton(615, 550, TranslationManager.translate("lobby.button.loadsave"));
	saveButton.setFont(ViewMaster.FONT);

	startGame.registerClick(() -> {
	    SoundUtil.stopThemesong();
	    lobby.startGame();
	});

	backButton.registerClick(() -> {
	    ViewMaster.setScene(new SceneLogin());
	    lobby.disconnect(ConnectionController.getThePlayer());
	});

	for (int i = 0; i < Color.values().length; i++) {
	    LobbyPlayer lobbyPlayer = new LobbyPlayer(Color.values()[i]);
	    lobbyPlayer.setLayoutX(i % 3 * 215);
	    lobbyPlayer.setLayoutY((i / 3) * 150);
	    lobbyGrid.getChildren().add(lobbyPlayer);
	    lobbyPlayer.registerClick(() -> {
		lobby.changeColor(ConnectionController.getThePlayer(), lobbyPlayer.getColor());
	    });
	    scrolls[i] = lobbyPlayer;
	}

	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getMenuBrazier(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame, backButton,
		saveButton);

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame,
		backButton, saveButton);
	return scene;
    }

    @Override
    public void setModel(Lobby lobby) throws RemoteException {
	List<Player> players = lobby.getConnectedPlayers();
	for (LobbyPlayer scroll : scrolls) {
	    players.stream().filter(pl -> pl.getColor() == scroll.getColor())
		    .forEach(player -> scroll.updatePlayer(player));
	}
    }

    @Override
    public void close(String key) throws RemoteException {
	lobby.registerScene(null);
	ExceptionDialog.warning("kicked." + key);
	ViewMaster.setScene(new SceneLogin());
    }

    @Override
    public void start() throws RemoteException {
	ViewMaster.setScene(new SceneMap());
    }

    @Override
    public void updateColor(Player player, Color color) throws RemoteException {
	Arrays.stream(scrolls).filter(scroll -> scroll.getColor() == color)
		.forEach(scroll -> scroll.updatePlayer(player));
    }

    @Override
    public void updateConfig() {
	lobbyLabel.setText("lobby.shield.title");
	startGame.updateText("lobby.button.start");
	backButton.updateText("lobby.button.back");
	saveButton.updateText("lobby.button.loadsave");
    }
}
