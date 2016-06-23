package nl.groep4.kvc.client.view.scene;

import java.net.InetAddress;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.controller.Controller;
import nl.groep4.kvc.client.controller.LoginController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.MenuCheckBox;
import nl.groep4.kvc.client.view.elements.MenuFilterdInputField;
import nl.groep4.kvc.client.view.elements.MenuMatchInputField;
import nl.groep4.kvc.client.view.elements.SettingsButton;
import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * Builds the Scene for logging in to Servers - main screen.
 * 
 * @author Tim
 * @version 1.1
 **/
public class SceneLogin implements SceneHolder {

    private static Pane form;

    private Text ipLabel;
    private Text portLabel;
    private Text usernameLabel;
    private Text confirmLabel;
    private Text nosoundLabel;
    private MenuButton joinButton;
    private TextField ipInput;
    private TextField portInput;
    private TextField usernameInput;
    private CheckBox confirmInput;
    private CheckBox nosoundInput;

    private LoginController controller;

    @Override
    public void registerController(Controller controller) {
	this.controller = (LoginController) controller;
    }

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new StackPane();

	/* Build the lobby */
	try {
	    layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLoginForeground(),
		    SceneUtil.getMenuBrazier(), buildFrom());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	SceneUtil.fadeIn(CollectionUtil.getItems(layers.getChildren(), 1, 3));
	return new Scene(layers);
    }

    private Node buildFrom() throws Exception {
	if (form == null) {
	    form = new Pane();

	    ipLabel = new Text(310, 350, TranslationManager.translate("lobby.label.ip"));
	    ipInput = new MenuMatchInputField(460, 320, InetAddress.getLocalHost().getHostAddress(),
		    KvCStatics.REGEX_IP);
	    portLabel = new Text(310, 375, TranslationManager.translate("lobby.label.port"));
	    portInput = new MenuFilterdInputField(460, 345, "", KvCStatics.NUMERIC);
	    usernameLabel = new Text(310, 410, TranslationManager.translate("lobby.label.username"));
	    usernameInput = new MenuFilterdInputField(460, 380, "", KvCStatics.USERNAME);
	    confirmLabel = new Text(310, 470, TranslationManager.translate("lobby.label.confirmeverything"));
	    confirmInput = new MenuCheckBox(540, 454, false);
	    nosoundLabel = new Text(310, 490, TranslationManager.translate("lobby.label.nosound"));
	    nosoundInput = new MenuCheckBox(540, 474, false);
	    joinButton = new MenuButton(425, 500, TranslationManager.translate("lobby.button.join"));

	    ipLabel.setFont(ViewMaster.FONT);
	    ipInput.setFont(ViewMaster.FONT);
	    portLabel.setFont(ViewMaster.FONT);
	    portInput.setFont(ViewMaster.FONT);
	    portInput.setText("1099");
	    usernameLabel.setFont(ViewMaster.FONT);
	    usernameInput.setFont(ViewMaster.FONT);
	    confirmLabel.setFont(ViewMaster.FONT);
	    nosoundLabel.setFont(ViewMaster.FONT);
	    joinButton.setFont(ViewMaster.FONT);

	    joinButton.registerClick(() -> onConnectClick());
	    confirmInput.selectedProperty()
		    .addListener(confirm -> ClientRefrence.setConfirmModus(confirmInput.isSelected()));
	    nosoundInput.selectedProperty()
		    .addListener(confirm -> ClientRefrence.setNoSound(nosoundInput.isSelected()));

	    form.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, usernameLabel, usernameInput,
		    confirmLabel, confirmInput, nosoundLabel, nosoundInput, joinButton,
		    SettingsButton.getButton(this, 13, 645));
	}
	return form;
    }

    @Override
    public void updateConfig() {
	ipLabel.setText(TranslationManager.translate("lobby.label.ip"));
	portLabel.setText(TranslationManager.translate("lobby.label.port"));
	usernameLabel.setText(TranslationManager.translate("lobby.label.username"));
	confirmLabel.setText(TranslationManager.translate("lobby.label.confirmeverything"));
	nosoundLabel.setText(TranslationManager.translate("lobby.label.nosound"));
	joinButton.updateText(TranslationManager.translate("lobby.button.join"));
    }

    /**
     * When mouse clicked a User gets registered.
     */
    public void onConnectClick() {
	controller.connect();
    }

    /**
     * Gets input of the IpLabel.
     * 
     * @return The input text of the Ip label.
     */
    public String getIpInput() {
	return ipInput.getText();
    }

    /**
     * Gets input of the portNumber label and converts it to an int.
     * 
     * @return The input of the portNumber label in an int
     */
    public int getPortInput() throws NumberFormatException {
	return Integer.parseInt(portInput.getText());
    }

    /**
     * Gets the input of the username label.
     * 
     * @return The input of the username label.
     */
    public String getUsernameInput() {
	return usernameInput.getText();
    }

    /**
     * Gets the no sound input.
     * 
     * @return The no sound input.
     */
    public boolean getNosoundInput() {
	return nosoundInput.isSelected();
    }
}
