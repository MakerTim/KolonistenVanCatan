package nl.groep4.kvc.client.view.elements;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.common.interfaces.Player;

public class TradeEntry extends Application implements PaneHolder {

    private StackPane pane = new StackPane();
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

    private Text give = new KvCText(TranslationManager.translate("trade.text.give"));
    private Text receive = new KvCText(TranslationManager.translate("trade.text.receive"));

    private Text username = new KvCText();
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
    private String us;
    private Player player;

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	// us = player.getUsername();
	us = "Matthijs";
	username.setText(us);

	trade.getChildren().addAll(give, receive);
	wheat.getChildren().addAll(giveWheat, receiveWheat);
	wood.getChildren().addAll(giveWood, receiveWood);
	wool.getChildren().addAll(giveWool, receiveWool);
	brick.getChildren().addAll(giveBrick, receiveBrick);
	ore.getChildren().addAll(giveOre, receiveOre);

	hboxusername.getChildren().addAll(username);
	hboxtrades.getChildren().addAll(trade, wheat, wood, wool, brick, ore);

	hboxusername.setSpacing(10);
	hboxtrades.setSpacing(10);
	hboxgive.setSpacing(10);

	vbox.getChildren().addAll(hboxusername, hboxgive, hboxtrades);
	pane.getChildren().add(vbox);

	Scene scene = new Scene(pane);
	primaryStage.setScene(scene);
	primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
	Application.launch(args);
    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

	username.setText(TranslationManager.translate("trade.text.username"));
	give.setText(TranslationManager.translate("trade.text.give"));
	receive.setText(TranslationManager.translate("trade.text.receive"));

	giveWood.setText(TranslationManager.translate("trade.text.givewood"));
	giveWheat.setText(TranslationManager.translate("trade.text.givewheat"));
	giveOre.setText(TranslationManager.translate("trade.text.givere"));
	giveBrick.setText(TranslationManager.translate("trade.text.givebrick"));
	giveWool.setText(TranslationManager.translate("trade.text.givewool"));

	receiveWood.setText(TranslationManager.translate("trade.text.receivewood"));
	receiveWheat.setText(TranslationManager.translate("trade.text.receivewheat"));
	receiveOre.setText(TranslationManager.translate("trade.text.receiveore"));
	receiveBrick.setText(TranslationManager.translate("trade.text.receivebrick"));
	receiveWool.setText(TranslationManager.translate("trade.text.receivewool"));

    }

}
