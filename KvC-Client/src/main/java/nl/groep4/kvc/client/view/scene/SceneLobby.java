package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.PlayerColorScroll;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * Builds scene settings menu
 * 
 * @version 1.0
 * @author Luc
 */
public class SceneLobby implements SceneHolder, Updatable<Lobby> {

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

	});

	backButton.registerClick(() -> {
	    ViewMaster.setScene(new SceneLogin());

	});

	scrolls = new PlayerColorScroll[Color.values().length];
	for (int i = 0; i < Color.values().length; i++) {
	    PlayerColorScroll scroll = new PlayerColorScroll(Color.values()[i]);
	    scroll.setLayoutX(i % 3 * 215);
	    scroll.setLayoutY((i / 3) * 150);
	    lobbyGrid.getChildren().add(scroll);
	    scroll.registerClick(() -> {

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

    public void registerController(LobbyController controller) {
	this.controller = controller;
    }

    @Override
    public void setModel(Lobby model) throws RemoteException {
	System.out.printf("setup model %s\n", model);
    }

    @Override
    public void close(String key) throws RemoteException {
	ViewMaster.setScene(new SceneLogin());
    }

}
