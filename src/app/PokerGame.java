package app;

import javafx.application.Application;
import javafx.stage.Stage;
import controller.PokerGameController;
import model.PokerGameModel;
import view.PokerGameView;

public class PokerGame extends Application {
	public static int numPlayers = 2;
	public static final int MAX_PLAYERS = 4;
	PokerGameModel model;
	PokerGameView view;
	PokerGameController controller;

	
    public static void main(String[] args) {
    	        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	// Create and initialize the MVC components
    	model = new PokerGameModel();
    	view = new PokerGameView(primaryStage, model);
    	controller = new PokerGameController(model, view);
    }

    public static void setNumPlayers(int newAmountOfPlayers){
    	if(newAmountOfPlayers >= 2 && newAmountOfPlayers <= MAX_PLAYERS)
    	numPlayers = newAmountOfPlayers;
		else
			numPlayers = 2;

	}

}
