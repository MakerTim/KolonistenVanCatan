package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.Slider;

/**
 * Design and settings of the MenuSlider
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
     * Places the MenuSlider
     * 
     * @param xPos
     *            the x-coordinate location of the MenuSlider
     * @param yPos
     *            the y-coordinate location of the MenuSlider
     */
    public MenuSlider(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    /**
     * the MenuSlider settings such as the range
     * 
     * @param xPos
     *            the x-coordinate location of the MenuSlider
     * @param yPos
     *            the y-coordinate location of the MenuSlider
     * @param min
     *            the minimal number which the slider can select
     * @param max
     *            the maximal number which the slider can select
     * @param value
     *            the value that the slider selects
     */
    public MenuSlider(int xPos, int yPos, double min, double max, double value) {
	this(xPos, yPos);
	setMin(min);
	setMax(max);
	setValue(value);
    }
}
