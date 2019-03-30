package controller;

import app.PokerGame;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.*;
import view.ChangeNumPlayersView;
import view.PlayerPane;
import view.PokerGameView;

import java.util.ArrayList;
import java.util.Collections;

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
		numPlayersView.getApply().setOnAction(this::changeNumPlayers);  //setting apply Button on action

	}

	private void changeNumPlayers(Event e){  //change num of players
		view.getStage().close();
		String numOfPlayers = numPlayersView.getNewNumOfPlayers().getText();
		try{
			int numPlayers = Integer.parseInt(numOfPlayers);
			PokerGame.setNumPlayers(numPlayers);
		} catch (NumberFormatException ex){
			Alert alert = new Alert(AlertType.ERROR, "Enter a valid Number!");
			alert.showAndWait();
		}
		model = new PokerGameModel();  //generating new Model and view to start a new game with new amount of players
		view = new PokerGameView(new Stage(), model);
		this.setViewsOnAction();
		view.start();
		numPlayersView.stop();  //close the window in which num of players got entered

	}

	private void evaluateWinner(){
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<Player> winners = new ArrayList<>();
		for (int i = 0; i < PokerGame.numPlayers; i++){  //getting all the players to manipulate them.
			players.add(model.getPlayer(i));
		}

		Collections.sort(players);  //sort players by their handtype
		Player winner = players.get(players.size()-1); //make the player with the highest hand defaultly to a winner
		winners.add(winner);
		players.remove(winner);  //remove player with highest hand from list.
		for(Player p : new ArrayList<>(players)){
			if(p.compareTo(winner) == 0){  //check if there are other players in the list with the same hand
				winners.add(p);  //if yes add them to the list of winners and remove them from regular players.
				players.remove(p);
			} else {
				p.setWinEval("lost");  //for every player wihch has not the best hand in list set winEval to lost.
			}
		}

		if(winners.size() == 1){ //if there is only one winner, everything is ok, set winEval to won
			winners.get(0).setWinEval("won");
		} else {
			winner = HandType.handleTieBreak(winners); //if there are more than one winner start tieBreak
			if(winner != null){
				winner.setWinEval("won");
			}

		}

	}

    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < PokerGame.numPlayers; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		PokerGameView.rotateCards(pp);
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
        	this.evaluateWinner(); //Setting winEval Variable in Player, based on compareTo on HandType
			for(int i = 0; i < PokerGame.numPlayers; i++) {
				view.getPlayerPane(i).showWinEvaluation(model.getPlayer(i).getWinEval());
			}
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }

}
