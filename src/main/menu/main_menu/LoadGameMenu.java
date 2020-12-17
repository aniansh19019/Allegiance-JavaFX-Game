package main.menu.main_menu;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.GlobalConfig;
import main.SinglePlayerGame;
import main.menu.main_menu.MenuManager;
import widget.LoadGameListItem;
import widget.MenuButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadGameMenu
{
    private StackPane pane;
    private static final GlobalConfig config;
    static
    {
        config = new GlobalConfig();
    }
    public LoadGameMenu(MenuManager menu)
    {
        //init pane
        pane = new StackPane();

        //init back button
        MenuButton backButton=new MenuButton(e->{
            menu.enterMainMenu();
        });
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(0.17);
        backButton.getButton().setScaleY(0.17);
        backButton.getButton().setTranslateX(-110);
        backButton.getButton().setTranslateY(-100);

        //init label
        Image loadGameImage = new Image("file:res/img/ui_elements/load_game_green.png");
        ImageView loadGameLabel = new ImageView(loadGameImage);
        loadGameLabel.setTranslateY(60);

        //init vbox

        VBox box = new VBox(-40);
        box.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(box);
//        scrollPane.setTranslateY(260);
        scrollPane.setTranslateX(48);
        scrollPane.setBackground(Background.EMPTY);
        scrollPane.setMaxHeight(0.8*config.getSCREEN_HEIGHT());
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //read files and generate tiles

        File saveDirectory = new File("saves");
        File saveFiles[] = saveDirectory.listFiles();

        //TODO check for file deletions

        //traverse all the files in the directory
        for(int i=0; i< saveFiles.length; i++)
        {
            try
            {
                FileInputStream inStream = new FileInputStream(saveFiles[i]);
                ObjectInputStream in = new ObjectInputStream(inStream);
                //read game object
                SinglePlayerGame game = (SinglePlayerGame)in.readObject();
                box.getChildren().add(new LoadGameListItem(game, menu).getLayer());
                //close file
                inStream.close();
                in.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        pane.getChildren().addAll(scrollPane, backButton.getButton(), loadGameLabel);
//        pane.setAlignment(Pos.CENTER);
        StackPane.setAlignment(backButton.getButton(),Pos.TOP_LEFT);
        StackPane.setAlignment(loadGameLabel,Pos.TOP_CENTER);
        StackPane.setAlignment(scrollPane,Pos.BOTTOM_CENTER);


    }

    public Node getLayer()
    {
        return pane;
    }
}
