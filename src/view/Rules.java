package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Rules extends VBox {


    public Rules(){


        Label rulesLablel = new Label();
        Image rules = new Image(this.getClass().getClassLoader().getResourceAsStream("images/handRanking.jpg"));
        ImageView imv = new ImageView(rules);
        imv.fitWidthProperty().bind(this.widthProperty());
        imv.fitHeightProperty().bind(this.heightProperty());
        imv.setPreserveRatio(true);
        rulesLablel.setGraphic(imv);

        this.getChildren().addAll(rulesLablel);

        Scene showRulesScene = new Scene(this, 600, 725);
        showRulesScene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        Stage showRulesStage = new Stage();
        showRulesStage.setScene(showRulesScene);
        showRulesStage.setTitle("Hand Rankings");
        showRulesStage.getIcons().add(new Image("images/icon.jpg"));
        showRulesStage.setResizable(false);


        showRulesStage.show();

    }
}
