package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar {

    protected Menu edit;
    protected MenuItem numPlayers;

    public MainMenu() {
        super();
        this.getStyleClass().add("main-menu");

        this.edit = new Menu("Edit");
        this.numPlayers = new MenuItem("Change Number of Players");

        edit.setId("menu-edit");

        edit.getItems().add(numPlayers);
        this.getMenus().add(edit);

    }

}
