package nl.groep4.kvc.client.view.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    protected List<Runnable> clickHandlers = new ArrayList<>();

    /**
     * Generates button design
     */
    public TexturedButton() {
	ImageView background = new ImageView(getTexture());
	label = new Text();
	label.setFill(Color.WHITE);
	label.setStroke(Color.BLACK);
	label.setEffect(getShadowEffect());
	setOnMouseEntered(mouseEnter -> background.setImage(getHoverTexture()));
	setOnMouseExited(mouseLeave -> background.setImage(getTexture()));
	setOnMousePressed(mousePressed -> {
	    background.setImage(getClickTexture());
	    for (Runnable click : clickHandlers) {
		click.run();
	    }
	});
	setOnMouseReleased(mouseRelease -> background.setImage(getHoverTexture()));
	getChildren().addAll(background, label);
    }

    /**
     * Updates the text of the button
     * 
     * @param text
     *            Text of the button
     */
    public TexturedButton(String text) {
	this();
	updateText(text);
    }

    /**
     * Sets the font of the label
     * 
     * 
     * @param font
     *            Font of the label
     */
    public void setFont(Font font) {
	label.setFont(font);
    }

    /**
     * Updates the text of the label
     * 
     * @param text
     *            Text of the label
     */
    public void updateText(String text) {
	this.label.setText(text);
    }

    /**
     * Gets the text of the label
     * 
     * @return text of the label
     */
    public String getText() {
	return this.label.getText();
    }

    /**
     * generates a shadow effect with color black
     * 
     * @return settings for shadow
     * @return black shadow effect
     */
    public static DropShadow getShadowEffect() {
	DropShadow shadow = new DropShadow();
	shadow.setOffsetX(3);
	shadow.setOffsetY(3);
	shadow.setColor(Color.BLACK);
	return shadow;
    }

    /**
     * Registers the clicks in a List
     * 
     * @param click
     *            click that gets registers
     */
    public void registerClick(Runnable click) {
	clickHandlers.add(click);
    }

    public void setDiabled() {
	setMouseTransparent(true);
    }

    public void setEnabled() {
	setMouseTransparent(false);
    }

    /**
     * Gets texture of image
     */
    public abstract Image getTexture();

    /**
     * gets hover effect when hovering above image
     */
    public abstract Image getHoverTexture();

    /**
     * gets click effect when user clicks on image
     */
    public abstract Image getClickTexture();
}
