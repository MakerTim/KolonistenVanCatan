package nl.groep4.kvc.client.view.elements;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * InputField with a default style to fit in the lobby
 * 
 * @version 1.0
 * @author Tim
 **/
public class MenuInputField extends TextField {

    /**
     * sets border color to black
     */
    public MenuInputField() {
	setBorderColor(Color.BLACK);
    }

    /**
     * Places MenuInputField
     * 
     * @param xPos
     *            the x-coordinate location of MenuInputField
     * @param yPos
     *            the y-coordinate location of MenuInputField
     * @param text
     *            the string
     */
    public MenuInputField(int xPos, int yPos, String text) {
	this();
	if (text == null) {
	    text = "";
	}
	this.setLayoutX(xPos);
	this.setLayoutY(yPos);
	this.setText(text);
    }

    /**
     * sets all border settings
     * 
     * @param color
     *            the color of the border
     */
    public void setBorderColor(Color color) {
	setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
	setBorder(new Border(new BorderStroke(null, null, color, null, null, null, BorderStrokeStyle.DASHED, null, null,
		null, null)));
	setPadding(Insets.EMPTY);
    }
}
