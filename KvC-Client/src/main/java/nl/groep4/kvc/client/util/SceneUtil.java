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
 * @version 1.1
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
    private static Node gamepane;
    private static Node settingsPane;

    /**
     * Gets the background for the menu.
     * 
     * @return Background image of the menu.
     */
    public static Node getMenuBackground() {
	if (background == null) {
	    background = new ImageView("img/menu/menu_background.png");
	}
	return background;
    }

    /**
     * Gets the foreground of the login screen.
     * 
     * @return Foreground image of the login screen.
     */
    public static Node getLoginForeground() {
	if (forground == null) {
	    forground = new ImageView("img/menu/login_foreground.png");
	}
	return forground;
    }

    /**
     * Gets the Braziers from the menu.
     * 
     * @return The braziers image.
     */
    public static Node getMenuBrazier() {
	if (brazier == null) {
	    brazier = new ImageView("img/menu/brazier.gif");
	}
	return brazier;
    }

    /**
     * Gets the foreground of the settings.
     * 
     * @return Image of the foreground from the settings.
     */
    public static Node getSettingsForeground() {
	if (settings == null) {
	    settings = new ImageView("img/menu/settings_foreground.png");
	}
	return settings;
    }

    /**
     * Gets the foreground of the settings.
     * 
     * @return Image of the foreground from the lobby.
     */
    public static Node getLobbyForeground() {
	if (lobbyBack == null) {
	    lobbyBack = new ImageView("img/menu/lobby_foreground.png");
	}
	return lobbyBack;
    }

    /**
     * Gets the corner shield image.
     * 
     * @return Image of the corner shield.
     */
    public static Node getCornerShield() {
	if (cornerShield == null) {
	    cornerShield = new ImageView("img/menu/shield_corner.png");
	}
	return cornerShield;
    }

    /**
     * Gets the board image.
     * 
     * @return Image of the Board.
     */
    public static Node getBoard() {
	if (board == null) {
	    board = new ImageView("img/game/board.png");
	}
	return board;
    }

    /**
     * Gets background of the board.
     * 
     * @return Image of the background from the board.
     */
    public static Node getBoardBackground() {
	if (boardBackground == null) {
	    boardBackground = new ImageView("img/game/board_background.gif");
	}
	return boardBackground;
    }

    /**
     * Gets background of the Pane.
     * 
     * @return Image of the background from the Pane menus.
     */
    public static Node getGamePane() {
	if (gamepane == null) {
	    gamepane = new ImageView("img/game/pane_background.png");
	}
	return gamepane;
    }

    /**
     * Gets settingsPane.
     * 
     * @return SettingsPane with its background.
     */
    public static Node getSettingsPane() {
	if (settingsPane == null) {
	    settingsPane = new ImageView("img/menu/settings.png");
	}
	return settingsPane;
    }

    /**
     * Gives a fade-in for each element in the List.
     * 
     * @param elements
     *            An element in the List.
     */
    public static void fadeIn(List<Node> elements) {
	elements.forEach(element -> fadeIn(element));
    }

    /**
     * Gives a fade-in for each element in the Array.
     * 
     * @param elements
     *            An element in the Array.
     */
    public static void fadeIn(Node... elements) {
	Arrays.stream(elements).forEach(element -> fadeIn(element));
    }

    /**
     * Gives fade transition to the element and plays it.
     * 
     * @param element
     *            The element with the fadeIn transition.
     */
    public static void fadeIn(Node element) {
	FadeTransition ft = new FadeTransition(Duration.millis(500), element);
	ft.setFromValue(0);
	ft.setToValue(1);
	ft.play();
    }

    /**
     * Gives a fade-out for each element in the Array.
     * 
     * @param elements
     *            An element in the Array.
     */
    public static void fadeOut(Node... elements) {
	Arrays.stream(elements).forEach(element -> fadeOut(element));
    }

    /**
     * Gives fade out transition to the element and plays it.
     * 
     * @param element
     *            The element with the fadeOut transition.
     */
    public static void fadeOut(Node element) {
	FadeTransition ft = new FadeTransition(Duration.millis(500), element);
	ft.setFromValue(1);
	ft.setToValue(0);
	ft.play();
    }
}
