package view;

import app.PokerGame;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.PokerGameModel;

public class PokerGameView {
	private TilePane players;
	private ControlArea controls;
	private MainMenu menu;
	
	private PokerGameModel model;
	private Stage stage;


	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		this.stage = stage;

		System.out.println(PokerGame.numPlayers);
		
		// Create all of the player panes we need, and put them into an HBox
		players = new TilePane(Orientation.HORIZONTAL);
		for (int i = 0; i < PokerGame.numPlayers; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			players.getChildren().add(pp);
		}

		players.setId("all-players");
		players.setPrefColumns(2);

		//Create main menu
		menu = new MainMenu();
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls, menu into a BorderPane
		BorderPane root = new BorderPane();
		root.setCenter(players);
		root.setBottom(controls);
		root.setTop(menu);
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(false);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        this.stage.setTitle("Poker Miniproject");
        this.stage.setScene(scene);
        //this.stage.setMaximized(true);
        this.start();
	}


	public void setStage(Stage stage){
		this.stage = stage;

	}

	public void start(){ this.stage.show();}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) players.getChildren().get(i);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}

	public MenuItem getNumPlayers() { return menu.numPlayers; }

	public Stage getStage() { return stage; }
}
