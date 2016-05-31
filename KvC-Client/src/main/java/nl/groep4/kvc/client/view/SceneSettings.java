package nl.groep4.kvc.client.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.MenuSlider;

/**
 * Configuration Scene
 * 
 * @version 1.0
 * @author Luc
 **/
public class SceneSettings implements SceneHolder {
    private static final String PLAY = "Play Music";
    private static final String STOP = "Stop Music";

    private SceneHolder parent;

    public SceneSettings(SceneHolder parent) {
	this.parent = parent;
    }

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new Pane();

	/* Build the settings menu in lobby */
	MenuButton music = new MenuButton(415, 150, SoundUtil.themesongIsPlaying() ? STOP : PLAY);
	music.setFont(ViewMaster.FONT);
	Slider slider = new MenuSlider(415, 230, 0, 1, SoundUtil.getVolumeLevel() + 0.5);
	slider.setPrefWidth(175);
	slider.valueProperty().addListener(changed -> {
	    SoundUtil.setVolume((float) (slider.getValue() - 0.5));
	});
	MenuButton acceptSettings = new MenuButton(415, 550, "Accept");
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

	acceptSettings.registerClick(() -> {
	    ViewMaster.setScene(parent.getScene());
	});

	Text settings = new Text(465, 120, "Settings");
	settings.setFont(ViewMaster.FONT);
	settings.setFill(Color.WHITE);
	settings.setStroke(Color.BLACK);

	layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbySettings(),
		SceneUtil.getLobbyBrazier(), buildFrom(), acceptSettings, settings, slider, music);
	Scene scene = new Scene(layers);
	SceneUtil.fadeIn(SceneUtil.getLobbySettings(), settings, slider, music, acceptSettings);

	return scene;

    }

    private Node buildFrom() {
	Pane settingsPane = new Pane();
	return settingsPane;

    }

}
