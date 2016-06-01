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

    public MenuButton() {
	this("");
    }

    public MenuButton(String text) {
	super(text);
	registerClick(() -> {
	    SoundUtil.playSound("sound/clicksound.wav");
	});
    }

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
