package nl.groep4.kvc.client.view;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class SceneSettings implements SceneHolder {

    @Override
    public Scene getScene() {
	// TODO: Settings screen
	return new Scene(new GridPane());
    }

}
