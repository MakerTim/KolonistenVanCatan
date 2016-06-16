package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class BuyPane extends Application implements PaneHolder {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);

    private MenuButton yes;
    private MenuButton no;
    private Text buy;

    @Override
    public void start(Stage primaryStage) throws Exception {

	StackPane buypane = new StackPane();
	VBox vbox = new VBox();
	HBox hbox = new HBox();

	yes = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.yes"));
	no = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.no"));
	buy = new Text(TranslationManager.translate("buycard.msg.buycard"));

	yes.setFont(ViewMaster.FONT);
	no.setFont(ViewMaster.FONT);
	buy.setFont(font);
	buy.setFill(Color.WHITE);
	buy.setStroke(Color.BLACK);

	hbox.setAlignment(Pos.CENTER);
	vbox.setAlignment(Pos.CENTER);

	Node background = SceneUtil.getGamePane();

	hbox.getChildren().addAll(yes, no);
	vbox.getChildren().addAll(buy, hbox);
	buypane.getChildren().addAll(background, vbox);

	Scene scene = new Scene(buypane);
	primaryStage.setScene(scene);
	primaryStage.show();

    }

    @Override
    public Pane getPane() {
	return new Pane();
    }

    @Override
    public void updateTranslation() {

    }

    public static void main(String[] args) {
	launch(args);
    }

}
