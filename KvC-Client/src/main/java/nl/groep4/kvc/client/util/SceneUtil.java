package nl.groep4.kvc.client.util;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class SceneUtil {

    public static Node getLobbbyBackground() {
	return new ImageView("img/lobby/menu_background.png");
    }

    public static Node getLobbyForeground() {
	return new ImageView("img/lobby/menu_foreground.png");
    }

    public static Node getLobbyBrazier() {
	return new ImageView("img/lobby/brazier.gif");
    }

    public static Node getLobbySettings() {
	return new ImageView("img/lobby/menu_settings.png");
    }
}
