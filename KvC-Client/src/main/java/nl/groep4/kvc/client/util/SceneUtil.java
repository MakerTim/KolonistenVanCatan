package nl.groep4.kvc.client.util;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Sets the lobby images, this includes background, foreground, brazier and
 * menu-settings background.
 * 
 * @author Tim
 * @version 1.0
 */

public class SceneUtil {

    private static Node background;
    private static Node forground;
    private static Node brazier;
    private static Node settings;

    public static Node getLobbbyBackground() {
	if (background == null) {
	    background = new ImageView("img/lobby/menu_background.png");
	}
	return background;
    }

    public static Node getLobbyForeground() {
	if (forground == null) {
	    forground = new ImageView("img/lobby/menu_foreground.png");
	}
	return forground;
    }

    public static Node getLobbyBrazier() {
	if (brazier == null) {
	    brazier = new ImageView("img/lobby/brazier.gif");
	}
	return brazier;
    }

    public static Node getLobbySettings() {
	if (settings == null) {
	    settings = new ImageView("img/lobby/menu_settings.png");
	}
	return settings;
    }
}
