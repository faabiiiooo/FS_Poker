package view;

import app.PokerGame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChangeNumPlayersView extends HBox {

    protected Button apply;
    protected TextField newNumOfPlayers;
    private Stage numPlayStage;

    public ChangeNumPlayersView(){  //New Window to change amount of players.

        numPlayStage = new Stage();
        Label lblNumPlayers = new Label("Enter Number of Players: \n" +
                "at least 2, max 4 Players");


        apply = new Button("Apply");
        apply.setDisable(true);
        newNumOfPlayers = new TextField();
        newNumOfPlayers.textProperty().addListener((observable, oldValue, newValue)->checkInput(newValue));

        this.getChildren().addAll(lblNumPlayers,newNumOfPlayers,apply);

        this.setAlignment(Pos.CENTER);

        this.getStyleClass().add("numPlayer-view");

        Scene numPlayScene = new Scene(this);
        numPlayScene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        numPlayStage.setScene(numPlayScene);
        numPlayStage.setTitle("Change Number of Players");
        numPlayStage.getIcons().add(new Image("images/icon.jpg"));
        numPlayStage.show();


    }

    private void checkInput(String newValue){

        if(newValue == null){
            this.newNumOfPlayers.setStyle("-fx-text-inner-color:red;");
            this.apply.setDisable(true);
        } else {
            try {
                int newNumOfPlayers = Integer.parseInt(newValue);
                if (newNumOfPlayers >= 2 && newNumOfPlayers <= PokerGame.MAX_PLAYERS) {
                    this.newNumOfPlayers.setStyle("-fx-text-inner-color:green;");
                    this.apply.setDisable(false);
                } else {
                    this.newNumOfPlayers.setStyle("-fx-text-inner-color:red;");
                    this.apply.setDisable(true);
                }
            } catch (NumberFormatException e) {
                this.newNumOfPlayers.setStyle("-fx-text-inner-color:red;");
            }
        }

    }

    public void stop(){ numPlayStage.close();}

    public Button getApply(){
        return apply;
    }

    public TextField getNewNumOfPlayers(){
        return newNumOfPlayers;
    }




}
