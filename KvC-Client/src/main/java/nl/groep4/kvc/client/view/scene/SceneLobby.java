package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.PlayerColorScroll;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateLobby;

/**
 * Builds scene settings menu
 * 
 * @version 1.2
 * @author Luc
 * @author Tim
 */
public class SceneLobby implements SceneHolder, UpdateLobby {

    private MenuButton saveButton;
    private MenuButton backButton;
    private MenuButton startGame;
    private Text lobbyLabel;
    private PlayerColorScroll[] scrolls;

    private LobbyController controller;

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
	    controller.startGame();
	});

	backButton.registerClick(() -> {
	    ViewMaster.setScene(new SceneLogin());
	    controller.disconnect(ClientRefrence.getThePlayer());
	    ClientRefrence.setThePlayer(null);
	});

	scrolls = new PlayerColorScroll[Color.values().length];
	for (int i = 0; i < Color.values().length; i++) {
	    PlayerColorScroll scroll = new PlayerColorScroll(Color.values()[i]);
	    scroll.setLayoutX(i % 3 * 215);
	    scroll.setLayoutY((i / 3) * 150);
	    lobbyGrid.getChildren().add(scroll);
	    scroll.registerClick(() -> {
		controller.changeColor(ClientRefrence.getThePlayer(), scroll.getColor());
	    });
	    scrolls[i] = scroll;
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
    public void updateConfig() {
	lobbyLabel = new Text(873, 150, TranslationManager.translate("lobby.shield.title"));
	startGame = new MenuButton(415, 550, TranslationManager.translate("lobby.button.start"));
	backButton = new MenuButton(215, 550, TranslationManager.translate("lobby.button.back"));
	saveButton = new MenuButton(615, 550, TranslationManager.translate("lobby.button.loadsave"));
	Arrays.stream(scrolls).forEach(scroll -> scroll.updateTranslation());
    }

    /**
     * sets controller
     * 
     * @param controller
     *            references to openLobby()
     */
    public void registerController(LobbyController controller) {
	this.controller = controller;
    }

    @Override
    public void setModel(Lobby model) throws RemoteException {
	for (Player pl : model.getPlayers()) {
	    updatePlayerColor(pl, pl.getColor());
	}
    }

    @Override
    public void close(String key) throws RemoteException {
	ViewMaster.setScene(new SceneLogin());
    }

    @Override
    public void updatePlayerColor(Player pl, Color newColor) throws RemoteException {
	Arrays.stream(scrolls).filter(scroll -> scroll.getColor() == newColor)
		.forEach(scroll -> scroll.updatePlayer(pl));
    }

    @Override
    public void kick(String reason) throws RemoteException {
	ExceptionDialog.warning("kicked." + reason);
	ViewMaster.setScene(new SceneLogin());
    }

    @Override
    public void test() throws RemoteException {
    }

}
