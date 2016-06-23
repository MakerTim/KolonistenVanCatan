package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;

/**
 * Default font layout for the application
 * 
 * @author Tim
 * @version 1.1
 */
public class KvCText extends Text {

    /**
     * Font settings to be displayed
     */
    public KvCText() {
	setFont(ViewMaster.FONT);
	setFill(Color.WHITE);
	setStroke(Color.BLACK);
    }

    /**
     * Sets font into text.
     * 
     * @param text
     *            The adjusted font.
     */
    public KvCText(String text) {
	this();
	setText(text);
    }

    /**
     * Is a text that will be used in the game with the right font and color.
     * 
     * @param x
     *            Sets the value of the property layoutX.
     * @param y
     *            Sets the value of the property layoutY.
     * @param text
     *            The adjusted font.
     */
    public KvCText(int x, int y, String text) {
	this(text);
	setLayoutX(x);
	setLayoutY(y);
    }

    /**
     * Adds shadow to the font.
     * 
     * @return The shadow.
     */
    public KvCText addShadow() {
	setEffect(TexturedButton.getShadowEffect());
	return this;
    }
}
