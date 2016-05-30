package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * Lobby buttons, extension of {@link TexturedButton}
 * 
 * @version 1.0
 * @author Tim
 **/
public class LobbyButton extends TexturedButton {

    private static final Image BUTTON_IMAGE = new Image("img/etc/button.png");
    private static final Image BUTTON_HOVER_IMAGE = new Image("img/etc/button_hover.png");
    private static final Image BUTTON_PRESSED_IMAGE = new Image("img/etc/button_pressed.png");

    private Runnable todo;

    public LobbyButton() {
	super();
    }

    public LobbyButton(String text) {
	super(text);
    }

    public LobbyButton(int xPos, int yPos, String text) {
	super(text);
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

    @Override
    public void onClick(MouseEvent mce) {
	SoundUtil.playSound("sound/clicksound.wav");
	if (todo != null) {
	    todo.run();
	}
    }

    public void registerClick(Runnable todo) {
	this.todo = todo;
    }

}
