package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class CreditsPane extends Application implements PaneHolder {
    Font font = new Font(ViewMaster.FONT.getName(), 40);

    Text credits;
    Text bachir;
    Text matthijs;
    Text Tim;
    Text Luc;
    Text Lisa;

    MenuButton back;

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	StackPane creditspane = new StackPane();
	VBox vbox = new VBox();

	credits = new Text(TranslationManager.translate("credits.text.credits"));
	bachir = new Text("Bachir Talbi");
	matthijs = new Text("Matthijs Mandjes");
	Tim = new Text("Tim Biesenbeek");
	Luc = new Text("Luc Runge");
	Lisa = new Text("Lisa Groenendijk");

	back = new MenuButton(425, 500, TranslationManager.translate("credits.button.back"));

	credits.setFont(ViewMaster.TITLE_FONT);
	credits.setFill(Color.WHITE);
	credits.setStroke(Color.BLACK);

	bachir.setFont(ViewMaster.FONT);
	matthijs.setFont(ViewMaster.FONT);
	Tim.setFont(ViewMaster.FONT);
	Luc.setFont(ViewMaster.FONT);
	Lisa.setFont(ViewMaster.FONT);

	bachir.setFill(nl.groep4.kvc.common.enumeration.Color.BROWN.getColor());
	matthijs.setFill(nl.groep4.kvc.common.enumeration.Color.BLUE.getColor());
	Tim.setFill(nl.groep4.kvc.common.enumeration.Color.ORANGE.getColor());
	Luc.setFill(nl.groep4.kvc.common.enumeration.Color.GREEN.getColor());
	Lisa.setFill(nl.groep4.kvc.common.enumeration.Color.RED.getColor());

	bachir.setStroke(Color.BLACK);
	matthijs.setStroke(Color.BLACK);
	Tim.setStroke(Color.BLACK);
	Luc.setStroke(Color.BLACK);
	Lisa.setStroke(Color.BLACK);

	vbox.setAlignment(Pos.CENTER);
	vbox.getChildren().addAll(credits, bachir, matthijs, Tim, Luc, Lisa, back);
	creditspane.getChildren().addAll(SceneUtil.getMenuBackground(), SceneUtil.getLobbyForeground(),
		SceneUtil.getMenuBrazier(), vbox);

	Scene scene = new Scene(creditspane, 1000, 700);
	primaryStage.setScene(scene);
	primaryStage.show();

    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

    public static void main(String[] args) {
	launch(args);
    }

}
