package nl.groep4.kvc.client.view.elements;

import java.rmi.RemoteException;
import java.util.EnumMap;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Trade;

/**
 * Generates layout of trades which are added to TradePane
 * 
 * @author Matthijs
 * @version 1.0
 */
public class TradeEntry implements PaneHolder {

    private SceneMap scenemap;

    private HBox hboxtrades = new HBox();
    private HBox hboxgive = new HBox();
    private HBox hboxusername = new HBox();
    private VBox vbox = new VBox();

    private VBox trade = new VBox();
    private VBox wheat = new VBox();
    private VBox brick = new VBox();
    private VBox wool = new VBox();
    private VBox wood = new VBox();
    private VBox ore = new VBox();

    private Text give = new KvCText(TranslationManager.translate("trade.text.offer"));
    private Text receive = new KvCText(TranslationManager.translate("trade.text.search"));

    private Text username = new KvCText("The UserName");
    private Text giveWood = new KvCText("0");
    private Text giveWheat = new KvCText("0");
    private Text giveOre = new KvCText("0");
    private Text giveBrick = new KvCText("0");
    private Text giveWool = new KvCText("0");

    private Text receiveWood = new KvCText("0");
    private Text receiveWheat = new KvCText("0");
    private Text receiveOre = new KvCText("0");
    private Text receiveBrick = new KvCText("0");
    private Text receiveWool = new KvCText("0");

    private Trade theTrade;

    /**
     * current trade settings and scenemap
     * 
     * @param trade
     *            trade settings
     * @param scenemap
     *            scenemap settings
     */
    public TradeEntry(Trade trade, SceneMap scenemap) {
	this.theTrade = trade;
	this.scenemap = scenemap;
    }

    @Override
    public Pane getPane() {
	StackPane pane = new StackPane();

	trade.getChildren().addAll(give, receive);
	wheat.getChildren().addAll(giveWheat, receiveWheat);
	wood.getChildren().addAll(giveWood, receiveWood);
	wool.getChildren().addAll(giveWool, receiveWool);
	brick.getChildren().addAll(giveBrick, receiveBrick);
	ore.getChildren().addAll(giveOre, receiveOre);

	hboxusername.getChildren().addAll(username);
	hboxtrades.getChildren().addAll(trade, wood, brick, wool, wheat, ore);

	hboxusername.setSpacing(10);
	hboxtrades.setSpacing(55);
	hboxgive.setSpacing(10);

	vbox.getChildren().addAll(hboxusername, hboxgive, hboxtrades);
	pane.getChildren().add(vbox);
	pane.setMinWidth(460);
	pane.setMaxWidth(460);
	pane.setPadding(new Insets(10, 10, 10, 10));
	pane.setStyle(
		"-fx-background-color: rgba(255, 255, 255, 0.4); -fx-border-color: black; -fx-border-style: solid; -fx-border-radius: 10px; -fx-background-radius: 10px;");

	pane.setOnMouseClicked(klick -> onPaneClick());
	updateTranslation();
	return pane;
    }

    private void onPaneClick() {
	scenemap.getController().doTrade(theTrade.getID());
    }

    @Override
    public void updateTranslation() {
	try {
	    username.setText(theTrade.getPlayer().getUsername());
	    give.setText(TranslationManager.translate("trade.text.offer"));
	    receive.setText(TranslationManager.translate("trade.text.search"));

	    giveWood.setText(getResource(theTrade.getReward(), Resource.WOOD));
	    giveWheat.setText(getResource(theTrade.getReward(), Resource.WHEAT));
	    giveOre.setText(getResource(theTrade.getReward(), Resource.ORE));
	    giveBrick.setText(getResource(theTrade.getReward(), Resource.BRICK));
	    giveWool.setText(getResource(theTrade.getReward(), Resource.WOOL));

	    receiveWood.setText(getResource(theTrade.getRequest(), Resource.WOOD));
	    receiveWheat.setText(getResource(theTrade.getRequest(), Resource.WHEAT));
	    receiveOre.setText(getResource(theTrade.getRequest(), Resource.ORE));
	    receiveBrick.setText(getResource(theTrade.getRequest(), Resource.BRICK));
	    receiveWool.setText(getResource(theTrade.getRequest(), Resource.WOOL));
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	} catch (NullPointerException ex) {
	}
    }

    private String getResource(EnumMap<Resource, Integer> resources, Resource toGet) {
	if (resources.containsKey(toGet)) {
	    return Integer.toString(resources.get(toGet));
	}
	return "0";
    }

}
