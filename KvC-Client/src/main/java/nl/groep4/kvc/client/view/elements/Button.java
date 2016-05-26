package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Button extends ImageView {

	private static final Image BUTTON_IMAGE = new Image("img/etc/button.png");
	private static final Image BUTTON_HOVER_IMAGE = new Image("img/etc/button_hover.png");
	private static final Image BUTTON_PRESSED_IMAGE = new Image("img/etc/button_pressed.png");

	protected String text;

	public Button() {
		super(BUTTON_IMAGE);
		setOnMouseEntered(mouseEnter -> setImage(BUTTON_HOVER_IMAGE));
		setOnMouseExited(mouseLeave -> setImage(BUTTON_IMAGE));
		setOnMousePressed(mousePressed -> {
			setImage(BUTTON_PRESSED_IMAGE);
			onClick(mousePressed);
		});
		setOnMouseReleased(mouseLeave -> setImage(BUTTON_IMAGE));
	}

	public Button(String text) {
		// #ConstructorLover
		this();
		this.text = text;
	}

	public void updateText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void onClick(MouseEvent mce) {
	}

}
