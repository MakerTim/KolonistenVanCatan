package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;

public class KvCText extends Text {

    public KvCText() {
	setFont(ViewMaster.FONT);
	setFill(Color.WHITE);
	setStroke(Color.BLACK);
	setEffect(TexturedButton.getShadowEffect());
    }

    public KvCText(String text) {
	this();
	setText(text);
    }

    public KvCText(String text, int x, int y) {
	this(text);
	setLayoutX(x);
	setLayoutY(y);
    }

}
