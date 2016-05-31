package nl.groep4.kvc.client.view;

import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.LobbyButton;

/**
 * Configuration Scene
 * 
 * @version 1.0
 * @author Luc
 **/
public class SceneSettings implements SceneHolder {
    private static final String PLAY = "Play Music";
    private static final String STOP = "Stop Music";

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new Pane();

	/* Build the settings menu in lobby */
	LobbyButton music = new LobbyButton(415, 150, SoundUtil.themesongIsPlaying() ? STOP : PLAY);
	music.setFont(ViewMaster.FONT);
	LobbyButton acceptSettings = new LobbyButton(415, 550, "Accept");
	acceptSettings.setFont(ViewMaster.FONT);

	music.registerClick(() -> {
	    if (SoundUtil.themesongIsPlaying()) {
		SoundUtil.stopThemesong();
		music.updateText(PLAY);

	    } else {
		SoundUtil.playThemesong();
		music.updateText(STOP);
	    }
	});

	Text settings = new Text(465, 120, "Settings");
	settings.setFont(ViewMaster.FONT);
	settings.setFill(Color.WHITE);
	settings.setStroke(Color.BLACK);

	layers.getChildren().addAll(SceneUtil.getLobbbyBackground(), SceneUtil.getLobbySettings(),
		SceneUtil.getLobbyBrazier(), buildFrom(), acceptSettings, settings, music);
	Scene scene = new Scene(layers);
	scene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));

	return scene;

    }

    private Node buildFrom() {
	Pane settingsPane = new Pane();
	return settingsPane;

    }

}
