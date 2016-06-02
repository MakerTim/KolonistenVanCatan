package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.LanguageButton;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.MenuSlider;
import nl.groep4.kvc.client.view.elements.TexturedButton;

/**
 * Configuration Scene
 * 
 * @version 1.0
 * @author Luc
 **/
public class SceneSettings implements SceneHolder {

    private static final String PLAY = "settings.button.playmusic";
    private static final String STOP = "settings.button.stopmusic";

    private SceneHolder parent;
    private Slider slider;

    public SceneSettings(SceneHolder parent) {
	this.parent = parent;
    }

    @Override
    public Scene getScene() throws RemoteException {
	/* Build multiple layers for the design */
	Pane layers = new Pane();

	/* Build the settings menu in lobby */
	MenuButton music = new MenuButton(415, 150,
		TranslationManager.translate(SoundUtil.themesongIsPlaying() ? STOP : PLAY));
	music.setFont(ViewMaster.FONT);
	slider = new MenuSlider(415, 230, 0, 1, SoundUtil.getVolumeLevel() + 0.5);
	slider.setPrefWidth(175);
	slider.valueProperty().addListener(changed -> {
	    SoundUtil.setVolume((float) (slider.getValue() - 0.5));
	});
	TexturedButton language = new LanguageButton();
	language.setLayoutX(460);
	language.setLayoutY(300);

	MenuButton acceptSettings = new MenuButton(415, 550, TranslationManager.translate("settings.button.accept"));
	acceptSettings.setFont(ViewMaster.FONT);
	music.registerClick(() -> {
	    if (SoundUtil.themesongIsPlaying()) {
		SoundUtil.stopThemesong();
		music.updateText(TranslationManager.translate(PLAY));

	    } else {
		SoundUtil.playThemesong();
		music.updateText(TranslationManager.translate(STOP));
	    }
	});

	acceptSettings.registerClick(() -> {
	    try {
		ViewMaster.setScene(parent.getScene());
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});

	Text settings = new Text(465, 120, TranslationManager.translate("settings.label.title"));
	settings.setFont(ViewMaster.FONT);
	settings.setFill(Color.WHITE);
	settings.setStroke(Color.BLACK);

	language.registerClick(() -> {
	    music.updateText(TranslationManager.translate(SoundUtil.themesongIsPlaying() ? STOP : PLAY));
	    acceptSettings.updateText(TranslationManager.translate("settings.button.accept"));
	    settings.setText(TranslationManager.translate("settings.label.title"));
	});

	layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getSettingsForeground(),
		SceneUtil.getMenuBrazier(), buildFrom(), acceptSettings, settings, slider, language, music);
	Scene scene = new Scene(layers);
	SceneUtil.fadeIn(SceneUtil.getSettingsForeground(), settings, slider, language, music, acceptSettings);

	return scene;

    }

    private Node buildFrom() {
	Pane settingsPane = new Pane();
	return settingsPane;

    }
}
