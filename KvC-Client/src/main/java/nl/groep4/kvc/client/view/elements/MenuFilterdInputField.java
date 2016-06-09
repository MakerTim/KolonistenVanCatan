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
     *            input requirements
     */
    public MenuFilterdInputField(String regex) {
	super();
	registerCheck(regex);
    }

    /**
     * Places MenuFilterdInputField and checks regex
     * 
     * @param xPos
     *            the x-coordinate location of MenuFilteredInputField
     * @param yPos
     *            the y-coordinate location of MenuFilteredInputField
     * @param text
     *            the string
     * @param regex
     *            arguments that will be tested before going further
     */
    public MenuFilterdInputField(int xPos, int yPos, String text, String regex) {
	super(xPos, yPos, text);
	registerCheck(regex);
    }

    private void registerCheck(String regex) {
	setOnKeyPressed(key -> {
	    if (key.getCode() == KeyCode.V && key.isControlDown()
		    || key.getCode() == KeyCode.INSERT && key.isShiftDown()) {
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
