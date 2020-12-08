package main.menus;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import main.GlobalConfig;

public class GameOverMenu
{
    private StackPane pane;
    private static GlobalConfig config;
    private static final double scale = 0.65;

    static
    {
        config = new GlobalConfig();
    }

    public GameOverMenu(EventHandler<? super MouseEvent> continueHandler, EventHandler<? super MouseEvent> restartHandler, EventHandler<? super MouseEvent> quitHandler, int starsRequired, int starsHave)
    {
        pane = new StackPane();

        VBox buttonLayer = new VBox(-16);



        //init continue button
        MenuButton continueButton;
        if(starsRequired<=starsHave)
        {
            continueButton = new MenuButton(continueHandler);
            continueButton.setNormalImageString("file:res/img/ui_elements/continue_blue.png");
            continueButton.setHoverImageString("file:res/img/ui_elements/continue_yellow.png");
            continueButton.setClickImageString("file:res/img/ui_elements/continue_orange.png");
        }
        else
        {
            continueButton = new MenuButton(null);
            continueButton.setNormalImageString("file:res/img/ui_elements/continue_grey.png");
            continueButton.setHoverImageString("file:res/img/ui_elements/continue_grey.png");
            continueButton.setClickImageString("file:res/img/ui_elements/continue_grey.png");
        }
        continueButton.loadImages();
        continueButton.getButton().setScaleX(scale);
        continueButton.getButton().setScaleY(scale);

        //init restart button
        MenuButton restartButton = new MenuButton(restartHandler);
        restartButton.setNormalImageString("file:res/img/ui_elements/restart_blue.png");
        restartButton.setHoverImageString("file:res/img/ui_elements/restart_yellow.png");
        restartButton.setClickImageString("file:res/img/ui_elements/restart_orange.png");
        restartButton.loadImages();
        restartButton.getButton().setScaleX(scale);
        restartButton.getButton().setScaleY(scale);


        //init quit button
        MenuButton quitButton = new MenuButton(quitHandler);
        quitButton.setNormalImageString("file:res/img/ui_elements/quit_blue.png");
        quitButton.setHoverImageString("file:res/img/ui_elements/quit_yellow.png");
        quitButton.setClickImageString("file:res/img/ui_elements/quit_orange.png");
        quitButton.loadImages();
        quitButton.getButton().setScaleX(scale);
        quitButton.getButton().setScaleY(scale);


        //init star image
        Image starImage = new Image("file:res/img/powerups/star.png");
        ImageView starOne = new ImageView(starImage);
        ImageView starTwo = new ImageView(starImage);

        //scale star images
        starOne.setScaleX(0.3);
        starOne.setScaleY(0.3);
        starTwo.setScaleX(0.3);
        starTwo.setScaleY(0.3);


        //init labels
        Label youHave = new Label("You have "+ starsHave, starOne);
        Label required = new Label("You Need "+ starsRequired, starTwo);
//        youHave.setGraphicTextGap(-100);

        youHave.setPadding(new Insets(-20, 36, -6, -20));
        required.setPadding(new Insets(0, 36, -16, -20));

//        youHave.setAlignment(Pos.CENTER_LEFT);
//        required.setAlignment(Pos.CENTER_LEFT);

        youHave.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        required.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        youHave.setGraphicTextGap(-35);
        required.setGraphicTextGap(-35);



        youHave.setFont(Font.loadFont(config.getPRIMARY_FONT(), 26));
        youHave.setTextFill(Paint.valueOf("333333"));
        required.setFont(Font.loadFont(config.getPRIMARY_FONT(), 26));
        required.setTextFill(Paint.valueOf("333333"));




        //add buttons

        buttonLayer.getChildren().addAll(required, youHave, continueButton.getButton(), restartButton.getButton(), quitButton.getButton());
        buttonLayer.setAlignment(Pos.CENTER);

//        buttonLayer.setAlignment(youHave, Pos.BASELINE_LEFT);
//        buttonLayer.setSpacing(2);

        Image background = new Image("file:res/img/ui_elements/menu_background.png");
        ImageView backgroundBox = new ImageView(background);
        BorderPane bgLayer = new BorderPane();
        Label overLabel = new Label("Game Over");
        overLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 36));
        overLabel.setTextFill(Paint.valueOf(config.getREGULAR_FONT_COLOR()));
        overLabel.setTranslateY(100);

        overLabel.setAlignment(Pos.CENTER);

        bgLayer.setCenter(backgroundBox);


        pane.getChildren().addAll(bgLayer,buttonLayer, overLabel);
        pane.setAlignment(overLabel, Pos.TOP_CENTER);



    }

    public Node getLayer()
    {
        return pane;
    }
}
