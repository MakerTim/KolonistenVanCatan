package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScorePane implements PaneHolder {

    StackPane scorePane;
    VBox players;
    VBox content;
    ImageView banner;
    ImageView closedBanner;
    Text title;

    @Override
    public Pane getPane() {
	scorePane = new StackPane();
	players = new VBox();
	content = new VBox();
	title = new Text();

	content.getChildren().addAll(title, players);

	scorePane.getChildren().addAll(getClosedBanner());

	scorePane.setOnMouseEntered(e -> clickOpen());
	scorePane.setOnMouseExited(e -> clickClose());
	scorePane.setAlignment(Pos.TOP_CENTER);
	return scorePane;
    }

    public void clickOpen() {
	scorePane.getChildren().addAll(getBanner(), content);
	scorePane.getChildren().remove(closedBanner);
    }

    public void clickClose() {
	scorePane.getChildren().add(getClosedBanner());
	scorePane.getChildren().removeAll(banner, content);
    }

    public Node getBanner() {
	banner = new ImageView("img/game/banner.png");
	banner.setFitHeight(398);
	banner.setFitWidth(240);
	return banner;
    }

    public Node getClosedBanner() {
	closedBanner = new ImageView("img/game/banner_closed.png");
	closedBanner.setFitHeight(120);
	closedBanner.setFitWidth(240);
	return closedBanner;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }
}
