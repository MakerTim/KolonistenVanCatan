package nl.groep4.kvc.client.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.controller.LoginController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.MenuCheckBox;
import nl.groep4.kvc.client.view.elements.MenuFilterdInputField;
import nl.groep4.kvc.client.view.elements.MenuMatchInputField;
import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * Builds the Scene for logging in to servers - mainscreen
 * 
 * @version 1.0
 * @author Tim
 **/
public class SceneLogin implements SceneHolder {

    private static Pane form;

    private TextField ipInput;
    private TextField portInput;
    private TextField usernameInput;
    private CheckBox nocolorInput;
    private CheckBox confirmInput;
    private CheckBox nosoundInput;

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new StackPane();

	/* Build the lobby */
	layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getMenuForeground(),
		SceneUtil.getLobbyBrazier(), buildFrom());
	SceneUtil.fadeIn(CollectionUtil.getItems(layers.getChildren(), 1, 3));
	return new Scene(layers);
    }

    private Node buildFrom() {
	if (form == null) {
	    form = new Pane();

	    Text ipLabel = new Text(330, 350, "Server IP");
	    ipInput = new MenuMatchInputField(450, 320, "", KvCStatics.REGEX_IP);
	    Text portLabel = new Text(330, 375, "Server port");
	    portInput = new MenuFilterdInputField(450, 345, "", KvCStatics.NUMERIC);
	    Text usernameLabel = new Text(330, 410, "Username");
	    usernameInput = new MenuFilterdInputField(450, 380, "", KvCStatics.USERNAME);
	    Text nocolorLabel = new Text(330, 450, "No color");
	    nocolorInput = new MenuCheckBox(540, 434, false);
	    Text confirmLabel = new Text(330, 470, "Confirm every action");
	    confirmInput = new MenuCheckBox(540, 454, false);
	    Text nosoundLabel = new Text(330, 490, "No sounds");
	    nosoundInput = new MenuCheckBox(540, 474, false);
	    MenuButton joinButton = new MenuButton(425, 500, "Join");
	    MenuButton settingsButton = new MenuButton(13, 645, "Settings");

	    ipLabel.setFont(ViewMaster.FONT);
	    ipInput.setFont(ViewMaster.FONT);
	    portLabel.setFont(ViewMaster.FONT);
	    portInput.setFont(ViewMaster.FONT);
	    portInput.setText("1099");
	    usernameLabel.setFont(ViewMaster.FONT);
	    usernameInput.setFont(ViewMaster.FONT);
	    nocolorLabel.setFont(ViewMaster.FONT);
	    confirmLabel.setFont(ViewMaster.FONT);
	    nosoundLabel.setFont(ViewMaster.FONT);
	    joinButton.setFont(ViewMaster.FONT);
	    joinButton.registerClick(() -> onConnectClick());
	    settingsButton.setFont(ViewMaster.FONT);
	    settingsButton.registerClick(() -> onSettingsClick());

	    form.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, usernameLabel, usernameInput,
		    nocolorLabel, nocolorInput, confirmLabel, confirmInput, nosoundLabel, nosoundInput, joinButton,
		    settingsButton);
	}
	return form;
    }

    public void onConnectClick() {
	Lobby lobby = LoginController.connect(this);
	if (lobby != null) {
	    LobbyController lobbyController = new LobbyController(lobby);
	    SceneLobby scene = new SceneLobby();
	    ViewMaster.setScene(scene.getScene());
	    scene.register(lobbyController);
	    scene.update();
	}
    }

    public void onSettingsClick() {
	ViewMaster.setScene(new SceneSettings().getScene());
    }

    public String getIpInput() {
	return ipInput.getText();
    }

    public int getPortInput() {
	return Integer.parseInt(portInput.getText());
    }

    public String getUsernameInput() {
	return usernameInput.getText();
    }

    public boolean getNocolorInput() {
	return nocolorInput.isSelected();
    }

    public boolean getNosoundInput() {
	return nosoundInput.isSelected();
    }

    public boolean getConfirmInput() {
	return confirmInput.isSelected();
    }

}
