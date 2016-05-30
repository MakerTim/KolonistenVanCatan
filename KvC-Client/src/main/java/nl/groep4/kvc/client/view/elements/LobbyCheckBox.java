package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.CheckBox;

/**
 * Wrapper for CheckBox
 * 
 * @version 1.0
 * @author Tim
 **/
public class LobbyCheckBox extends CheckBox {

    public LobbyCheckBox() {
	super();
    }

    public LobbyCheckBox(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    public LobbyCheckBox(int xPos, int yPos, boolean defaultSelected) {
	this(xPos, yPos);
	setSelected(defaultSelected);
    }

}
