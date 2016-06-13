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
    private static Node gamepane;
    private static Node buildMenu;

    /**
     * Gets the background for the menu
     * 
     * @return background image of the menu
     */
    public static Node getMenuBackground() {
	if (background == null) {
	    background = new ImageView("img/menu/menu_background.png");
	}
	return background;
    }

    /**
     * Gets the foreground of loginscreen
     * 
     * @return foreground image of the loginscreen
     */
    public static Node getLoginForeground() {
	if (forground == null) {
	    forground = new ImageView("img/menu/login_foreground.png");
	}
	return forground;
    }

    /**
     * Gets the Braziers from the menu
     * 
     * @return brazier image
     */
    public static Node getMenuBrazier() {
	if (brazier == null) {
	    brazier = new ImageView("img/menu/brazier.gif");
	}
	return brazier;
    }

    /**
     * Gets the foreground of the settings
     * 
     * @return image of the foreground from the settings
     */
    public static Node getSettingsForeground() {
	if (settings == null) {
	    settings = new ImageView("img/menu/settings_foreground.png");
	}
	return settings;
    }

    /**
     * Gets the foreground of the settings
     * 
     * @return image of the foreground from the lobby
     */
    public static Node getLobbyForeground() {
	if (lobbyBack == null) {
	    lobbyBack = new ImageView("img/menu/lobby_foreground.png");
	}
	return lobbyBack;
    }

    /**
     * Gets the cornershield image
     * 
     * @return image of the corner shield
     */
    public static Node getCornerShield() {
	if (cornerShield == null) {
	    cornerShield = new ImageView("img/menu/shield_corner.png");
	}
	return cornerShield;
    }

    /**
     * Gets the board
     * 
     * @return image of the Board
     */
    public static Node getBoard() {
	if (board == null) {
	    board = new ImageView("img/game/board.png");
	}
	return board;
    }

    /**
     * Gets background of the board
     * 
     * @return image of the background from the board
     */
    public static Node getBoardBackground() {
	if (boardBackground == null) {
	    boardBackground = new ImageView("img/game/board_background.gif");
	}
	return boardBackground;
    }

    public static Node getGamePane() {
	if (gamepane == null) {
	    gamepane = new ImageView("img/game/pane_background.png");
	}
	return gamepane;
    }

    /**
     * Gives a fade-in for each element in the List
     * 
     * @param elements
     *            an element in the List
     */
    public static void fadeIn(List<Node> elements) {
	elements.forEach(element -> fadeIn(element));
    }

    /**
     * Gives a fade-in for each element in the Array
     * 
     * @param elements
     *            an element in the Array
     */
    public static void fadeIn(Node... elements) {
	Arrays.stream(elements).forEach(element -> fadeIn(element));
    }

    /**
     * Gives fade transition to element and plays it
     * 
     * @param element
     */
    public static void fadeIn(Node element) {
	FadeTransition ft = new FadeTransition(Duration.millis(500), element);
	ft.setFromValue(0);
	ft.setToValue(1);
	ft.play();
    }

    public static Node getBuildMenuBackground() {
	if (buildMenu == null) {
	    buildMenu = new ImageView("img/game/settings_foreground_horizontal.png");
	}
	return buildMenu;
    }
}
