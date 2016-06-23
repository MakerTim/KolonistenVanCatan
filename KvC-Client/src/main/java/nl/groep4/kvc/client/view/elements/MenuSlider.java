package nl.groep4.kvc.client.view.elements;

import javafx.scene.control.Slider;

/**
 * Design and settings of the MenuSlider.
 * 
 * @author Luc
 * @version 1.1
 */
public class MenuSlider extends Slider {

    /**
     * Sets background image for slider.
     * 
     */
    public MenuSlider() {
	super();
	setStyle(
		"-fx-background-image: url('img/etc/button.png'); -fx-padding: 20px; -fx-background-repeat: no-repeat;");
    }

    /**
     * Places the MenuSlider.
     * 
     * @param xPos
     *            The x-coordinate location of the MenuSlider.
     * @param yPos
     *            The y-coordinate location of the MenuSlider.
     */
    public MenuSlider(int xPos, int yPos) {
	this();
	setLayoutX(xPos);
	setLayoutY(yPos);
    }

    /**
     * the MenuSlider settings such as the range.
     * 
     * @param xPos
     *            The x-coordinate location of the MenuSlider.
     * @param yPos
     *            The y-coordinate location of the MenuSlider.
     * @param min
     *            The minimal number which the slider can select.
     * @param max
     *            The maximal number which the slider can select.
     * @param value
     *            The value that the slider selects.
     */
    public MenuSlider(int xPos, int yPos, double min, double max, double value) {
	this(xPos, yPos);
	setMin(min);
	setMax(max);
	setValue(value);
    }
}
