package nl.groep4.kvc.client.view.elements;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;

public class ResourceCard extends Text {

    private ImageView cardPlank;
    private ImageView oreCard;
    private ImageView stoneCard;
    private ImageView wheatCard;
    private ImageView woodCard;
    private ImageView woolCard;

    Text amntWood;
    Text amntOre;
    Text amntWool;
    Text amntWheat;
    Text amntStone;

    public Text getWoodText() {
	amntWood = new Text("0");
	amntWood.setFont(ViewMaster.TITLE_FONT);
	amntWood.setFill(Color.WHITE);
	amntWood.setStroke(Color.BLACK);
	return amntWood;
    }

    public Text getOreText() {
	amntOre = new Text("0");
	amntOre.setFont(ViewMaster.TITLE_FONT);
	amntOre.setFill(Color.WHITE);
	amntOre.setStroke(Color.BLACK);
	return amntOre;
    }

    public Text getWoolText() {
	amntWool = new Text("0");
	amntWool.setFont(ViewMaster.TITLE_FONT);
	amntWool.setFill(Color.WHITE);
	amntWool.setStroke(Color.BLACK);
	return amntWool;
    }

    public Text getWheatText() {
	amntWheat = new Text("0");
	amntWheat.setFont(ViewMaster.TITLE_FONT);
	amntWheat.setFill(Color.WHITE);
	amntWheat.setStroke(Color.BLACK);
	return amntWheat;
    }

    public Text getStoneText() {
	amntStone = new Text("0");
	amntStone.setFont(ViewMaster.TITLE_FONT);
	amntStone.setFill(Color.WHITE);
	amntStone.setStroke(Color.BLACK);
	return amntStone;
    }

    public Node getCardPlank() {
	if (cardPlank == null) {
	    cardPlank = new ImageView("img/game/kaart_plank.png");
	}
	return cardPlank;
    }

    /**
     * Gets the resource for the wood card
     * 
     * @return image of the wood card
     */
    public Node getWoodCard() {
	woodCard = new ImageView("img/cards/kaart_hout.png");
	woodCard.setFitHeight(170);
	woodCard.setFitWidth(113.5);
	return woodCard;
    }

    /**
     * Gets the resource for the stone card
     * 
     * @return image of the stone card
     */
    public Node getStoneCard() {
	stoneCard = new ImageView("img/cards/kaart_steen.png");
	stoneCard.setFitHeight(170);
	stoneCard.setFitWidth(113.5);
	return stoneCard;
    }

    /**
     * Gets the resource for the wool card
     * 
     * @return image of the wool card
     */
    public Node getWoolCard() {
	woolCard = new ImageView("img/cards/kaart_schaap.png");
	woolCard.setFitHeight(170);
	woolCard.setFitWidth(113.5);
	return woolCard;
    }

    /**
     * Gets the resource for the wheat card
     * 
     * @return image of the wheat card
     */
    public Node getWheatCard() {
	wheatCard = new ImageView("img/cards/kaart_graan.png");
	wheatCard.setFitHeight(170);
	wheatCard.setFitWidth(113.5);
	return wheatCard;
    }

    /**
     * Gets the resource for the ore card
     * 
     * @return image of the ore card
     */
    public Node getOreCard() {
	oreCard = new ImageView("img/cards/kaart_erts.png");
	oreCard.setFitHeight(170);
	oreCard.setFitWidth(113.5);
	return oreCard;
    }

}
