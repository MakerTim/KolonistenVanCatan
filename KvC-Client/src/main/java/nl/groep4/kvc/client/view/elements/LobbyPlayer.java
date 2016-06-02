package nl.groep4.kvc.client.view.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

public class LobbyPlayer extends StackPane {

    private static final Image IMAGE = new Image("img/menu/parchment.png");
    private static final Image HOVER_IMAGE = new Image("img/menu/parchment_hover.png");
    private static final Image PRESSED_IMAGE = new Image("img/menu/parchment_pressed.png");
    private static final String EMPTY = "empty";

    private Color color;
    private Text colorLabel;
    private Text usernameLabel;

    protected List<Runnable> clickHandlers = new ArrayList<>();

    public LobbyPlayer(Color color) {
	VBox tussenPane = new VBox();
	tussenPane.setAlignment(Pos.CENTER);
	this.color = color;
	ImageView background = new ImageView(IMAGE);
	colorLabel = new Text(color.name());
	usernameLabel = new Text(EMPTY);
	usernameLabel.setTextAlignment(TextAlignment.CENTER);
	colorLabel.setFill(color.getColor());
	colorLabel.setTextAlignment(TextAlignment.CENTER);
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
	tussenPane.getChildren().addAll(usernameLabel, colorLabel);
	getChildren().addAll(background, tussenPane);
    }

    public void setFont(Font font) {
	colorLabel.setFont(font);
	usernameLabel.setFont(font);
    }

    public Color getColor() {
	return color;
    }

    public void updatePlayer(Player player) {
	if (player == null) {
	    usernameLabel.setText(EMPTY);
	} else {
	    usernameLabel.setText(player.getUsername());
	}
    }

    public void registerClick(Runnable click) {
	clickHandlers.add(click);
    }
}
