package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.view.elements.LobbyButton;

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
	}

	private Node buildFrom() {
		Pane theGrid = new Pane();

		Text ipLabel = new Text(350, 350, "Server IP");
		ipLabel.setFont(FONT);

		LobbyButton join = new LobbyButton("Join");
		join.setLayoutX(425);
		join.setLayoutY(500);
		join.setOnMouseClicked(click -> {

		});

		theGrid.getChildren().addAll(ipLabel, join);
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
