package nl.groep4.kvc.common.enumeration;

public enum Color {

    BLUE(0, 0, 255), BROWN(127, 0, 0), GREEN(0, 255, 0), ORANGE(255, 108, 0), RED(255, 0, 0), WHITE(255, 255, 255);

    private int red;
    private int blue;
    private int green;

    private Color(int red, int green, int blue) {
	this.red = red;
	this.blue = blue;
	this.green = green;
    }

    public javafx.scene.paint.Color getColor() {
	return new javafx.scene.paint.Color(red, green, blue, 0);
    }

}
