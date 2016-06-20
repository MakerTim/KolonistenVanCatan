package nl.groep4.kvc.client.view.elements;

import java.rmi.RemoteException;
import java.util.EnumMap;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;

public class PlayerScore {

    private Player player;
    private VBox playerScorePane;
    private Text username;
    private Text playerRes;

    public VBox getPane(Player player) {
	playerScorePane = new VBox();

	try {
	    username = new KvCText(player.getUsername());
	    username.setFill(player.getColor().getColor());
	    username.setStroke(Color.BLACK);
	    String res = "";
	    for (int i = 0; i < Resource.values().length; i++) {
		res += 0 + " ";
	    }
	    ;
	    playerRes = new Text(res);

	} catch (RemoteException e) {
	    e.printStackTrace();
	}
	playerScorePane.getChildren().addAll(username, playerRes);
	playerScorePane.setAlignment(Pos.CENTER);
	return playerScorePane;
    }

    public PlayerScore(Player player) {
	this.player = player;
    }

    public Player getPlayer() {
	return player;
    }

    public Pane getPlayerScorePane() {
	return playerScorePane;
    }

    public void updateResources(EnumMap<Resource, Integer> resources) {

    }
}
