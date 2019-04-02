package view;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class Documentation {

    private WebView documentationView;
    private WebEngine documentationEngine;

    public Documentation(){
        this.documentationView = new WebView();
        this.documentationEngine = documentationView.getEngine();

        URL documentation = getClass().getResource("/documentation/Documentation_Poker_FS.html");
        documentationView.getEngine().load(documentation.toExternalForm());

        //documentationEngine.loadContent("/documentation/Documentation_Poker_FS.html");

        System.out.println(documentationEngine.getDocument());

        Scene documentationScene = new Scene(this.documentationView);
        Stage documentationStage = new Stage();
        documentationStage.setScene(documentationScene);
        documentationStage.setTitle("Documentation");
        documentationStage.show();


    }

}
