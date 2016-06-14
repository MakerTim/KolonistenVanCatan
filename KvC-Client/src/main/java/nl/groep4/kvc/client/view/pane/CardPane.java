package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class CardPane implements PaneHolder {

    // TODO: Bachir, vertalingen updaten werkt niet
    // TODO: Kosten van kaart hier laten zien

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	MenuButton yes = new MenuButton(425, 500, TranslationManager.translate("decision.yes"));
	MenuButton no = new MenuButton(425, 500, TranslationManager.translate("decision.no"));

	StackPane cardPane = new StackPane();
	cardPane.setAlignment(Pos.CENTER);

	HBox hbox = new HBox(3);
	hbox.setPrefWidth(300);
	hbox.setPrefHeight(50);
	hbox.setAlignment(Pos.CENTER);

	Text empty = new Text("");
	Text empty1 = new Text("");
	Text empty2 = new Text("");
	Text empty3 = new Text("");

	VBox vbox = new VBox(3);
	vbox.setPrefWidth(1366);
	vbox.setPrefHeight(768);
	vbox.setAlignment(Pos.CENTER);

	Text msg = new Text("msg.buycard");

	msg.setFill(Color.WHITE);
	msg.setStroke(Color.BLACK);
	msg.setFont(ViewMaster.FONT);
	yes.setFont(ViewMaster.FONT);
	no.setFont(ViewMaster.FONT);

	hbox.getChildren().addAll(yes, no);
	vbox.getChildren().addAll(msg, empty, empty1, empty2, empty3, hbox);
	cardPane.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return null;
    }

    @SuppressWarnings("unused")
    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

	MenuButton yes = new MenuButton(425, 500, TranslationManager.translate("decision.yes"));
	MenuButton no = new MenuButton(425, 500, TranslationManager.translate("decision.no"));
    }
}