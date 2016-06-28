package nl.groep4.kvc.client.view.pane;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.interfaces.NotCloseable;
import nl.groep4.kvc.common.interfaces.Player;

public class WinPane implements PaneHolder, NotCloseable {
    private Font font = new Font(ViewMaster.FONT.getName(), 40);
    private Player winner;

    private MenuButton credits = new MenuButton(425, 500, TranslationManager.translate("win.button.credits"));
    private MenuButton close = new MenuButton(425, 500, TranslationManager.translate("win.button.close"));
    private MenuButton screenshot = new MenuButton(425, 500, TranslationManager.translate("win.button.screenshot"));

    private Text winnerLine = new Text();

    private VBox vbox;
    private HBox hbox;

    private ImageView trophy;
    private ImageView banner;

    private SceneMap scenemap;

    private WritableImage image;

    public WinPane() {
    }

    public WinPane(SceneMap scenemap, Player winner) {
	this.scenemap = scenemap;
	this.winner = winner;
    }

    @Override
    public Pane getPane() {
	StackPane winpane = new StackPane();
	StackPane namepane = new StackPane();

	vbox = new VBox();
	hbox = new HBox();

	trophy = new ImageView("img/etc/trophy.png");
	banner = new ImageView("img/etc/namebanner.png");

	try {
	    winnerLine.setText(winner.getUsername());
	} catch (RemoteException e) {
	    e.printStackTrace();
	} catch (NullPointerException npe) {
	    winnerLine.setText("Lisa");
	}
	winnerLine.setFont(font);
	winnerLine.setFill(Color.WHITE);
	winnerLine.setStroke(Color.BLACK);

	hbox.setAlignment(Pos.CENTER);
	vbox.setAlignment(Pos.CENTER);
	namepane.setAlignment(Pos.TOP_CENTER);
	namepane.setPadding(new Insets(50, 0, 0, 0));

	hbox.setSpacing(10);

	hbox.getChildren().addAll(close, credits, screenshot);
	vbox.getChildren().addAll(banner, trophy, hbox);
	namepane.getChildren().add(winnerLine);

	close.registerClick(() -> onCloseClick());
	credits.registerClick(() -> onCreditsClick());
	screenshot.registerClick(() -> onScreenshotClick());

	winpane.getChildren().addAll(vbox, namepane);
	return winpane;
    }

    private void onCloseClick() {
	System.exit(0);
    }

    private void onCreditsClick() {
	scenemap.openCreditsPane();
    }

    private void onScreenshotClick() {
	image = scenemap.getLayers().snapshot(null, null);

	File file = new File("screenshot.png");

	try {
	    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	    System.out.println("Screenshot!");
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (NullPointerException npe) {
	    npe.printStackTrace();
	}
    }

    @Override
    public void updateTranslation() {
	credits.updateText(TranslationManager.translate("win.button.credits"));
	close.updateText(TranslationManager.translate("win.button.close"));
	screenshot.updateText(TranslationManager.translate("win.button.screenshot"));
    }

}
