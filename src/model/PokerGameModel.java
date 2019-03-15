package model;

import app.PokerGame;

import java.util.ArrayList;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	
	public PokerGameModel() {
		for (int i = 0; i < PokerGame.numPlayers; i++) {
			players.add(new Player("Player " + i));
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}

	public void evaluateWinner(){  //Sets the winEval String player attribute

		for(int i = 0; i < PokerGame.numPlayers -1; i++){
			for(int j = i+1; j < PokerGame.numPlayers; j++){
				if(players.get(i).compareTo(players.get(j)) < 0){
					players.get(i).setWinEval("loose");
					players.get(j).setWinEval("win");
				} else {
					if(players.get(i).compareTo(players.get(j)) > 0){
						players.get(i).setWinEval("win");
						players.get(j).setWinEval("loose");
					} else { //default set to win
						players.get(i).setWinEval("win");
						players.get(j).setWinEval("win");
					}
				}
			}
		}
	}

}
