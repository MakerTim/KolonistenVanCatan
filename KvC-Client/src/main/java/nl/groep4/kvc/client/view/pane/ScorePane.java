package nl.groep4.kvc.client.view.pane;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.PlayerScore;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdatePlayerOrder;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class ScorePane implements PaneHolder, UpdateStock, UpdatePlayerOrder {

    StackPane scorePane;
    VBox playersBox;
    VBox content;
    ImageView banner;
    ImageView closedBanner;
    Text title;
    Text scoreBrick;
    Text scoreWood;
    Text scoreOre;
    Text scoreWool;
    Text scoreWheat;

    Pane playerSlot;
    Text playerName;
    Text playerScore;

    ArrayList<PlayerScore> scores;

    @Override
    public Pane getPane() {
	scores = new ArrayList<>();
	scorePane = new StackPane();
	playersBox = new VBox();
	content = new VBox();
	title = new Text("Title");
	scorePane.getChildren().addAll(getClosedBanner());
	title.setFont(ViewMaster.FONT);
	title.setFill(Color.WHITE);
	title.setStroke(Color.BLACK);
	content.getChildren().addAll(title);
	content.setAlignment(Pos.TOP_CENTER);

	scorePane.setOnMouseEntered(e -> clickOpen());
	scorePane.setOnMouseExited(e -> clickClose());
	scorePane.setAlignment(Pos.TOP_CENTER);
	return scorePane;
    }

    /**
     * Opens the banner with player resources by adding children to the pane
     * 
     */
    public void clickOpen() {
	scorePane.getChildren().addAll(getBanner(), content);
	scorePane.getChildren().remove(closedBanner);
    }

    /**
     * Closed the banner with player resources by removing children from the
     * pane
     * 
     */
    public void clickClose() {
	scorePane.getChildren().add(getClosedBanner());
	scorePane.getChildren().removeAll(banner, content);
    }

    /**
     * Gets the banner texture
     * 
     * @return image of the banner in the game
     */
    public Node getBanner() {
	banner = new ImageView("img/game/banner.png");
	banner.setFitHeight(380);
	banner.setFitWidth(240);
	return banner;
    }

    /**
     * Gets the closed banner texture
     * 
     * @return image of the closed banner in the game
     */
    public Node getClosedBanner() {
	closedBanner = new ImageView("img/game/banner_closed.png");
	closedBanner.setFitHeight(120);
	closedBanner.setFitWidth(240);
	return closedBanner;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

    @Override
    public void updateStock(Player pl, EnumMap<Resource, Integer> resources) {
	for (PlayerScore playerScore : scores) {
	    if (pl == null || playerScore.getPlayer().equals(pl)) {
		playerScore.updateResources(resources);
	    }
	}
    }

    @Override
    public void updateStock(Player pl, List<Card> cards) {
	for (Card card : cards) {

	}
    }

    @Override
    public void updatePlayerOrder(List<Player> order) {
	content.getChildren().clear();
	for (Player player : order) {
	    PlayerScore ps = new PlayerScore(player);
	    scores.add(ps);
	    content.getChildren().add(ps.getPane());
	}
    }
}
