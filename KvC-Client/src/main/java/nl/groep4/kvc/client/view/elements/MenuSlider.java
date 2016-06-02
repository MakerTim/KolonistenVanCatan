package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.Slider;

public class MenuSlider extends Slider {

    public MenuSlider() {
	super();
	setStyle("");
    }

    public MenuSlider(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    public MenuSlider(int xPos, int yPos, double min, double max, double value) {
	this(xPos, yPos);
	setMin(min);
	setMax(max);
	setValue(value);
    }
}
