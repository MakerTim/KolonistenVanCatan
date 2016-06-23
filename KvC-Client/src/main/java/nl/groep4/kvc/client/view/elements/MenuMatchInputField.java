package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * InputField where a regular expression determines when field is valid or not.
 * 
 * @author Tim
 * @version 1.1
 **/
public class MenuMatchInputField extends MenuInputField {

    private String regex;

    /**
     * Inherit everything from MenuInputField and calls registerCheck().
     * 
     * @param regex
     *            Contains arguments to prevent wrong input.
     */
    public MenuMatchInputField(String regex) {
	super();
	this.regex = regex;
	registerCheck();
    }

    /**
     * Inherit xPos, yPos and text from MenuInputField and calls
     * registerCheck().
     * 
     * @param xPos
     *            The x-coordinate location of MenuMatchInputField.
     * @param yPos
     *            The y-coordinate location of MenuMatchInputField.
     * @param text
     *            The string to display.
     * @param regex
     *            Contains arguments to prevent wrong input.
     */
    public MenuMatchInputField(int xPos, int yPos, String text, String regex) {
	super(xPos, yPos, text);
	this.regex = regex;
	registerCheck();
    }

    /**
     * Contains string with arguments for inputfields.
     * 
     * @return Regex which has arguments for inputfields.
     */
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
