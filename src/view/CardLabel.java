package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Card;



public class CardLabel extends Label {
	public CardLabel() {
		super();
		this.getStyleClass().add("card");


	}



	public void setCard(Card card) {
		if (card != null) {
			String fileName = cardToFileName(card);
			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("images/" + fileName));
			ImageView imv = new ImageView(image);
			imv.fitWidthProperty().bind(this.widthProperty());
			imv.fitHeightProperty().bind(this.heightProperty());
			imv.setPreserveRatio(true);
			this.setGraphic(imv);
		} else {
			//setting a beautiful cardBack when no card is displayed
		    Image cardBack = new Image(this.getClass().getClassLoader().getResourceAsStream("images/card_background.png"));
            ImageView imvCardBack = new ImageView(cardBack);
            imvCardBack.fitWidthProperty().bind(this.widthProperty());
            imvCardBack.fitHeightProperty().bind(this.heightProperty());
            imvCardBack.setPreserveRatio(true);
			this.setGraphic(imvCardBack);
		}
	}

	private String cardToFileName(Card card) {
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		return rank + "_of_" + suit + ".png";
	}



}
