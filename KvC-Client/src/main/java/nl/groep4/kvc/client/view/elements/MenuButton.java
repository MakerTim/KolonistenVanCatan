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
     */
    public MenuButton(String text) {
	super(text);
	registerClick(() -> {
	    SoundUtil.playSound("sound/clicksound.wav");
	});
    }

    /**
     * 
     * 
     * @param xPos
     * @param yPos
     * @param text
     */
    public MenuButton(int xPos, int yPos, String text) {
	this(text);
	this.setLayoutX(xPos);
	this.setLayoutY(yPos);
    }

    /**
     * Gets button image
     */
    @Override
    public Image getTexture() {
	return BUTTON_IMAGE;
    }

    /**
     * Gets button image when mouse hovers over a button
     */
    @Override
    public Image getHoverTexture() {
	return BUTTON_HOVER_IMAGE;
    }

    /**
     * Gets button image when mouse pressed a button
     */
    @Override
    public Image getClickTexture() {
	return BUTTON_PRESSED_IMAGE;
    }
}
