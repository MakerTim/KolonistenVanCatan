package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.CheckBox;

/**
 * Wrapper for CheckBox
 * 
 * @version 1.0
 * @author Tim
 **/
public class MenuCheckBox extends CheckBox {

    /**
     * Inherit everything from CheckBox
     */
    public MenuCheckBox() {
	super();
    }

    /**
     * Places MenuCheckBox to location
     * 
     * @param xPos
     *            the x-coordinate position of MenuCheckBox
     * @param yPos
     *            the y-coordinate position of MenuCheckBox
     */
    public MenuCheckBox(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    /**
     * Selects default selection
     * 
     * @param xPos
     *            the x-coordinate position of MenuCheckBox
     * @param yPos
     *            the y-coordinate position of MenuCheckBox
     * @param defaultSelected
     *            selects standard settings
     */
    public MenuCheckBox(int xPos, int yPos, boolean defaultSelected) {
	this(xPos, yPos);
	setSelected(defaultSelected);
    }

}
