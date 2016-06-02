package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * InputField where a regulair expression determines if field is valid or not
 * 
 * @version 1.0
 * @author Tim
 **/
public class MenuMatchInputField extends MenuInputField {

    private String regex;

    public MenuMatchInputField(String regex) {
	super();
	this.regex = regex;
	registerCheck();
    }

    public MenuMatchInputField(int xPos, int yPos, String text, String regex) {
	super(xPos, yPos, text);
	this.regex = regex;
	registerCheck();
    }

    public String getRegex() {
	return this.regex;
    }

    private void registerCheck() {
	focusedProperty().addListener(change -> {
	    if (!isFocused() && !getText().matches(getRegex()) && !getText().isEmpty()) {
		requestFocus();
		setBorderColor(Color.RED);
		SoundUtil.playError();
	    } else {
		setBorderColor(Color.BLACK);
	    }
	});
    }

}
