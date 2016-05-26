package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.groep4.kvc.client.view.elements.Button;

public class ViewLobby extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Starting lobby");

		// Build multiple layers for the design
		Pane layers = new StackPane();
		GridPane theGrid = new GridPane();

		// Add all user interactables
		theGrid.getChildren().add(new Button());

		// Build the lobby
		layers.getChildren().addAll(getBackground(), getForeground(), getBrazier(), theGrid);
		Scene scene = new Scene(layers);
		scene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kolonisten van Catan: Online");
		primaryStage.setResizable(false);
		primaryStage.show();
		System.out.println("Showing lobby");
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
