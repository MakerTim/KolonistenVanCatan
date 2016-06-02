package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.CheckBox;

/**
 * Wrapper for CheckBox
 * 
 * @version 1.0
 * @author Tim
 **/
public class MenuCheckBox extends CheckBox {

    public MenuCheckBox() {
	super();
    }

    public MenuCheckBox(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    public MenuCheckBox(int xPos, int yPos, boolean defaultSelected) {
	this(xPos, yPos);
	setSelected(defaultSelected);
    }

}