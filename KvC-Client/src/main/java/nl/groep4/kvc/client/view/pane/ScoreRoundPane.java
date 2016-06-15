package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.TexturedButton;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateRound;
import nl.groep4.kvc.common.interfaces.UpdateScore;

/**
 * The shield in where round and score can be on displayed
 * 
 * @author Tim
 * @version 1.0
 */
public class ScoreRoundPane implements PaneHolder, UpdateScore, UpdateRound {

    private Text roundText;
    private Text scoreText;

    private int round = 0;
    private int score = 0;

    @Override
    public Pane getPane() {
	StackPane layers = new StackPane();
	layers.setPadding(new Insets(0, 0, 0, 10));
	ImageView shield = new ImageView("/img/game/score_board.png");
	shield.setFitHeight(194);
	shield.setFitWidth(125);
	VBox text = new VBox(10);
	roundText = new Text(TranslationManager.translate("game.round", round));
	scoreText = new Text(TranslationManager.translate("game.score", score));
	roundText.setFont(ViewMaster.FONT);
	scoreText.setFont(ViewMaster.FONT);
	roundText.setStroke(Color.BLACK);
	scoreText.setStroke(Color.BLACK);
	roundText.setEffect(TexturedButton.getShadowEffect());
	scoreText.setEffect(TexturedButton.getShadowEffect());
	roundText.setFill(Color.WHITE);
	scoreText.setFill(Color.WHITE);
	text.getChildren().addAll(roundText, scoreText);
	layers.setAlignment(Pos.CENTER);
	text.setAlignment(Pos.CENTER);

	layers.getChildren().addAll(shield, text);
	return layers;
    }

    @Override
    public void updateTranslation() {
	roundText.setText(TranslationManager.translate("game.round", round));
	scoreText.setText(TranslationManager.translate("game.score", score));
    }

    @Override
    public void updateRound(int round) {
	this.round = round;
	roundText.setText(TranslationManager.translate("game.round", round));
    }

    @Override
    public void updateScore(Player pl, int score) {
	if (ClientRefrence.getThePlayer().equals(pl)) {
	    this.score = score;
	    scoreText.setText(TranslationManager.translate("game.score", score));
	}
    }

    @Override
    public void updateTurn(Player who, TurnState what) throws RemoteException {
    }

}
