package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * Lobby buttons, extension of TexturedButton.
 * 
 * @author Tim
 * @version 1.1
 **/
public class MenuButton extends TexturedButton {

    private static final Image BUTTON_IMAGE = new Image("img/etc/button.png");
    private static final Image BUTTON_HOVER_IMAGE = new Image("img/etc/button_hover.png");
    private static final Image BUTTON_PRESSED_IMAGE = new Image("img/etc/button_pressed.png");

    /**
     * Makes the MenuButton.
     */
    public MenuButton() {
	this("");
    }

    /**
     * A sound effect that plays when button is clicked.
     * 
     * @param text
     *            The string to display on the button.
     */
    public MenuButton(String text) {
	super(text);
	registerClick(() -> {
	    SoundUtil.playSound("sound/clicksound.wav");
	});
    }

    /**
     * Places MenuButton to location.
     * 
     * @param xPos
     *            The x-coordinate position of MenuButton.
     * @param yPos
     *            The y-coordinate position of MenuButton.
     * @param text
     *            The string to display on the button.
     */
    public MenuButton(int xPos, int yPos, String text) {
	this(text);
	this.setLayoutX(xPos);
	this.setLayoutY(yPos);
    }

    @Override
    public Image getTexture() {
	return BUTTON_IMAGE;
    }

    @Override
    public Image getHoverTexture() {
	return BUTTON_HOVER_IMAGE;
    }

    @Override
    public Image getClickTexture() {
	return BUTTON_PRESSED_IMAGE;
    }
}
