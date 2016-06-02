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
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

/**
 * 
 * @author Tim
 * @version 1.0
 */
public class LobbyPlayer extends StackPane {

    private static final Image IMAGE = new Image("img/menu/parchment.png");
    private static final Image HOVER_IMAGE = new Image("img/menu/parchment_hover.png");
    private static final Image PRESSED_IMAGE = new Image("img/menu/parchment_pressed.png");
    private static final String EMPTY = "lobby.parchment.empty";

    private Color color;
    private Text colorLabel;
    private Text usernameLabel;

    protected List<Runnable> clickHandlers = new ArrayList<>();

    /**
     * 
     * @param color
     */
    public LobbyPlayer(Color color) {
	VBox tussenPane = new VBox();
	tussenPane.setAlignment(Pos.CENTER);
	this.color = color;
	ImageView background = new ImageView(IMAGE);
	colorLabel = new Text(TranslationManager.translate("lobby.parchment.color." + color.name().toLowerCase()));
	usernameLabel = new Text(TranslationManager.translate(EMPTY));
	usernameLabel.setTextAlignment(TextAlignment.CENTER);
	colorLabel.setFill(color.getColor());
	colorLabel.setTextAlignment(TextAlignment.CENTER);
	usernameLabel.setFill(color.getColor());
	colorLabel.setStroke(color.getColor());
	usernameLabel.setStroke(color.getColor());
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

    /**
     * 
     * @param font
     */
    public void setFont(Font font) {
	colorLabel.setFont(font);
	usernameLabel.setFont(font);
    }

    /**
     * 
     * @return
     */
    public Color getColor() {
	return color;
    }

    /**
     * 
     * @param player
     */
    public void updatePlayer(Player player) {
	if (player == null) {
	    usernameLabel.setText(TranslationManager.translate(EMPTY));
	} else {
	    usernameLabel.setText(player.getUsername());
	}
    }

    /**
     * 
     * @param click
     */
    public void registerClick(Runnable click) {
	clickHandlers.add(click);
    }
}
