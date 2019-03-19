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
			System.out.println("invalid number");
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
		for(Player p : new ArrayList<Player>(players)){
			if(p.compareTo(winner) == 0){  //check if there are other players in the list with the same hand
				winners.add(p);  //if yes add them to the list of winners and remove them from regular players.
				players.remove(p);
			} else {
				p.setWinEval("lost");  //for every player wihch has not the best hand in list set winEval to lost.
			}
		}

		//System.out.println("winners.size: "+winners.size());
		/*for(Player p: winners){
			System.out.println(p.getHandType());
		}*/
		if(winners.size() == 1){ //if there is only one winner, everything is ok, set winEval to won
			winners.get(0).setWinEval("won");
		} else {
			winner = HandType.handleTieBreak(winners); //if there are more than one winner start tieBreak
			if(winner != null){
				winner.setWinEval("won");
			}/*
			for(Player p: winners){
				System.out.println(p.getWinEval());
			}*/
			//HandType.handleTieBreak(winners);
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
        	/*for(int i = 0; i < PokerGame.numPlayers -1; i++) {  //Appending win / loose / draw to EvaluationLabel
				for (int j = i+1; j < PokerGame.numPlayers; j++) {

					if (model.getPlayer(i).getWinEval().equals("won") && model.getPlayer(j).getWinEval().equals("won")) {
						view.getPlayerPane(i).showWinEvaluation("draw"+i);
						view.getPlayerPane(j).showWinEvaluation("draw"+j);
						System.out.println("1st if");
					} else {
						if (model.getPlayer(i).getWinEval().equals("won") && model.getPlayer(j).getWinEval().equals("lost")) {
							view.getPlayerPane(i).showWinEvaluation("win");
							view.getPlayerPane(j).showWinEvaluation("loose");
							System.out.println("2nd if");
						} else {
							if (model.getPlayer(i).getWinEval().equals("lost") && model.getPlayer(j).getWinEval().equals("won")) {
								view.getPlayerPane(i).showWinEvaluation("loose");
								view.getPlayerPane(j).showWinEvaluation("win");
								System.out.println("3th if");
							}
						}
					}

				}
			} */
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }
}
