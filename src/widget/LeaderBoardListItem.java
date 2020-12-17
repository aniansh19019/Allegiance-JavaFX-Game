package widget;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import main.GlobalConfig;
import main.SinglePlayerGame;
import main.SinglePlayerGameWrapper;
import main.menu.main_menu.MenuManager;

//TODO fix alignment

public class LeaderBoardListItem
{
    private static final double scale=0.8;
    private static final GlobalConfig config;

//    private MenuButton button;
    private Group group;
    private SinglePlayerGame game;

    public SinglePlayerGame getGame()
    {
        return game;
    }

    public void setGame(SinglePlayerGame game)
    {
        this.game = game;
    }

    static
    {
        config= new GlobalConfig();
    }
    private String trimPadString(String s, int n)
    {
        if(s.length()>=n)
        {
            return s.substring(0,n);
        }
        else
        {
            for(int i=s.length(); i<n; i++)
            {
                s+=" ";
            }
            return s;
        }
    }


    public LeaderBoardListItem(String name, String starCount, String score)
    {

        //create bar image
        Image itemBarImage  = new Image("file:res/img/ui_elements/list_bar_green.png");
        ImageView itemBackGround = new ImageView(itemBarImage);

        itemBackGround.setScaleX(scale);
        itemBackGround.setScaleY(scale);


        //init overlay

        Label nameLabel = new Label(trimPadString(name,10));

        Image starImage = new Image("file:res/img/powerups/star.png");
        ImageView stars = new ImageView(starImage);
        stars.setScaleX(0.3);
        stars.setScaleY(0.3);
        System.out.println(nameLabel.getText().length());

        Label starsLabel = new Label(trimPadString( ""+starCount, 3), stars);

        Label scoreLabel = new Label("Score: "+ score);

        //setup text
        nameLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 18));
        starsLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 18));
        scoreLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 18));

        nameLabel.setTextFill(Paint.valueOf("000000"));
        starsLabel.setTextFill(Paint.valueOf("000000"));
        scoreLabel.setTextFill(Paint.valueOf("000000"));
        starsLabel.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        starsLabel.setGraphicTextGap(-30);
        starsLabel.setPadding(new Insets(0,20,0,10));
//        starsLabel.setLayoutY(-100);
//        nameLabel.setLayoutX(200);



        //add to hbox
        HBox hBox = new HBox(0);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(nameLabel,starsLabel,scoreLabel);
        hBox.setTranslateY(-24);
        hBox.setTranslateX(80);

        //init group
        group = new Group();

        //add to group
        group.getChildren().addAll(itemBackGround, hBox);


    }

    public Node getLayer()
    {
        return group;
    }
}
