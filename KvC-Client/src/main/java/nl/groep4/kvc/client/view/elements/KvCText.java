package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;

public class KvCText extends Text {

    public KvCText() {
	setFont(ViewMaster.FONT);
	setFill(Color.WHITE);
	setStroke(Color.BLACK);
    }

    public KvCText(String text) {
	this();
	setText(text);
    }

    public KvCText(int x, int y, String text) {
	this(text);
	setLayoutX(x);
	setLayoutY(y);
    }

    public KvCText addShadow() {
	setEffect(TexturedButton.getShadowEffect());
	return this;
    }
}
