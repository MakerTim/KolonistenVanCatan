package nl.groep4.kvc.client.view.elements;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Button class what makes a combination of images looks like a real button
 * 
 * @version 1.0
 * @author Tim
 **/
public abstract class TexturedButton extends StackPane {

    protected Text label;

    public TexturedButton() {
	ImageView background = new ImageView(getTexture());
	label = new Text();
	label.setFill(Color.WHITE);
	label.setEffect(getShadowEffect());
	setOnMouseEntered(mouseEnter -> background.setImage(getHoverTexture()));
	setOnMouseExited(mouseLeave -> background.setImage(getTexture()));
	setOnMousePressed(mousePressed -> {
	    background.setImage(getClickTexture());
	    onClick(mousePressed);
	});
	setOnMouseReleased(mouseRelease -> background.setImage(getHoverTexture()));
	getChildren().addAll(background, label);
    }

    public TexturedButton(String text) {
	this();
	updateText(text);
    }

    public void setFont(Font font) {
	label.setFont(font);
    }

    public void updateText(String text) {
	this.label.setText(text);
    }

    public String getText() {
	return this.label.getText();
    }

    private DropShadow getShadowEffect() {
	DropShadow shadow = new DropShadow();
	shadow.setOffsetX(3);
	shadow.setOffsetY(3);
	shadow.setColor(Color.BLACK);
	return shadow;
    }

    public abstract Image getTexture();

    public abstract Image getHoverTexture();

    public abstract Image getClickTexture();

    public abstract void onClick(MouseEvent mce);
}
