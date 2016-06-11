package nl.groep4.kvc.client.view.elements;

import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneHolder;
import nl.groep4.kvc.client.view.scene.SceneSettings;

public class SettingsButton extends MenuButton {

    private static final SettingsButton BUTTON = new SettingsButton();

    private SceneHolder parent;

    public SettingsButton() {
	super(TranslationManager.translate("lobby.button.settings"));
	setFont(ViewMaster.FONT);
	registerClick(() -> onSettingsClick());
    }

    private void onSettingsClick() {
	ViewMaster.setScene(new SceneSettings(parent));
    }

    public static void updateConfig() {
	BUTTON.updateText(TranslationManager.translate("lobby.button.settings"));
    }

    public static SettingsButton getButton(SceneHolder parent, int x, int y) {
	BUTTON.parent = parent;
	BUTTON.setLayoutX(x);
	BUTTON.setLayoutY(y);
	return BUTTON;
    }

}
