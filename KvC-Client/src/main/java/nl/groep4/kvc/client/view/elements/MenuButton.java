package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * Lobby buttons, extension of {@link TexturedButton}
 * 
 * @version 1.0
 * @author Tim
 **/
public class MenuButton extends TexturedButton {

    private static final Image BUTTON_IMAGE = new Image("img/etc/button.png");
    private static final Image BUTTON_HOVER_IMAGE = new Image("img/etc/button_hover.png");
    private static final Image BUTTON_PRESSED_IMAGE = new Image("img/etc/button_pressed.png");

    /**
     * A menu button
     */
    public MenuButton() {
	this("");
    }

    /**
     * A sound effect that plays when button is clicked
     * 
     * @param text
     *            the string to display on the button
     */
    public MenuButton(String text) {
	super(text);
	registerClick(() -> {
	    SoundUtil.playSound("sound/clicksound.wav");
	});
    }

    /**
     * Places MenuButton to location
     * 
     * @param xPos
     *            the x-coordinate position of MenuButton
     * @param yPos
     *            the y-coordinate position of MenuButton
     * @param text
     *            the string to display on the button
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
