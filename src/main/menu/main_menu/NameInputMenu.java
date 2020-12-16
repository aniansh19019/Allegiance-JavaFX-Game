package main.menu.main_menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.SinglePlayerGame;
import main.SinglePlayerGameWrapper;
import widget.MenuButton;
import widget.TextBox;

public class NameInputMenu
{
    private StackPane pane;

    public NameInputMenu(MenuManager menuManager)
    {
        //init pane
        pane = new StackPane();
        //init textBox
        TextBox textBox = new TextBox();
        textBox.getLayer().setTranslateX(20);

        //init Start Game Button

        //TODO stop sound
        MenuButton startGameButton = new MenuButton(e->
        {
            menuManager.setPlayerName(textBox.getText());
            SinglePlayerGameWrapper singlePlayerGameWrapper = new SinglePlayerGameWrapper(menuManager);
            menuManager.getWindow().setScene(singlePlayerGameWrapper.getGame().getScene());
        });
        startGameButton.setNormalImageString("file:res/img/ui_elements/continue_blue.png");
        startGameButton.setHoverImageString("file:res/img/ui_elements/continue_yellow.png");
        startGameButton.setClickImageString("file:res/img/ui_elements/continue_orange.png");
        startGameButton.loadImages();
        startGameButton.getButton().setScaleX(0.65);
        startGameButton.getButton().setScaleY(0.65);
        startGameButton.getButton().setTranslateY(-120);

        //init back button
        //init back button
        MenuButton backButton=new MenuButton(e->{menuManager.newGame();});
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(0.17);
        backButton.getButton().setScaleY(0.17);
        backButton.getButton().setTranslateX(-110);
        backButton.getButton().setTranslateY(-100);

        //init label
        Image enterNameImage = new Image("file:res/img/ui_elements/enter_name_label.png");
        ImageView enterNameLabel = new ImageView(enterNameImage);
        enterNameLabel.setTranslateY(60);


        //add elements
        pane.getChildren().addAll(backButton.getButton(), startGameButton.getButton(), textBox.getLayer(), enterNameLabel);
        pane.setAlignment(Pos.CENTER);
        StackPane.setAlignment(backButton.getButton(), Pos.TOP_LEFT);
        StackPane.setAlignment(startGameButton.getButton(), Pos.BOTTOM_CENTER);
        StackPane.setAlignment(textBox.getLayer(), Pos.CENTER);
        StackPane.setAlignment(enterNameLabel, Pos.TOP_CENTER);
//        StackPane.setAlignment(textBox.getLayer(), Pos.CENTER);

    }

    public Node getLayer()
    {
        return pane;
    }
}
