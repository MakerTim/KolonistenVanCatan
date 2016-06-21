package nl.groep4.kvc.client.view.elements;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    public PlayerScore(Player player) {
	this.player = player;
	playerRes = new KvCText();
	playerRes.setFont(new Font(ViewMaster.FONT.getName(), 14));
    }

    public Player getPlayer() {
	return player;
    }

    public Pane getPlayerScorePane() {
	return playerScorePane;
    }

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
	    // TODO: makerluc
	}
	playerRes.setText(woodAmount + " " + brickAmount + " " + woolAmount + " " + wheatAmount + " " + oreAmount);

    }
}
