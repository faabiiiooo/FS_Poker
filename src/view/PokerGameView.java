package view;

import app.PokerGame;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
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

		// Create all of the player panes we need, and put them into an HBox
		players = new TilePane(Orientation.HORIZONTAL);
		for (int i = 0; i < PokerGame.numPlayers; i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			pp.getLblEvaluation().textProperty().addListener((observable, oldValue, newValue)
					-> winnerChanged(newValue));  //defining a Changelistener on evaluation Label
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
        this.start();
	}



	private void winnerChanged(String newValue) {

		if (newValue.contains("won")) {  //checking if newValue contains won

			for (int i = 0; i < PokerGame.numPlayers; i++) {
				if (this.getPlayerPane(i).getLblEvaluation().getText().contains("won")) {  //going through all PlayerPanes to get pane of winner

					ScaleTransition makeWinnerBig = new ScaleTransition(Duration.millis(750), this.getPlayerPane(i));
					makeWinnerBig.setByX(0.25);
					makeWinnerBig.setByY(0.25);  //make winnerpane pulsating
					makeWinnerBig.setCycleCount(4);
					makeWinnerBig.setAutoReverse(true);

					makeWinnerBig.play();

				}
			}
		}
	}

	public static void rotateCards(PlayerPane pp){

		SequentialTransition rotateAllCards = new SequentialTransition();

		for(int i = 0; i < pp.hboxCards.getChildren().size(); i++){
			Label cardLabel = (Label) pp.hboxCards.getChildren().get(i);
			RotateTransition rotateCard = new RotateTransition(Duration.millis(500), cardLabel);
			rotateCard.setAxis(Rotate.Y_AXIS);
			rotateCard.setByAngle(360);
			rotateCard.setAutoReverse(true);
			rotateAllCards.getChildren().add(rotateCard);
		}
		rotateAllCards.play();


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

	public MenuItem getShowRules() { return menu.showRules; }

	public Stage getStage() { return stage; }
}
