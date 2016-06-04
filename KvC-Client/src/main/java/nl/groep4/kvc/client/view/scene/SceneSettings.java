package nl.groep4.kvc.client.view.scene;

import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

    private Text settings;
    private MenuButton music;
    private TexturedButton language;
    private MenuButton acceptSettings;

    private SceneHolder parent;
    private Slider slider;

    public SceneSettings(SceneHolder parent) {
	this.parent = parent;
    }

    @Override
    public Scene getScene() {
	/* Build multiple layers for the design */
	Pane layers = new Pane();
	Pane form = new VBox(20);
	form.setLayoutX(410);
	form.setLayoutY(100);

	settings = new Text(TranslationManager.translate("settings.label.title"));
	settings.setTextAlignment(TextAlignment.CENTER);
	settings.setFont(ViewMaster.FONT);
	settings.setFill(Color.WHITE);
	settings.setStroke(Color.BLACK);
	settings.prefWidth(175);

	/* Build the settings menu in lobby */
	music = new MenuButton(TranslationManager.translate(SoundUtil.themesongIsPlaying() ? STOP : PLAY));
	music.setFont(ViewMaster.FONT);
	slider = new MenuSlider(415, 230, 0, 1, SoundUtil.getVolumeLevel() + 0.5);
	slider.setPrefWidth(175);
	slider.valueProperty().addListener(changed -> {
	    SoundUtil.setVolume((float) (slider.getValue() - 0.5));
	});
	language = new LanguageButton();
	language.setLayoutX(460);
	language.setLayoutY(300);

	acceptSettings = new MenuButton(415, 550, TranslationManager.translate("settings.button.accept"));
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
	    ViewMaster.setScene(parent);
	});

	language.registerClick(() -> {
	    updateConfig();
	    ViewMaster.updateConfig();
	});

	form.getChildren().addAll(new StackPane(settings), music, slider, language);
	layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getSettingsForeground(),
		SceneUtil.getMenuBrazier(), acceptSettings, form);
	Scene scene = new Scene(layers);
	SceneUtil.fadeIn(SceneUtil.getSettingsForeground(), form, acceptSettings);
	return scene;

    }

    @Override
    public void updateConfig() {
	music.updateText(TranslationManager.translate(SoundUtil.themesongIsPlaying() ? STOP : PLAY));
	acceptSettings.updateText(TranslationManager.translate("settings.button.accept"));
	settings.setText(TranslationManager.translate("settings.label.title"));
	if (parent instanceof SceneLogin) {
	    ((SceneLogin) parent).updateConfig();
	}
    }
}
