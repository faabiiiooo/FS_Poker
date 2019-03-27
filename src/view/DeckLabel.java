package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.DeckOfCards;

public class DeckLabel extends Label {
	public DeckLabel() {
		super();
		this.getStyleClass().add("deck");
		Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/card_background.png"));
		ImageView imv = new ImageView(image);
		imv.setFitHeight(136);
		imv.setFitWidth(90);
		imv.setPreserveRatio(true);
		this.setGraphic(imv);
	}
	
	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck) {
		this.textProperty().bind(deck.getCardsRemainingProperty().asString());
	}
}
