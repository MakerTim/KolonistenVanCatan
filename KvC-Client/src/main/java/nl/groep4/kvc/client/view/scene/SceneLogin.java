package nl.groep4.kvc.client.view.scene;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.MenuCheckBox;
import nl.groep4.kvc.client.view.elements.MenuFilterdInputField;
import nl.groep4.kvc.client.view.elements.MenuMatchInputField;
import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * Builds the Scene for logging in to servers - mainscreen
 * 
 * @version 1.0
 * @author Tim
 **/
public class SceneLogin implements SceneHolder {

    private static Pane form;

    private Text ipLabel;
    private Text portLabel;
    private Text usernameLabel;
    private Text nocolorLabel;
    private Text confirmLabel;
    private Text nosoundLabel;
    private MenuButton joinButton;
    private MenuButton settingsButton;
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
	layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLoginForeground(),
		SceneUtil.getMenuBrazier(), buildFrom());
	SceneUtil.fadeIn(CollectionUtil.getItems(layers.getChildren(), 1, 3));
	return new Scene(layers);
    }

    private Node buildFrom() {
	if (form == null) {
	    form = new Pane();

	    ipLabel = new Text(310, 350, TranslationManager.translate("lobby.label.ip"));
	    ipInput = new MenuMatchInputField(460, 320, "", KvCStatics.REGEX_IP);
	    portLabel = new Text(310, 375, TranslationManager.translate("lobby.label.port"));
	    portInput = new MenuFilterdInputField(460, 345, "", KvCStatics.NUMERIC);
	    usernameLabel = new Text(310, 410, TranslationManager.translate("lobby.label.username"));
	    usernameInput = new MenuFilterdInputField(460, 380, "", KvCStatics.USERNAME);
	    nocolorLabel = new Text(310, 450, TranslationManager.translate("lobby.label.nocolor"));
	    nocolorInput = new MenuCheckBox(540, 434, false);
	    confirmLabel = new Text(310, 470, TranslationManager.translate("lobby.label.confirmeverything"));
	    confirmInput = new MenuCheckBox(540, 454, false);
	    nosoundLabel = new Text(310, 490, TranslationManager.translate("lobby.label.nosound"));
	    nosoundInput = new MenuCheckBox(540, 474, false);
	    joinButton = new MenuButton(425, 500, TranslationManager.translate("lobby.button.join"));
	    settingsButton = new MenuButton(13, 645, TranslationManager.translate("lobby.button.settings"));

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

    @Override
    public void updateConfig() {
	ipLabel.setText(TranslationManager.translate("lobby.label.ip"));
	portLabel.setText(TranslationManager.translate("lobby.label.port"));
	usernameLabel.setText(TranslationManager.translate("lobby.label.username"));
	nocolorLabel.setText(TranslationManager.translate("lobby.label.nocolor"));
	confirmLabel.setText(TranslationManager.translate("lobby.label.confirmeverything"));
	nosoundLabel.setText(TranslationManager.translate("lobby.label.nosound"));
	joinButton.updateText(TranslationManager.translate("lobby.button.join"));
	settingsButton.updateText(TranslationManager.translate("lobby.button.settings"));
    }

    public void onConnectClick() {
	// TODO
    }

    public void onSettingsClick() {
	ViewMaster.setScene(new SceneSettings(this));
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
