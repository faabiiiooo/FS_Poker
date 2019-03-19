package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        newNumOfPlayers = new TextField();

        this.getChildren().addAll(lblNumPlayers,newNumOfPlayers,apply);

        this.setAlignment(Pos.CENTER);

        this.getStyleClass().add("numPlayer-view");

        Scene numPlayScene = new Scene(this);
        numPlayScene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        numPlayStage.setScene(numPlayScene);
        numPlayStage.setTitle("Change Number of Players");
        numPlayStage.show();


    }

    public void stop(){ numPlayStage.close();}

    public Button getApply(){
        return apply;
    }

    public TextField getNewNumOfPlayers(){
        return newNumOfPlayers;
    }




}
