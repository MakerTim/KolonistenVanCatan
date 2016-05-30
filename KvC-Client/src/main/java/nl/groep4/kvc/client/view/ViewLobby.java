package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.controller.LobbyController;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;
import nl.groep4.kvc.client.view.elements.LobbyCheckBox;
import nl.groep4.kvc.client.view.elements.LobbyFilterdInputField;
import nl.groep4.kvc.client.view.elements.LobbyMatchInputField;

public class ViewLobby extends Application {

    public static final Font FONT = new Font("Impact", 22);

    private TextField ipInput;
    private TextField portInput;
    private TextField usernameInput;
    private CheckBox nocolorInput;
    private CheckBox confirmInput;
    private CheckBox nosoundInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
	System.out.println("Starting lobby");

	/* Build multiple layers for the design */
	Pane layers = new StackPane();

	/* Build the lobby */
	layers.getChildren().addAll(getBackground(), getForeground(), getBrazier(), buildFrom());

	/* Setup the window */
	Scene scene = new Scene(layers);
	scene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
	primaryStage.setScene(scene);
	primaryStage.setTitle("Kolonisten van Catan: Online");
	primaryStage.setResizable(false);
	primaryStage.show();
	System.out.println("Showing lobby");

	SoundUtil.playSound("sound/themesongKvC.wav");
	System.out.println("Playing themesong");
    }

    private Node buildFrom() {
	Pane theGrid = new Pane();

	Text ipLabel = new Text(330, 350, "Server IP");
	/*
	 * REGEX: ipv4 adress or a (subd)domain name *including case or
	 * 'localhost'
	 */
	ipInput = new LobbyMatchInputField(450, 320, "",
		"((?:[0-9]{1,3}\\.){3}[0-9]{1,3})|(([a-zA-Z0-9|-]+\\.)*[a-zA-Z0-9|-]+\\.[a-zA-Z]+)|(localhost)");
	Text portLabel = new Text(330, 375, "Server port");
	/* REGEX: only numbers */
	portInput = new LobbyFilterdInputField(450, 345, "", "[0-9]");
	Text usernameLabel = new Text(330, 410, "Username");
	/* REGEX: only characters, capital charcaters or numbers */
	usernameInput = new LobbyFilterdInputField(450, 380, "", "[a-zA-Z0-9]");
	Text nocolorLabel = new Text(330, 450, "No color");
	nocolorInput = new LobbyCheckBox(540, 434, false);
	Text confirmLabel = new Text(330, 470, "Confirm every action");
	confirmInput = new LobbyCheckBox(540, 454, false);
	Text nosoundLabel = new Text(330, 490, "No sounds");
	nosoundInput = new LobbyCheckBox(540, 474, false);
	LobbyButton joinButton = new LobbyButton(425, 500, "Join");
	LobbyButton settingsButton = new LobbyButton(13, 650, "Settings");

	ipLabel.setFont(FONT);
	ipInput.setFont(FONT);
	portLabel.setFont(FONT);
	portInput.setFont(FONT);
	portInput.setText("1099");
	usernameLabel.setFont(FONT);
	usernameInput.setFont(FONT);
	nocolorLabel.setFont(FONT);
	confirmLabel.setFont(FONT);
	nosoundLabel.setFont(FONT);
	joinButton.setFont(FONT);
	joinButton.registerClick(() -> onConnectClick());
	settingsButton.setFont(FONT);

	theGrid.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, usernameLabel, usernameInput, nocolorLabel,
		nocolorInput, confirmLabel, confirmInput, nosoundLabel, nosoundInput, joinButton, settingsButton);
	return theGrid;
    }

    public void onConnectClick() {
	LobbyController.connect(this);
    }

    public void onSettingsClick() {

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

    private Node getBackground() {
	return new ImageView("img/lobby/menu_background.png");
    }

    private Node getForeground() {
	return new ImageView("img/lobby/menu_foreground.png");
    }

    private Node getBrazier() {
	return new ImageView("img/lobby/brazier.gif");
    }

}
