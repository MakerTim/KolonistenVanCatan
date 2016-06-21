package nl.groep4.kvc.client.view.elements;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;

public class PlayerScore {

    private Player player;
    private VBox playerScorePane;
    HBox cards;
    private Text username;
    private Text playerRes;
    private Text playerCards;
    private String brickAmount = "";
    private String oreAmount = "";
    private String woodAmount = "";
    private String woolAmount = "";
    private String wheatAmount = "";
    private ImageView woodIcon;
    private ImageView woolIcon;
    private ImageView wheatIcon;
    private ImageView brickIcon;
    private ImageView oreIcon;

    String woodLetter = "";
    String wheatLetter = "";
    String woolLetter = "";
    String oreLetter = "";

    /**
     * Gets the resources element for each player in game
     * 
     * @return VBox with player name and player resources
     */
    public VBox getPane() {
	playerScorePane = new VBox();
	cards = new HBox();
	cards.setAlignment(Pos.CENTER);
	getWoodIcon();
	getOreIcon();
	getWheatIcon();
	getWoolIcon();
	getBrickIcon();

	try {
	    username = new KvCText(player.getUsername());
	    username.setFill(player.getColor().getColor());
	    username.setFont(new Font(ViewMaster.FONT.getName(), 18));
	    username.setStroke(Color.BLACK);
	    Map<Resource, Integer> emptylist = new HashMap<>();
	    for (Resource resource : Resource.values()) {
		emptylist.put(resource, 0);
	    }
	    updateResources(new EnumMap<>(emptylist));
	    updateStock(player, new ArrayList<>());
	} catch (RemoteException e) {
	    e.printStackTrace();
	}

	cards.getChildren().addAll(playerRes, playerCards);
	playerScorePane.getChildren().addAll(username, cards);
	playerScorePane.setAlignment(Pos.CENTER);
	return playerScorePane;
    }

    /**
     * contructor for setting up object for PlayerScore
     * 
     */
    public PlayerScore(Player player) {
	this.player = player;
	woodIcon = new ImageView();
	woolIcon = new ImageView();
	wheatIcon = new ImageView();
	brickIcon = new ImageView();
	oreIcon = new ImageView();
	playerRes = new KvCText();
	playerCards = new KvCText(" ");
	playerCards.setFont(new Font(ViewMaster.FONT.getName(), 24));
	playerCards.setFill(Color.BISQUE);
	playerRes.setFont(new Font(ViewMaster.FONT.getName(), 15));
	woodLetter = (TranslationManager.translate("game.score.woodletter"));
	brickLetter = (TranslationManager.translate("game.score.brickletter"));
	wheatLetter = (TranslationManager.translate("game.score.wheatletter"));
	woolLetter = (TranslationManager.translate("game.score.woolletter"));
	oreLetter = (TranslationManager.translate("game.score.oreletter"));

    }

    String brickLetter = "";

    /**
     * getter for Player class
     * 
     * @return the player in the object
     */
    public Player getPlayer() {
	return player;
    }

    public void updateTranslation() {
	woodLetter = (TranslationManager.translate("game.score.woodletter"));
	brickLetter = (TranslationManager.translate("game.score.brickletter"));
	wheatLetter = (TranslationManager.translate("game.score.wheatletter"));
	woolLetter = (TranslationManager.translate("game.score.woolletter"));
	oreLetter = (TranslationManager.translate("game.score.oreletter"));
    }

    /**
     * updates the resources within the object everytime this method is called
     * 
     * @param resources
     *            all the resources
     */
    public void updateResources(EnumMap<Resource, Integer> resources) {

	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();
	    switch (res) {
	    case BRICK:
		brickAmount = amount;
		break;
	    case ORE:
		oreAmount = amount;
		break;
	    case WHEAT:
		wheatAmount = amount;
		break;
	    case WOOD:
		woodAmount = amount;
		break;
	    case WOOL:
		woolAmount = amount;
		break;

	    }
	}
	cards.getChildren().addAll(woodIcon, new KvCText(woodAmount + " "), brickIcon, new KvCText(brickAmount + " "),
		woolIcon, new KvCText(woolAmount + " "), wheatIcon, new KvCText(wheatAmount + " "), oreIcon,
		new KvCText(oreAmount));
    }

    public void updateStock(Player pl, List<Card> cards) throws RemoteException {
	playerCards.setText(" " + cards.size());
    }

    public Node getWoodIcon() {
	woodIcon = new ImageView("img/etc/wood.png");
	return woodIcon;
    }

    public Node getWoolIcon() {
	woolIcon = new ImageView("/img/etc/wool.png");
	return woolIcon;
    }

    public Node getWheatIcon() {
	wheatIcon = new ImageView("/img/etc/wheat.png");
	return wheatIcon;
    }

    public Node getBrickIcon() {
	brickIcon = new ImageView("/img/etc/brick.png");
	return brickIcon;
    }

    public Node getOreIcon() {
	oreIcon = new ImageView("/img/etc/ore.png");
	return oreIcon;
    }

}
