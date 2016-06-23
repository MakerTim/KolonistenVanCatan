package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.CheckBox;

/**
 * Wrapper for CheckBox.
 * 
 * @version 1.1
 * @author Tim
 **/
public class MenuCheckBox extends CheckBox {

    /**
     * Inherit everything from CheckBox.
     * 
     */
    public MenuCheckBox() {
	super();
    }

    /**
     * Places MenuCheckBox to location.
     * 
     * @param xPos
     *            The x-coordinate position of MenuCheckBox.
     * @param yPos
     *            The y-coordinate position of MenuCheckBox.
     */
    public MenuCheckBox(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    /**
     * Selects default selection.
     * 
     * @param xPos
     *            The x-coordinate position of MenuCheckBox.
     * @param yPos
     *            The y-coordinate position of MenuCheckBox.
     * @param defaultSelected
     *            Selects standard settings.
     */
    public MenuCheckBox(int xPos, int yPos, boolean defaultSelected) {
	this(xPos, yPos);
	setSelected(defaultSelected);
    }

}
