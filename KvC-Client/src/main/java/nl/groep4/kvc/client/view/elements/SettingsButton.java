package nl.groep4.kvc.client.view.elements;

import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneHolder;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.client.view.scene.SceneSettings;

/**
 * Button linked to settings menu. Updates when settings are changed.
 * 
 * @author Tim
 * @version 1.1
 */
public class SettingsButton extends MenuButton {

    private static final SettingsButton BUTTON = new SettingsButton();

    private SceneHolder parent;

    /**
     * Translates button and activates when registers a click.
     * 
     */
    private SettingsButton() {
	super(TranslationManager.translate("lobby.button.settings"));
	setFont(ViewMaster.FONT);
	registerClick(() -> onSettingsClick());
    }

    private void onSettingsClick() {
	if (parent instanceof SceneMap) {
	    ((SceneMap) parent).setOverlay(new SceneSettings(parent));
	} else {
	    ViewMaster.setScene(new SceneSettings(parent));
	}
    }

    /**
     * Translates button string.
     * 
     */
    public static void updateConfig() {
	BUTTON.updateText(TranslationManager.translate("lobby.button.settings"));
    }

    /**
     * Gets button settings.
     * 
     * @param parent
     *            Parent of button.
     * @param x
     *            Sets the value of the property layoutX.
     * @param y
     *            Sets the value of the property layoutY.
     * @return The button with all its values.
     */
    public static SettingsButton getButton(SceneHolder parent, int x, int y) {
	BUTTON.parent = parent;
	BUTTON.setLayoutX(x);
	BUTTON.setLayoutY(y);
	return BUTTON;
    }

}
