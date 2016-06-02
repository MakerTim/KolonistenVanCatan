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
     * 
     * @param text
     */
    public TexturedButton(String text) {
	this();
	updateText(text);
    }

    /**
     * 
     * @param font
     */
    public void setFont(Font font) {
	label.setFont(font);
    }

    /**
     * 
     * @param text
     */
    public void updateText(String text) {
	this.label.setText(text);
    }

    public String getText() {
	return this.label.getText();
    }

    /**
     * 
     * @return
     */
    public static DropShadow getShadowEffect() {
	DropShadow shadow = new DropShadow();
	shadow.setOffsetX(3);
	shadow.setOffsetY(3);
	shadow.setColor(Color.BLACK);
	return shadow;
    }

    /**
     * 
     * @param click
     */
    public void registerClick(Runnable click) {
	clickHandlers.add(click);
    }

    /**
     * 
     * @return
     */
    public abstract Image getTexture();

    /**
     * 
     * @return
     */
    public abstract Image getHoverTexture();

    /**
     * 
     * @return
     */
    public abstract Image getClickTexture();
}
