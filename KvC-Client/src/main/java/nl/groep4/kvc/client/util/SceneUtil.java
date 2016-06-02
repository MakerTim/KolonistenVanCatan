package nl.groep4.kvc.client.util;

import java.util.Arrays;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
    private static Node lobbyBack;
    private static Node cornerShield;
    private static Node board;
    private static Node boardBackground;

    public static Node getMenuBackground() {
	if (background == null) {
	    background = new ImageView("img/menu/menu_background.png");
	}
	return background;
    }

    public static Node getLoginForeground() {
	if (forground == null) {
	    forground = new ImageView("img/menu/login_foreground.png");
	}
	return forground;
    }

    public static Node getMenuBrazier() {
	if (brazier == null) {
	    brazier = new ImageView("img/menu/brazier.gif");
	}
	return brazier;
    }

    public static Node getSettingsForeground() {
	if (settings == null) {
	    settings = new ImageView("img/menu/settings_foreground.png");
	}
	return settings;
    }

    public static Node getLobbyForeground() {
	if (lobbyBack == null) {
	    lobbyBack = new ImageView("img/menu/lobby_foreground.png");
	}
	return lobbyBack;
    }

    public static Node getCornerShield() {
	if (cornerShield == null) {
	    cornerShield = new ImageView("img/menu/shield_corner.png");
	}
	return cornerShield;
    }

    public static Node getBoard() {
	if (board == null) {
	    board = new ImageView("img/game/board.png");
	}
	return board;
    }

    public static Node getBoardBackground() {
	if (boardBackground == null) {
	    boardBackground = new ImageView("img/game/board_background.gif");
	}
	return boardBackground;
    }

    public static void fadeIn(List<Node> elements) {
	elements.forEach(element -> fadeIn(element));
    }

    public static void fadeIn(Node... elements) {
	Arrays.stream(elements).forEach(element -> fadeIn(element));
    }

    public static void fadeIn(Node element) {
	FadeTransition ft = new FadeTransition(Duration.millis(500), element);
	ft.setFromValue(0);
	ft.setToValue(1);
	ft.play();
    }
}
