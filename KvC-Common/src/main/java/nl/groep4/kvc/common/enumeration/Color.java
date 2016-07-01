package nl.groep4.kvc.common.enumeration;

/**
 * All color codes.
 * 
 * @version 1.1
 * @author Tim
 */
public enum Color {

    BLUE(0, 0, 255), BROWN(106, 60, 1), GREEN(38, 127, 0), ORANGE(255, 85, 0), RED(255, 0, 0), WHITE(255, 255, 255);

    private int red;
    private int blue;
    private int green;

    private Color(int red, int green, int blue) {
	this.red = red;
	this.blue = blue;
	this.green = green;
    }

    /**
     * Returns color back to javafx defined color.
     * 
     * @return Retrieves color.
     */
    public javafx.scene.paint.Color getColor() {
	return new javafx.scene.paint.Color(red / 255D, green / 255D, blue / 255D, 1);
    }

}
