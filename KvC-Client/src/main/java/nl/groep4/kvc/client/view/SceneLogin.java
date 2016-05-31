package nl.groep4.kvc.client.view;

import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;
import nl.groep4.kvc.client.view.elements.LobbyCheckBox;
import nl.groep4.kvc.client.view.elements.LobbyFilterdInputField;
import nl.groep4.kvc.client.view.elements.LobbyMatchInputField;
import nl.groep4.kvc.common.KvCStatics;

/**
 * Builds the Scene for logging in to servers - mainscreen
 * 
 * @version 1.0
 * @author Tim
 **/
public class SceneLogin implements SceneHolder {

    private Stage parent;

    private TextField ipInput;
    private TextField portInput;
    private TextField usernameInput;
    private CheckBox nocolorInput;
    private CheckBox confirmInput;
    private CheckBox nosoundInput;

    public SceneLogin(Stage stage) {
	this.parent = stage;
    }

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new StackPane();

	/* Build the lobby */
	layers.getChildren().addAll(SceneUtil.getLobbbyBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getLobbyBrazier(), buildFrom());
	Scene scene = new Scene(layers, 1000, 700);
	scene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
	return scene;
    }

    private Node buildFrom() {
	Pane theGrid = new Pane();

	Text ipLabel = new Text(330, 350, "Server IP");
	ipInput = new LobbyMatchInputField(450, 320, "", KvCStatics.REGEX_IP);
	Text portLabel = new Text(330, 375, "Server port");
	portInput = new LobbyFilterdInputField(450, 345, "", KvCStatics.NUMERIC);
	Text usernameLabel = new Text(330, 410, "Username");
	usernameInput = new LobbyFilterdInputField(450, 380, "", KvCStatics.USERNAME);
	Text nocolorLabel = new Text(330, 450, "No color");
	nocolorInput = new LobbyCheckBox(540, 434, false);
	Text confirmLabel = new Text(330, 470, "Confirm every action");
	confirmInput = new LobbyCheckBox(540, 454, false);
	Text nosoundLabel = new Text(330, 490, "No sounds");
	nosoundInput = new LobbyCheckBox(540, 474, false);
	LobbyButton joinButton = new LobbyButton(425, 500, "Join");
	LobbyButton settingsButton = new LobbyButton(13, 650, "Settings");

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

	theGrid.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, usernameLabel, usernameInput, nocolorLabel,
		nocolorInput, confirmLabel, confirmInput, nosoundLabel, nosoundInput, joinButton, settingsButton);
	return theGrid;
    }

    public void onConnectClick() {
	if (LobbyController.connect(this)) {
	    parent.setScene(new SceneLobby().getScene());
	}
    }

    public void onSettingsClick() {
	parent.setScene(new SceneSettings().getScene());
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
