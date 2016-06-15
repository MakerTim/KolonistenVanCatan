package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.controller.Controller;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.ColorScroll;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.SettingsButton;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateLobby;
import nl.groep4.kvc.common.util.KvCUtil;
import nl.groep4.kvc.common.util.Scheduler;

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
    private ColorScroll[] scrolls;
    private Lobby model;

    private LobbyController controller;

    @Override
    public void registerController(Controller controller) {
	this.controller = (LobbyController) controller;
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
	    controller.startGame();
	});

	backButton.registerClick(() -> {
	    controller.disconnect(ClientRefrence.getThePlayer());
	});

	scrolls = new ColorScroll[Color.values().length];
	for (int i = 0; i < Color.values().length; i++) {
	    ColorScroll scroll = new ColorScroll(Color.values()[i]);
	    scroll.setLayoutX(i % 3 * 215);
	    scroll.setLayoutY((i / 3) * 150);
	    lobbyGrid.getChildren().add(scroll);
	    scroll.registerClick(() -> {
		controller.changeColor(ClientRefrence.getThePlayer(), scroll.getColor());
	    });
	    scrolls[i] = scroll;
	}

	Scheduler.runAsyncLater(() -> {
	    do {
		for (ColorScroll scroll : scrolls) {
		    scroll.setPing(KvCUtil.ping(scroll.getPlayer()));
		}
		try {
		    Thread.sleep(1000L);
		} catch (Exception ex) {
		}
	    } while (ViewMaster.getLastScene() == this);
	}, 1000);
	lobbyPane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getMenuBrazier(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame, backButton,
		saveButton, SettingsButton.getButton(this, 13, 645));

	Scene scene = new Scene(lobbyPane);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), SceneUtil.getCornerShield(), lobbyLabel, lobbyGrid, startGame,
		backButton, saveButton);
	if (model != null) {
	    try {
		setModel(model);
	    } catch (RemoteException e) {
		e.printStackTrace();
	    }
	}
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

    @Override
    public void setModel(Lobby model) throws RemoteException {
	this.model = model;
	for (Player pl : model.getPlayers()) {
	    updatePlayerColor(pl, pl.getColor());
	}
    }

    @Override
    public void updatePlayerColor(Player pl, Color newColor) throws RemoteException {
	if (scrolls == null) {
	    return;
	}
	Arrays.stream(scrolls).filter(scroll -> scroll.getColor() == newColor)
		.forEach(scroll -> scroll.updatePlayer(pl));
    }

    @Override
    public void close(String reason) throws RemoteException {
	ExceptionDialog.warning("kicked." + reason);
	ViewMaster.setScene(new SceneLogin());
    }

    @Override
    public void popup(String key) throws RemoteException {
	ExceptionDialog.warning("note." + key);
    }

    @Override
    public void start() throws RemoteException {
	controller.start();
    }
}
