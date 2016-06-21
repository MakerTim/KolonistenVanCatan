package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;

/**
 * Default font layout for the application
 * 
 * @author Tim
 * @version 1.0
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
     * sets font into text
     * 
     * @param text
     *            the adjusted font
     */
    public KvCText(String text) {
	this();
	setText(text);
    }

    /**
     * 
     * 
     * @param x
     *            sets the value of the property layoutX
     * @param y
     *            sets the value of the property layoutY
     * @param text
     *            the adjusted font
     */
    public KvCText(int x, int y, String text) {
	this(text);
	setLayoutX(x);
	setLayoutY(y);
    }

    /**
     * adds shadow to the font
     * 
     * @return the shadow
     */
    public KvCText addShadow() {
	setEffect(TexturedButton.getShadowEffect());
	return this;
    }
}
