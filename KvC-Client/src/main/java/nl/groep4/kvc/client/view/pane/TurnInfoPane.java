package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.TexturedButton;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateRound;

/**
 * The pane that gives some info on this turn
 * 
 * @author Tim
 * @version 1.0
 */
public class TurnInfoPane implements PaneHolder, UpdateRound {

    private final Font font = new Font(ViewMaster.FONT.getName(), 16);
    private Player lastTurn;
    private TurnState state;

    private Text whoText;
    private Text whatText;

    @Override
    public Pane getPane() {
	StackPane layers = new StackPane();
	ImageView imageView = new ImageView("img/etc/parchment.png");
	VBox texts = new VBox();
	whoText = new Text();
	whatText = new Text();
	whoText.setFont(font);
	whatText.setFont(font);
	whoText.setStroke(Color.BLACK);
	whatText.setStroke(Color.BLACK);
	whoText.setEffect(TexturedButton.getShadowEffect());
	whatText.setEffect(TexturedButton.getShadowEffect());
	whoText.setFill(Color.WHITE);
	whatText.setFill(Color.WHITE);
	texts.setAlignment(Pos.TOP_CENTER);
	texts.setPadding(new Insets(40, 0, 0, 0));
	texts.getChildren().addAll(whoText, whatText);
	layers.getChildren().addAll(imageView, texts);
	layers.setAlignment(Pos.TOP_CENTER);
	layers.setPadding(new Insets(0, 0, 0, 10));
	return layers;
    }

    @Override
    public void updateTranslation() {
	try {
	    whoText.setText(lastTurn.getUsername());
	    whatText.setText(TranslationManager.translate(state.translate()));
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void updateRound(int round) throws RemoteException {
    }

    @Override
    public void updateTurn(Player who, TurnState what) throws RemoteException {
	whoText.setText(who.getUsername());
	whatText.setText(TranslationManager.translate(what.translate()));
    }

}
