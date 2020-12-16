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

public class LoadGameListItem
{
    private static final double scale=0.8;
    private static final GlobalConfig config;

    private MenuButton button;
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


    public LoadGameListItem(SinglePlayerGame singlePlayerGame, MenuManager menu)
    {

        this.game =singlePlayerGame;
        //init button
        button = new MenuButton(e->{
            SinglePlayerGameWrapper singlePlayerGameWrapper = new SinglePlayerGameWrapper(game, menu);
            menu.getWindow().setScene(singlePlayerGameWrapper.getGame().getScene());
            game.startTimer();
        });
        button.setNormalImageString("file:res/img/ui_elements/list_bar_green.png");
        button.setHoverImageString("file:res/img/ui_elements/list_bar_yellow.png");
        button.setClickImageString("file:res/img/ui_elements/list_bar_orange.png");
        button.loadImages();
        button.getButton().setScaleX(scale);
        button.getButton().setScaleY(scale);


        //init overlay

        Label nameLabel = new Label(trimPadString(singlePlayerGame.getName(),10));

        Image starImage = new Image("file:res/img/powerups/star.png");
        ImageView stars = new ImageView(starImage);
        stars.setScaleX(0.3);
        stars.setScaleY(0.3);
        System.out.println(nameLabel.getText().length());

        Label starsLabel = new Label(trimPadString( ""+singlePlayerGame.getStarCount(), 3), stars);
//        starsLabel.setGraphicTextGap(-15);

        Label scoreLabel = new Label("Score: "+ singlePlayerGame.getShip().getScore());

        //setup text
        nameLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 18));
        starsLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 18));
        scoreLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 18));

        nameLabel.setTextFill(Paint.valueOf("000000"));
        starsLabel.setTextFill(Paint.valueOf("000000"));
        scoreLabel.setTextFill(Paint.valueOf("000000"));
        starsLabel.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        starsLabel.setGraphicTextGap(-30);
        starsLabel.setPadding(new Insets(0,50,0,10));
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
        group.getChildren().addAll(button.getButton(), hBox);

        //assigning actions
        group.setOnMouseEntered(button.getButton().getOnMouseEntered());
        group.setOnMouseExited(button.getButton().getOnMouseExited());
        group.setOnMousePressed(button.getButton().getOnMousePressed());
        group.setOnMouseReleased(button.getButton().getOnMouseReleased());



    }

    public Node getLayer()
    {
        return group;
    }
}
