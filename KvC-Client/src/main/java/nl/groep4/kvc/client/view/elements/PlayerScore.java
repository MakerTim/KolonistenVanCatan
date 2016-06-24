package nl.groep4.kvc.client.view.elements;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;

/**
 * Updates score and stock.
 * 
 * @author Tim en Luc
 * @version 1.1
 */
public class PlayerScore {

    private static final Font TOP_FONT = new Font(ViewMaster.FONT.getName(), 24);
    private static final Font BOTTOM_FONT = new Font(ViewMaster.FONT.getName(), 15);

    private Player player;
    private HBox bottom;
    private Text usernameLabel;
    private Text scoreLabel;
    private Text brickLabel;
    private Text oreLabel;
    private Text woodLabel;
    private Text woolLabel;
    private Text wheatLabel;
    private Text cardLabel;
    private ImageView woodIcon;
    private ImageView woolIcon;
    private ImageView wheatIcon;
    private ImageView brickIcon;
    private ImageView oreIcon;
    private ImageView coinIcon;
    private ImageView cardIcon;
    private ImageView ridderIcon;
    private ImageView roadIcon;

    /**
     * Constructor for setting up object for PlayerScore.
     *
     * @param player
     *            The player.
     */
    public PlayerScore(Player player) {
	this.player = player;
	usernameLabel = new KvCText();
	brickLabel = new KvCText();
	oreLabel = new KvCText();
	woodLabel = new KvCText();
	woolLabel = new KvCText();
	wheatLabel = new KvCText();
	scoreLabel = new KvCText();
	cardLabel = new KvCText();

	brickLabel.setFill(Color.BISQUE);
	oreLabel.setFill(Color.BISQUE);
	woodLabel.setFill(Color.BISQUE);
	woolLabel.setFill(Color.BISQUE);
	wheatLabel.setFill(Color.BISQUE);
	scoreLabel.setFill(Color.BISQUE);
	cardLabel.setFill(Color.BISQUE);

	usernameLabel.setFont(TOP_FONT);
	brickLabel.setFont(BOTTOM_FONT);
	oreLabel.setFont(BOTTOM_FONT);
	woodLabel.setFont(BOTTOM_FONT);
	woolLabel.setFont(BOTTOM_FONT);
	wheatLabel.setFont(BOTTOM_FONT);
	scoreLabel.setFont(BOTTOM_FONT);
	cardLabel.setFont(BOTTOM_FONT);

	try {
	    usernameLabel.setText(player.getUsername());
	    usernameLabel.setFill(player.getColor().getColor());
	    updateResources(player.getResources());
	    updateStock(player.getCards());
	    updateScore(player.getPoints());
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	} catch (NullPointerException ex) {
	}
    }

    /**
     * Gets the resources element for each player in game.
     * 
     * @return VBox with player name and player resources.
     */
    public VBox getPane() {
	VBox playerScorePane = new VBox();
	HBox top = new HBox();
	HBox mittle = new HBox();
	bottom = new HBox();
	top.setAlignment(Pos.CENTER);
	mittle.setAlignment(Pos.CENTER);
	bottom.setAlignment(Pos.CENTER);

	woodIcon = getWoodIcon();
	oreIcon = getOreIcon();
	wheatIcon = getWheatIcon();
	woolIcon = getWoolIcon();
	brickIcon = getBrickIcon();
	coinIcon = getCoinIcon();
	cardIcon = getCardIcon();
	ridderIcon = getSwordIcon();
	roadIcon = getRoadIcon();

	top.getChildren().addAll(usernameLabel);
	mittle.getChildren().addAll(woodIcon, woodLabel, brickIcon, brickLabel, woolIcon, woolLabel, wheatIcon,
		wheatLabel, oreIcon, oreLabel);
	bottom.getChildren().addAll(coinIcon, scoreLabel, cardIcon, cardLabel);

	playerScorePane.getChildren().addAll(top, mittle, bottom);
	playerScorePane.setAlignment(Pos.CENTER);
	return playerScorePane;
    }

    /**
     * Getter for Player class.
     * 
     * @return The player in the object.
     */
    public Player getPlayer() {
	return player;
    }

    /**
     * Updates all resources.
     * 
     */
    public void updateTranslation() {
    }

    /**
     * Updates the resources within the object every time this method is called.
     * 
     * @param resources
     *            All the resources.
     */
    public void updateResources(EnumMap<Resource, Integer> resources) {
	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();
	    switch (res) {
	    case BRICK:
		brickLabel.setText(amount);
		break;
	    case ORE:
		oreLabel.setText(amount);
		break;
	    case WHEAT:
		wheatLabel.setText(amount);
		break;
	    case WOOD:
		woodLabel.setText(amount);
		break;
	    case WOOL:
		woolLabel.setText(amount);
		break;
	    }
	}
	try {
	    scoreLabel.setText(Integer.toString(player.getPoints()));
	    bottom.getChildren().remove(ridderIcon);
	    if (player.hasMostRidders()) {
		bottom.getChildren().add(ridderIcon);
	    }
	    bottom.getChildren().remove(roadIcon);
	    if (player.hasLongestRoad()) {
		bottom.getChildren().add(roadIcon);
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	} catch (NullPointerException ex) {
	}
    }

    /**
     * Updates the resource cards in stock.
     * 
     * @param cards
     *            Resource cards.
     */
    public void updateStock(List<Card> cards) {
	cardLabel.setText(Integer.toString(cards.size()));
	try {
	    scoreLabel.setText(Integer.toString(player.getPoints()));
	    if (player.hasMostRidders()) {
		bottom.getChildren().add(ridderIcon);
	    } else {
		bottom.getChildren().remove(ridderIcon);
	    }
	    if (player.hasLongestRoad()) {
		bottom.getChildren().add(roadIcon);
	    } else {
		bottom.getChildren().remove(roadIcon);
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	} catch (NullPointerException ex) {
	}
    }

    public void updateScore(int score) {
	scoreLabel.setText(Integer.toString(score));
    }

    public ImageView getRoadIcon() {
	return new ImageView("img/etc/road.png");
    }

    public ImageView getSwordIcon() {
	return new ImageView("img/etc/swords.png");
    }

    /**
     * Gets woodIcon.
     * 
     * @return A woodicon.
     */
    public ImageView getWoodIcon() {
	return new ImageView("img/etc/wood.png");
    }

    /**
     * Gets wool icon.
     * 
     * @return A woolIcon.
     */
    public ImageView getWoolIcon() {
	return new ImageView("/img/etc/wool.png");
    }

    /**
     * Gets wheat icon.
     * 
     * @return a wheatIcon.
     */
    public ImageView getWheatIcon() {
	return new ImageView("/img/etc/wheat.png");
    }

    /**
     * Gets brick icon.
     *
     * @return A brickIcon.
     */
    public ImageView getBrickIcon() {
	return new ImageView("/img/etc/brick.png");
    }

    public ImageView getCoinIcon() {
	return new ImageView("/img/etc/coin.png");
    }

    /**
     * Gets ore icon.
     * 
     * @return an OreIcon.
     */
    public ImageView getOreIcon() {
	return new ImageView("/img/etc/ore.png");
    }

    public ImageView getCardIcon() {
	return new ImageView("/img/etc/card.png");
    }

}
