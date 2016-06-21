package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;

/**
 * The pane with the credits.
 * 
 * @author Lisa
 *
 */
public class CreditsPane implements PaneHolder {
    private Font font = new Font(ViewMaster.FONT.getName(), 40);

    private SceneMap sceneMap;

    private Text credits = new KvCText(TranslationManager.translate("credits.text.credits"));
    private Text bachir = new Text("Bachir Talbi");
    private Text matthijs = new Text("Matthijs Mandjes");
    private Text Tim = new Text("Tim Biesenbeek");
    private Text Luc = new Text("Luc Runge");
    private Text Lisa = new Text("Lisa Groenendijk");

    private ImageView boardbackground;
    private ImageView board;

    private MenuButton back = new MenuButton(425, 500, TranslationManager.translate("credits.button.back"));

    /**
     * Sets up the CreditsPane
     * 
     * @param sceneMap
     */
    public CreditsPane(SceneMap sceneMap) {
	this.sceneMap = sceneMap;
    }

    @Override
    public Pane getPane() {

	StackPane creditspane = new StackPane();

	HBox hbox = new HBox();
	VBox vbox = new VBox();

	boardbackground = new ImageView("img/game/board_background.gif");
	board = new ImageView("img/game/board.png");

	back.setFont(ViewMaster.FONT);

	credits.setFont(ViewMaster.TITLE_FONT);
	credits.setFill(Color.WHITE);
	credits.setStroke(Color.BLACK);

	bachir.setFont(font);
	matthijs.setFont(font);
	Tim.setFont(font);
	Luc.setFont(font);
	Lisa.setFont(font);

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

	back.setPadding(new Insets(50, 0, 0, 0));

	hbox.setAlignment(Pos.CENTER);
	hbox.getChildren().add(back);

	vbox.setAlignment(Pos.CENTER);
	vbox.getChildren().addAll(credits, Tim, Lisa, matthijs, Luc, bachir, hbox);

	creditspane.getChildren().addAll(boardbackground, board, vbox);

	back.registerClick(() -> {
	    sceneMap.closeOverlay();
	});

	return creditspane;
    }

    @Override
    public void updateTranslation() {

	credits.setText(TranslationManager.translate("credits.text.credits"));
	back.updateText(TranslationManager.translate("credits.button.back"));
    }
}