package nl.groep4.kvc.client.view.elements;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * InputField where only certain input is allowed
 * 
 * @version 1.0
 * @author Tim
 **/
public class MenuFilterdInputField extends MenuInputField {

    /**
     * Filters input data
     * 
     * @param regex
     */
    public MenuFilterdInputField(String regex) {
	super();
	registerCheck(regex);
    }

    /**
     * 
     * 
     * @param xPos
     * @param yPos
     * @param text
     * @param regex
     */
    public MenuFilterdInputField(int xPos, int yPos, String text, String regex) {
	super(xPos, yPos, text);
	registerCheck(regex);
    }

    private void registerCheck(String regex) {
	setOnKeyPressed(key -> {
	    if (key.getCode() == KeyCode.V && key.isControlDown()) {
		key.consume();
	    }
	});
	setOnKeyTyped(key -> {
	    if (!key.getCharacter().trim().equals("") && !key.getCharacter().matches(regex)) {
		key.consume();
		setBorderColor(Color.RED);
		SoundUtil.playError();
	    } else {
		setBorderColor(Color.BLACK);
	    }
	});
    }

}
