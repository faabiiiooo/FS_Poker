package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar {

    protected Menu edit, info;
    protected MenuItem numPlayers, showRules, showDocumentation;

    public MainMenu() {
        super();
        this.getStyleClass().add("main-menu");

        this.edit = new Menu("Edit");
        this.numPlayers = new MenuItem("Change Number of Players");

        this.info = new Menu("Help");
        this.showRules = new MenuItem("Show Hand Rankings");
        this.showDocumentation = new Menu("Show Documentation");

        edit.setId("menu-edit");
        info.setId("menu-edit");
        //edit.getStyleClass().add("menu-edit");
        //info.getStyleClass().add("menu-edit");

        edit.getItems().add(numPlayers);
        info.getItems().addAll(showRules,showDocumentation);
        this.getMenus().addAll(edit,info);

    }

}
