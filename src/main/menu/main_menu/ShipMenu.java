package main.menu.main_menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.SinglePlayerGameWrapper;
import widget.ChooseShipTilePane;
import widget.MenuButton;

public class ShipMenu
{
    private final StackPane pane;
    private ChooseShipTilePane shipSelector;
    private final MenuManager menuManager;

    private void startGame()
    {
        SinglePlayerGameWrapper singlePlayerGameWrapper = new SinglePlayerGameWrapper(menuManager);
        menuManager.getWindow().setScene(singlePlayerGameWrapper.getGame().getScene());
    }

    public ShipMenu(MenuManager menuManager)
    {
        this.menuManager=menuManager;
        //init pane
        pane = new StackPane();
        //init back button
        MenuButton backButton=new MenuButton(e->{menuManager.enterMainMenu();});
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(0.17);
        backButton.getButton().setScaleY(0.17);
        backButton.getButton().setTranslateX(-110);
        backButton.getButton().setTranslateY(-100);

        //init start game button
        //init save button
        MenuButton continueButton = new MenuButton(e->
        {
            menuManager.setShipNum(shipSelector.getShipSelected());
            menuManager.enterName();
        });
        continueButton.setNormalImageString("file:res/img/ui_elements/continue_blue.png");
        continueButton.setHoverImageString("file:res/img/ui_elements/continue_yellow.png");
        continueButton.setClickImageString("file:res/img/ui_elements/continue_orange.png");
        continueButton.loadImages();
        continueButton.getButton().setScaleX(0.65);
        continueButton.getButton().setScaleY(0.65);
        continueButton.getButton().setTranslateY(-70);

        //init tiles
        shipSelector = new ChooseShipTilePane();
        shipSelector.getLayer().setTranslateY(80);
        shipSelector.getLayer().setTranslateX(20);

        //init label
        Image chooseShipImage = new Image("file:res/img/ui_elements/choose_ship.png");
        ImageView chooseShipLabel = new ImageView(chooseShipImage);
        chooseShipLabel.setTranslateY(60);

        //add elements
        pane.getChildren().addAll(shipSelector.getLayer(), chooseShipLabel, backButton.getButton(), continueButton.getButton());
        pane.setAlignment(Pos.CENTER);
        StackPane.setAlignment(chooseShipLabel, Pos.TOP_CENTER);
        StackPane.setAlignment(backButton.getButton(), Pos.TOP_LEFT);
        StackPane.setAlignment(continueButton.getButton(), Pos.BOTTOM_CENTER);
        StackPane.setAlignment(shipSelector.getLayer(), Pos.CENTER);

    }

    public Node getLayer()
    {
        return pane;
    }
}
