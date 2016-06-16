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
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Player;

/**
 * Handles design of the lobby and click events
 * 
 * @author Tim
 * @version 1.0
 */
public class ColorScroll extends StackPane {

    private static final Image IMAGE = new Image("img/menu/parchment.png");
    private static final Image HOVER_IMAGE = new Image("img/menu/parchment_hover.png");
    private static final Image PRESSED_IMAGE = new Image("img/menu/parchment_pressed.png");
    private static final String EMPTY = "lobby.parchment.empty";

    private Color color;
    private Text colorLabel;
    private Text usernameLabel;
    private Text pingLabel;
    private Player player;

    protected List<Runnable> clickHandlers = new ArrayList<>();

    /**
     * Handles when mouse clicks on the parchment image
     * 
     * @param color
     *            reference to change or add color to a player
     */
    public ColorScroll(Color color) {
	VBox tussenPane = new VBox();
	tussenPane.setAlignment(Pos.CENTER);
	this.color = color;
	ImageView background = new ImageView(IMAGE);
	colorLabel = new Text(TranslationManager.translate("lobby.parchment.color." + color.name().toLowerCase()));
	usernameLabel = new Text(TranslationManager.translate(EMPTY));
	pingLabel = new Text();
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
	tussenPane.getChildren().addAll(usernameLabel, colorLabel, pingLabel);
	getChildren().addAll(background, tussenPane);
    }

    /**
     * sets font for colorLabel and usernameLabel
     * 
     * @param font
     *            a letter type for labels
     */
    public void setFont(Font font) {
	colorLabel.setFont(font);
	usernameLabel.setFont(font);
    }

    /**
     * gets color
     * 
     * @return color type
     */
    public Color getColor() {
	return color;
    }

    /**
     * Updates labels when user chooses another parchment, also translates
     * parchment text into given language
     * 
     * @param player
     *            name of the user
     */
    public void updatePlayer(Player player) {
	try {
	    this.player = player;
	    usernameLabel.setText(player.getUsername());
	} catch (Exception ex) {
	    usernameLabel.setText(TranslationManager.translate(EMPTY));
	}
	pingLabel.setText("");
    }

    /**
     * Check if player clicks another parchment
     * 
     * @param click
     *            click that gets registers
     */
    public void registerClick(Runnable click) {
	clickHandlers.add(click);
    }

    /**
     * Updates label text when user changes parchment
     */
    public void updateTranslation() {
	colorLabel.setText(TranslationManager.translate("lobby.parchment.color." + color.name().toLowerCase()));
    }

    public Player getPlayer() {
	return player;
    }

    public void setPing(long pong) {
	if (pong < 0) {
	    pingLabel.setText("");
	} else {
	    pingLabel.setText(Long.toString(pong) + " ms");
	}
    }
}
