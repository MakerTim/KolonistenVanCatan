package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.Slider;

/**
 * 
 * @author Luc
 * @version 1.0
 */
public class MenuSlider extends Slider {

    /**
     * sets background image for slider
     */
    public MenuSlider() {
	super();
	setStyle(
		"-fx-background-image: url('img/etc/button.png'); -fx-padding: 20px; -fx-background-repeat: no-repeat;");
    }

    /**
     * 
     * @param xPos
     * @param yPos
     */
    public MenuSlider(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    /**
     * 
     * @param xPos
     * @param yPos
     * @param min
     * @param max
     * @param value
     */
    public MenuSlider(int xPos, int yPos, double min, double max, double value) {
	this(xPos, yPos);
	setMin(min);
	setMax(max);
	setValue(value);
    }
}
