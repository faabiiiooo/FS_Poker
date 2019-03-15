package view;

import javafx.scene.control.Label;
import model.DeckOfCards;

public class DeckLabel extends Label {
	public DeckLabel() {
		super();
		this.getStyleClass().add("deck");
		/*Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/card_background.png"));
		ImageView imv = new ImageView(image);
		imv.setFitHeight(68);
		imv.setFitWidth(45);
		imv.setPreserveRatio(true);
		this.setGraphic(imv);*/
	}
	
	// Bind the label to the CardsRemaining property of the deck
	public void setDeck(DeckOfCards deck) {
		this.textProperty().bind(deck.getCardsRemainingProperty().asString());
	}
}
