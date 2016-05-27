package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;
import nl.groep4.kvc.client.view.elements.LobbyInputField;

public class ViewLobby extends Application {

	public static final Font FONT = new Font("Impact", 22);

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Starting lobby");		

		// Build multiple layers for the design
		Pane layers = new StackPane();

		// Build the lobby
		layers.getChildren().addAll(getBackground(), getForeground(), getBrazier(), buildFrom());
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
		TextField ipInput = new LobbyInputField(450, 320, "");
		Text portLabel = new Text(330, 370, "Server port");
		TextField portInput = new LobbyInputField(450, 345, "");
		Text usernameLabel = new Text(330, 410, "Username");
		Text nocolorLabel = new Text(330, 450, "No color");
		Text confirmLabel = new Text(330, 470, "Confirm every action");
		Text nosoundLabel = new Text(330, 490, "No sounds");

		ipLabel.setFont(FONT);
		ipInput.setFont(FONT);
		portLabel.setFont(FONT);
		portInput.setFont(FONT);
		usernameLabel.setFont(FONT);
		nocolorLabel.setFont(FONT);
		confirmLabel.setFont(FONT);
		nosoundLabel.setFont(FONT);

		LobbyButton join = new LobbyButton("Join");
		join.setLayoutX(425);
		join.setLayoutY(500);
		join.setOnMouseClicked(click -> {

		});

		theGrid.getChildren().addAll(ipLabel, ipInput, portLabel, portInput, usernameLabel, nocolorLabel, confirmLabel,
				nosoundLabel, join);
		return theGrid;
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
