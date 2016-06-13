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
    private static Node oreCard;
    private static Node stoneCard;
    private static Node wheatCard;
    private static Node woodCard;
    private static Node woolCard;
    private static Node cardPlank;

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

    /**
     * Gets background of the pane
     * 
     * @return image of the background from the pane menus
     */
    public static Node getGamePane() {
	if (gamepane == null) {
	    gamepane = new ImageView("img/game/pane_background.png");
	}
	return gamepane;
    }

    public static Node getCardPlank() {
	if (cardPlank == null) {
	    cardPlank = new ImageView("img/game/kaart_plank.png");
	}
	return cardPlank;
    }

    /**
     * Gets the resource for the wood card
     * 
     * @return image of the wood card
     */
    public static Node getWoodCard() {
	if (woodCard == null) {
	    woodCard = new ImageView("img/cards/kaart_hout.png");
	}
	return woodCard;
    }

    /**
     * Gets the resource for the stone card
     * 
     * @return image of the stone card
     */
    public static Node getStoneCard() {
	if (stoneCard == null) {
	    stoneCard = new ImageView("img/cards/kaart_steen.png");
	}
	return stoneCard;
    }

    /**
     * Gets the resource for the wool card
     * 
     * @return image of the wool card
     */
    public static Node getWoolCard() {
	if (woolCard == null) {
	    woolCard = new ImageView("img/cards/kaart_schaap.png");
	}
	return woolCard;
    }

    /**
     * Gets the resource for the wheat card
     * 
     * @return image of the wheat card
     */
    public static Node getWheatCard() {
	if (wheatCard == null) {
	    wheatCard = new ImageView("img/cards/kaart_graan.png");
	}
	return wheatCard;
    }

    /**
     * Gets the resource for the ore card
     * 
     * @return image of the ore card
     */
    public static Node getOreCard() {
	if (oreCard == null) {
	    oreCard = new ImageView("img/cards/kaart_erts.png");
	}
	return oreCard;
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
