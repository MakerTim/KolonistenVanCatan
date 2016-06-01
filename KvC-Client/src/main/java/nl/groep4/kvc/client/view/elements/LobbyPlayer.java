package nl.groep4.kvc.client.view.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

public class LobbyPlayer extends StackPane {

    private static final Image IMAGE = new Image("img/menu/parchment.png");
    private static final Image HOVER_IMAGE = new Image("img/menu/parchment_hover.png");
    private static final Image PRESSED_IMAGE = new Image("img/menu/parchment_pressed.png");

    private Color color;
    private Text colorLabel;
    private Text usernameLabel;

    protected List<Runnable> clickHandlers = new ArrayList<>();

    public LobbyPlayer(Color color) {
	this.color = color;
	ImageView background = new ImageView(IMAGE);
	colorLabel = new Text();
	usernameLabel = new Text();
	colorLabel.setFill(color.getColor());
	usernameLabel.setFill(color.getColor());
	colorLabel.setStroke(javafx.scene.paint.Color.BLACK);
	usernameLabel.setStroke(javafx.scene.paint.Color.BLACK);
	colorLabel.setEffect(TexturedButton.getShadowEffect());
	usernameLabel.setEffect(TexturedButton.getShadowEffect());
	setOnMouseEntered(mouseEnter -> background.setImage(HOVER_IMAGE));
	setOnMouseExited(mouseLeave -> background.setImage(IMAGE));
	setOnMousePressed(mousePressed -> {
	    background.setImage(PRESSED_IMAGE);
	    for (Runnable click : clickHandlers) {
		click.run();
	    }
	});
	setOnMouseReleased(mouseRelease -> background.setImage(HOVER_IMAGE));
	Pane labels = new VBox();
	labels.getChildren().addAll(colorLabel, usernameLabel);
	getChildren().addAll(background, labels);
    }

    public void setFont(Font font) {
	colorLabel.setFont(font);
	usernameLabel.setFont(font);
    }

    public Color getColor() {
	return color;
    }

    public void updatePlayer(Player player) {
	usernameLabel.setText(player.getUsername());
    }

    public void registerClick(Runnable click) {
	clickHandlers.add(click);
    }
}
