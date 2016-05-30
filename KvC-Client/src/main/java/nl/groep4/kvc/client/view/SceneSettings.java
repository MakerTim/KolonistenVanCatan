package nl.groep4.kvc.client.view;

import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;

public class SceneSettings implements SceneHolder {

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new Pane();

	/* Build the settings menu in lobby */
	LobbyButton acceptSettings = new LobbyButton(415, 550, "Accept");
	acceptSettings.setFont(ViewMaster.FONT);

	Text settings = new Text(465, 120, "Settings");
	settings.setFont(ViewMaster.FONT);

	layers.getChildren().addAll(SceneUtil.getLobbbyBackground(), SceneUtil.getLobbySettings(),
		SceneUtil.getLobbyBrazier(), buildFrom(), acceptSettings, settings);
	Scene scene = new Scene(layers);
	scene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));

	return scene;

    }

    private Node buildFrom() {
	Pane settingsPane = new Pane();
	return settingsPane;

    }

}
