package nl.groep4.kvc.client.view.elements;

import javafx.stage.Stage;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneHolder;
import nl.groep4.kvc.client.view.scene.SceneSettings;

/**
 * Button linked to settings menu. Updates when settings are changed.
 * 
 * @author Tim
 * @version 1.0
 */
public class SettingsButton extends MenuButton {

    private static final SettingsButton BUTTON = new SettingsButton();

    private SceneHolder parent;

    /**
     * Translates button and activates when registers a click
     */
    public SettingsButton() {
	super(TranslationManager.translate("lobby.button.settings"));
	setFont(ViewMaster.FONT);
	registerClick(() -> onSettingsClick());
    }

    private void onSettingsClick() {
	if (parent == null) {
	    Stage stage = new Stage();
	    stage.setScene(new SceneSettings(null).getScene());
	    stage.showAndWait();
	} else {
	    ViewMaster.setScene(new SceneSettings(parent));
	}
    }

    /**
     * Translates button string
     */
    public static void updateConfig() {
	BUTTON.updateText(TranslationManager.translate("lobby.button.settings"));
    }

    /**
     * gets button settings
     * 
     * @param parent
     *            parent of button
     * @param x
     *            sets the value of the property layoutX
     * @param y
     *            sets the value of the property layoutY
     * @return
     */
    public static SettingsButton getButton(SceneHolder parent, int x, int y) {
	BUTTON.parent = parent;
	BUTTON.setLayoutX(x);
	BUTTON.setLayoutY(y);
	return BUTTON;
    }

}
