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
import widget.LeaderBoardListItem;
import widget.MenuButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class PlayerRecord implements Comparable<PlayerRecord>
{
    private String name;
    private int stars;
    private int score;

    public PlayerRecord(String name, int stars, int score)
    {
        this.name = name;
        this.stars = stars;
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getStars()
    {
        return stars;
    }

    public void setStars(int stars)
    {
        this.stars = stars;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public int compareTo(PlayerRecord o)
    {
        return o.score - this.score;
    }
}

public class LeaderBoardMenu
{
    private final StackPane pane;
    private static final GlobalConfig config;
    static
    {
        config = new GlobalConfig();
    }
    public LeaderBoardMenu(EventHandler<? super MouseEvent> backHandler)
    {
        //init pane
        pane = new StackPane();

        //init back button
        MenuButton backButton=new MenuButton(backHandler);
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(0.17);
        backButton.getButton().setScaleY(0.17);
        backButton.getButton().setTranslateX(-110);
        backButton.getButton().setTranslateY(-100);

        //init label
        Image leaderBoardImage = new Image("file:res/img/ui_elements/leaderboards_green.png");
        ImageView leaderBoardLabel = new ImageView(leaderBoardImage);
        leaderBoardLabel.setTranslateY(60);

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

        File leaderBoardFile = new File("leaderboard/leaderboard.csv");
        ArrayList<PlayerRecord> records = new ArrayList<PlayerRecord>();




        //read and parse files and create tiles

        try
        {
//            BufferedReader in = new BufferedReader(new FileReader(leaderBoardFile));
            Scanner in = new Scanner(leaderBoardFile);
//            in.useDelimiter(", ");

            while(in.hasNext())//read all records
            {
                String name = in.next();
                String starCount = in.next();
                String score = in.next();
                name = name.substring(0,name.length()-1);
                starCount = starCount.substring(0,starCount.length()-1);
                records.add(new PlayerRecord(name, Integer.parseInt(starCount), Integer.parseInt(score)));
//                box.getChildren().add(new LeaderBoardListItem(name, starCount, score).getLayer());
            }
            in.close();

            //reading done, no sort the records
            Collections.sort(records);

            //now create list items
            for(int i=0; i< records.size(); i++)
            {
                box.getChildren().add(new LeaderBoardListItem("#"+ (i+1)+" "+records.get(i).getName(), ""+records.get(i).getStars(), ""+records.get(i).getScore()).getLayer());
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        pane.getChildren().addAll(scrollPane, backButton.getButton(), leaderBoardLabel);
//        pane.setAlignment(Pos.CENTER);
        StackPane.setAlignment(backButton.getButton(),Pos.TOP_LEFT);
        StackPane.setAlignment(leaderBoardLabel,Pos.TOP_CENTER);
        StackPane.setAlignment(scrollPane,Pos.BOTTOM_CENTER);


    }

    public Node getLayer()
    {
        return pane;
    }
}
