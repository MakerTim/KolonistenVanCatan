package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;
import nl.groep4.kvc.common.interfaces.NotCloseable;
import nl.groep4.kvc.common.interfaces.Player;

public class WinPane implements PaneHolder, NotCloseable {

    private Player winner;

    public WinPane(Player winner) {
	this.winner = winner;
    }

    @Override
    public Pane getPane() {
	winner.toString();
	return new Pane();
    }

    @Override
    public void updateTranslation() {
	// TODO 'winner' WIN - pane
    }

}
