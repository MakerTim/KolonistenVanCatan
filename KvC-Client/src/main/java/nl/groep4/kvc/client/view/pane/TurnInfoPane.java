package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateRound;

/**
 * The pane that gives some info on this turn.
 * 
 * @author Tim
 * @version 1.1
 */
public class TurnInfoPane implements PaneHolder, UpdateRound {

    private final Font font = new Font(ViewMaster.FONT.getName(), 16);
    private Player lastTurn;
    private TurnState state = TurnState.WAITING;

    private Text whoText;
    private Text whatText;

    @Override
    public Pane getPane() {
	StackPane layers = new StackPane();
	ImageView imageView = new ImageView("img/etc/parchment.png");
	VBox texts = new VBox();
	whoText = new KvCText();
	whatText = new KvCText();
	whoText.setFont(font);
	whatText.setFont(font);
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
	    updateTurn(lastTurn, state);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void updateRound(int round) throws RemoteException {
    }

    @Override
    public void updateTurn(Player who, TurnState what) throws RemoteException {
	lastTurn = who;
	state = what;
	String append = "";
	if (who == null || ClientRefrence.getThePlayer().equals(who)) {
	    whoText.setText(TranslationManager.translate("turn.self"));
	    append = ".self";
	} else {
	    whoText.setText(who.getUsername());
	}
	whatText.setText(TranslationManager.translate(what.translate() + append));
    }

}
