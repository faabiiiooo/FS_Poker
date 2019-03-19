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

	/*
	public void evaluateWinner(){  //Sets the winEval String player attribute
		ArrayList<Player> winners = new ArrayList<>();
		ArrayList<Player> clonedPlrs = (ArrayList<Player>) this.players.clone();

        Collections.sort(clonedPlrs);

		for (int i = 0; i < players.size()-1; i++){
			for(int j = i+1; j < players.size(); j++){
				if(players.get(i).compareTo(players.get(j)) < 0){
					winners.add(players.get(j));
					players.get(i).setWinEval("lost");
				} else {
					if( players.get(i).compareTo(players.get(j)) > 0){
						winners.add(players.get(i));
						players.get(j).setWinEval("lost");
					}
				}
			}
		}

		for (int i = 0; i < winners.size()-1; i++){
			for(int j = i+1; j < winners.size(); j++){
				if (winners.get(i).compareTo(winners.get(j)) < 0){
					winners.get(i).setWinEval("lost");
					winners.get(j).setWinEval("won");
				} else {
					if(winners.get(i).compareTo(winners.get(j)) > 0){
						winners.get(j).setWinEval("lost");
						winners.get(i).setWinEval("won");
					}
				}
			}
		}
	}
	*/

}
