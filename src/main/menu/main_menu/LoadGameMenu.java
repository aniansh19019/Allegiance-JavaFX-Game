package main.menu.main_menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.GlobalConfig;
import main.SinglePlayerGame;
import widget.LoadGameListItem;
import widget.MenuButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Comparator;

public class LoadGameMenu
{
    private final StackPane pane;
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

        //traverse all the files in the directory
        if (saveFiles != null)
        {
            // sort by time modified
            Arrays.sort(saveFiles, new Comparator<File>(){
                public int compare(File f1, File f2)
                {
                    return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                } });

            int limit=0;

            if(saveFiles.length>10)
            {
                limit = 10; // limit to showing 10 most recent saves
            }
            else
            {
                limit = saveFiles.length;
            }

            for(int i=0; i< limit; i++)
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

            if(limit < saveFiles.length) // delete any files older than the 10 most recent entries
            {
                for(int i=limit; i< saveFiles.length; i++)
                {
                    try
                    {
                        saveFiles[i].delete();
                    }
                    catch (Exception e)
                    {
                        System.out.println("Unable to delete files!");
                        e.printStackTrace();
                    }
                }
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
