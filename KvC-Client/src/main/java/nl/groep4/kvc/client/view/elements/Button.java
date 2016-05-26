package nl.groep4.kvc.client.view.elements;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewLobby;

public class Button extends StackPane {

	private static final Image BUTTON_IMAGE = new Image("img/etc/button.png");
	private static final Image BUTTON_HOVER_IMAGE = new Image("img/etc/button_hover.png");
	private static final Image BUTTON_PRESSED_IMAGE = new Image("img/etc/button_pressed.png");

	protected Text label;

	public Button() {
		ImageView background = new ImageView(BUTTON_IMAGE);
		label = new Text();
		label.setFont(ViewLobby.FONT);
		label.setFill(Color.WHITE);
		label.setEffect(getShadowEffect());
		setOnMouseEntered(mouseEnter -> background.setImage(BUTTON_HOVER_IMAGE));
		setOnMouseExited(mouseLeave -> background.setImage(BUTTON_IMAGE));
		setOnMousePressed(mousePressed -> {
			background.setImage(BUTTON_PRESSED_IMAGE);
			onClick(mousePressed);
		});
		setOnMouseReleased(mouseRelease -> background.setImage(BUTTON_HOVER_IMAGE));
		getChildren().addAll(background, label);
	}

	public Button(String text) {
		// #ConstructorLover
		this();
		updateText(text);
	}

	public void updateText(String text) {
		this.label.setText(text);
	}

	public String getText() {
		return this.label.getText();
	}

	public void onClick(MouseEvent mce) {
	}

	private DropShadow getShadowEffect() {
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(3);
		shadow.setOffsetY(3);
		shadow.setColor(Color.BLACK);
		return shadow;
	}
}
