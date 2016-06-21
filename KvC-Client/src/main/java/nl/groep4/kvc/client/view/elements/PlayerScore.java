package nl.groep4.kvc.client.view.elements;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;

public class PlayerScore {

    private Player player;
    private VBox playerScorePane;
    private Text username;
    private Text playerRes;
    String playerResources = "Init fase";
    private String brickAmount = "";
    private String oreAmount = "";
    private String woodAmount = "";
    private String woolAmount = "";
    private String wheatAmount = "";
    String woodLetter = "";
    String brickLetter = "";
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

	try {
	    username = new KvCText(player.getUsername());
	    username.setFill(player.getColor().getColor());
	    username.setStroke(Color.BLACK);
	    Map<Resource, Integer> emptylist = new HashMap<>();
	    for (Resource resource : Resource.values()) {
		emptylist.put(resource, 0);
	    }
	    updateResources(new EnumMap<>(emptylist));

	} catch (RemoteException e) {
	    e.printStackTrace();
	}
	playerScorePane.getChildren().addAll(username, playerRes);
	playerScorePane.setAlignment(Pos.CENTER);
	return playerScorePane;
    }

    /**
     * contructor for setting up object for PlayerScore
     * 
     */
    public PlayerScore(Player player) {
	this.player = player;
	playerRes = new KvCText();
	playerRes.setFont(new Font(ViewMaster.FONT.getName(), 14));
	woodLetter = (TranslationManager.translate("game.score.woodletter"));
	brickLetter = (TranslationManager.translate("game.score.brickletter"));
	wheatLetter = (TranslationManager.translate("game.score.wheatletter"));
	woolLetter = (TranslationManager.translate("game.score.woolletter"));
	oreLetter = (TranslationManager.translate("game.score.oreletter"));
    }

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
	playerRes.setText(woodLetter + " " + woodAmount + " " + brickLetter + " " + brickAmount + " " + woolLetter + " "
		+ woolAmount + " " + wheatLetter + " " + wheatAmount + " " + oreLetter + " " + oreAmount);

    }
}
