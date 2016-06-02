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

    public MenuInputField() {
	setBorderColor(Color.BLACK);
    }

    public MenuInputField(int xPos, int yPos, String text) {
	this();
	if (text == null) {
	    text = "";
	}
	this.setLayoutX(xPos);
	this.setLayoutY(yPos);
	this.setText(text);
    }

    public void setBorderColor(Color color) {
	setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
	setBorder(new Border(new BorderStroke(null, null, color, null, null, null, BorderStrokeStyle.DASHED, null, null,
		null, null)));
	setPadding(Insets.EMPTY);
    }
}