package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdatePlayerOrder;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class ScorePane implements PaneHolder, UpdateStock, UpdatePlayerOrder {

    StackPane scorePane;
    VBox players;
    VBox content;
    ImageView banner;
    ImageView closedBanner;
    Text title;
    Text firstSlot;
    Text secondSlot;
    Text thirdSlot;
    Text fourthSlot;
    Text fifthSlot;
    Text sixthSlot;

    @Override
    public Pane getPane() {
	scorePane = new StackPane();
	players = new VBox();
	content = new VBox();
	title = new Text("Title");
	firstSlot = new Text("player 1\n1 0 1 2 0");
	firstSlot.setFill(Color.WHITE);
	secondSlot = new Text("player 2\n1 0 1 2 0");
	secondSlot.setFill(Color.WHITE);
	thirdSlot = new Text("player 3\n1 0 1 2 0");
	thirdSlot.setFill(Color.WHITE);
	fourthSlot = new Text("player 4\n1 0 1 2 0");
	fourthSlot.setFill(Color.WHITE);
	fifthSlot = new Text("player 5\n1 0 1 2 0");
	fifthSlot.setFill(Color.WHITE);
	sixthSlot = new Text("player 6\n1 0 1 2 0");
	sixthSlot.setFill(Color.WHITE);
	title.setFont(ViewMaster.FONT);
	title.setFill(Color.WHITE);
	title.setStroke(Color.BLACK);

	content.getChildren().addAll(title, players, firstSlot, secondSlot, thirdSlot, fourthSlot, fifthSlot,
		sixthSlot);
	content.setAlignment(Pos.TOP_CENTER);

	scorePane.getChildren().addAll(getClosedBanner());

	scorePane.setOnMouseEntered(e -> clickOpen());
	scorePane.setOnMouseExited(e -> clickClose());
	scorePane.setAlignment(Pos.TOP_CENTER);
	return scorePane;
    }

    public void clickOpen() {
	scorePane.getChildren().addAll(getBanner(), content);
	scorePane.getChildren().remove(closedBanner);
    }

    public void clickClose() {
	scorePane.getChildren().add(getClosedBanner());
	scorePane.getChildren().removeAll(banner, content);
    }

    public Node getBanner() {
	banner = new ImageView("img/game/banner.png");
	banner.setFitHeight(398);
	banner.setFitWidth(240);
	return banner;
    }

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
    public void updateStock(Player pl, EnumMap<Resource, Integer> resources) throws RemoteException {
	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amnt = resource.getValue().toString();
	    switch (res) {
	    case BRICK:
		break;
	    case ORE:
		break;
	    case WHEAT:
		break;
	    case WOOD:
		break;
	    case WOOL:
		break;
	    }

	}
    }

    @Override
    public void updateStock(Player pl, List<Card> cards) throws RemoteException {
	// TODO Auto-generated method stub

    }

    @Override
    public void updatePlayerOrder(List<Player> order) throws RemoteException {

    }
}
