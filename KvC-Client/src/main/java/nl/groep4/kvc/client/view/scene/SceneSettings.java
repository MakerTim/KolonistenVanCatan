package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import nl.groep4.kvc.client.controller.Controller;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.LanguageButton;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.MenuSlider;
import nl.groep4.kvc.client.view.elements.TexturedButton;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.common.interfaces.UpdateMap;

/**
 * Configuration Scene.
 * 
 * @author Luc
 * @version 1.1
 * 
 **/
public class SceneSettings implements SceneHolder, PaneHolder {

    private static final String PLAY = "settings.button.playmusic";
    private static final String STOP = "settings.button.stopmusic";

    private Text settings;
    private MenuButton music;
    private TexturedButton language;
    private MenuButton acceptSettings;

    private SceneHolder parent;
    private Slider slider;
    private Pane form;

    /**
     * Current settings.
     * 
     * @param parent
     *            Of sceneSettings.
     */
    public SceneSettings(SceneHolder parent) {
	this.parent = parent;
    }

    @Override
    public Scene getScene() {
	Pane pane = getPane();
	Scene scene = new Scene(pane);
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

    @Override
    public void registerController(Controller controller) {
    }

    @Override
    public Pane getPane() {
	/* Build multiple layers for the design */
	Pane layers = new Pane();
	form = new VBox(20);
	form.setLayoutX(410);
	form.setLayoutY(100);

	settings = new KvCText(TranslationManager.translate("settings.label.title"));
	settings.setTextAlignment(TextAlignment.CENTER);
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
	    } else {
		SoundUtil.playThemesong();
	    }
	    updateConfig();
	});
	acceptSettings.registerClick(() -> {
	    if (parent instanceof UpdateMap) {
		try {
		    ((UpdateMap) parent).closeOverlay();
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    } else {
		ViewMaster.setScene(parent);
	    }
	});

	language.registerClick(() -> {
	    updateConfig();
	    ViewMaster.updateConfig();
	});

	form.getChildren().addAll(new StackPane(settings), music, slider, language);

	if (!(parent instanceof UpdateMap)) {
	    layers.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getMenuBrazier());
	}
	layers.getChildren().addAll(SceneUtil.getSettingsForeground(), acceptSettings, form);
	HBox hbox = new HBox(layers);
	hbox.setAlignment(Pos.CENTER);
	return hbox;
    }
}
