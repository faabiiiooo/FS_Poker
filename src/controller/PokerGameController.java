package controller;

import app.PokerGame;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Card;
import model.DeckOfCards;
import model.Player;
import model.PokerGameModel;
import view.ChangeNumPlayersView;
import view.PlayerPane;
import view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	private ChangeNumPlayersView numPlayersView;
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		this.setViewsOnAction();

	}

	private void setViewsOnAction(){  //Setting all the views on Action.
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		view.getNumPlayers().setOnAction(this::openNumPlayersWindow);
	}

	private void openNumPlayersWindow(Event e){  //open window to enter new amount of players
		numPlayersView = new ChangeNumPlayersView();
		numPlayersView.getApply().setOnAction(this::changeNumPlayers);

	}

	private void changeNumPlayers(Event e){  //change num of players
		view.getStage().close();
		String numOfPlayers = numPlayersView.getNewNumOfPlayers().getText();
		try{
			int numPlayers = Integer.parseInt(numOfPlayers);
			PokerGame.setNumPlayers(numPlayers);
		} catch (NumberFormatException ex){
			System.out.println("Keine ganze Zahl eingegeben!");
		}
		model = new PokerGameModel();  //generating new Model and view to start a new game with new amount of players
		view = new PokerGameView(new Stage(), model);
		this.setViewsOnAction();
		view.start();
		numPlayersView.stop();  //close the window in which num of players got entered

	}

    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < PokerGame.numPlayers; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}

    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = PokerGame.numPlayers * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < PokerGame.numPlayers; i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		pp.updatePlayerDisplay();
        	}
        	model.evaluateWinner(); //Settin winEval Variable in Player, based on compareTo on HandType
        	for(int i = 0; i < PokerGame.numPlayers -1; i++) {  //Appending win / loose / draw to EvaluationLabel
				for (int j = i+1; j < PokerGame.numPlayers; j++) {
					if (model.getPlayer(i).getWinEval().equals("win") && model.getPlayer(j).getWinEval().equals("win")) {
						view.getPlayerPane(i).showWinEvaluation("draw");
						view.getPlayerPane(j).showWinEvaluation("draw");
					} else {
						if (model.getPlayer(i).getWinEval().equals("win") && model.getPlayer(j).getWinEval().equals("loose")) {
							view.getPlayerPane(i).showWinEvaluation("win");
							view.getPlayerPane(j).showWinEvaluation("loose");
						} else {
							if (model.getPlayer(i).getWinEval().equals("loose") && model.getPlayer(j).getWinEval().equals("win")) {
								view.getPlayerPane(i).showWinEvaluation("loose");
								view.getPlayerPane(j).showWinEvaluation("win");
							}
						}
					}
				}
			}
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }
}
