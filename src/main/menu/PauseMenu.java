package main.menu;


import javafx.event.EventHandler;
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
import widget.MenuButton;

public class PauseMenu
{
    private final StackPane pane;
    private static final GlobalConfig config;
    private static final double scale = 0.65;

    static
    {
        config = new GlobalConfig();
    }

    public PauseMenu(EventHandler<? super MouseEvent> resumeHandler, EventHandler<? super MouseEvent> restartHandler,EventHandler<? super MouseEvent> saveHandler, EventHandler<? super MouseEvent> quitHandler )
    {
        pane = new StackPane();

        VBox buttonLayer = new VBox(-14);

        //init resume button
        MenuButton resumeButton = new MenuButton(resumeHandler);
        resumeButton.setNormalImageString("file:res/img/ui_elements/resume_blue.png");
        resumeButton.setHoverImageString("file:res/img/ui_elements/resume_yellow.png");
        resumeButton.setClickImageString("file:res/img/ui_elements/resume_orange.png");
        resumeButton.loadImages();
        resumeButton.getButton().setScaleX(scale);
        resumeButton.getButton().setScaleY(scale);
//        resumeButton.getButton().get

        //init restart button
        MenuButton restartButton = new MenuButton(restartHandler);
        restartButton.setNormalImageString("file:res/img/ui_elements/restart_blue.png");
        restartButton.setHoverImageString("file:res/img/ui_elements/restart_yellow.png");
        restartButton.setClickImageString("file:res/img/ui_elements/restart_orange.png");
        restartButton.loadImages();
        restartButton.getButton().setScaleX(scale);
        restartButton.getButton().setScaleY(scale);

        //init save button
        MenuButton saveButton = new MenuButton(saveHandler);
        saveButton.setNormalImageString("file:res/img/ui_elements/save_blue.png");
        saveButton.setHoverImageString("file:res/img/ui_elements/save_yellow.png");
        saveButton.setClickImageString("file:res/img/ui_elements/save_orange.png");
        saveButton.loadImages();
        saveButton.getButton().setScaleX(scale);
        saveButton.getButton().setScaleY(scale);

        //init save button
        MenuButton quitButton = new MenuButton(quitHandler);
        quitButton.setNormalImageString("file:res/img/ui_elements/quit_blue.png");
        quitButton.setHoverImageString("file:res/img/ui_elements/quit_yellow.png");
        quitButton.setClickImageString("file:res/img/ui_elements/quit_orange.png");
        quitButton.loadImages();
        quitButton.getButton().setScaleX(scale);
        quitButton.getButton().setScaleY(scale);


        //add buttons

        buttonLayer.getChildren().addAll(resumeButton.getButton(), restartButton.getButton(), saveButton.getButton(), quitButton.getButton());
        buttonLayer.setAlignment(Pos.CENTER);
//        buttonLayer.setSpacing(2);

        Image background = new Image("file:res/img/ui_elements/menu_background.png");
        ImageView backgroundBox = new ImageView(background);
        BorderPane bgLayer = new BorderPane();
        Label pausedLabel = new Label("Game Paused");
        pausedLabel.setFont(Font.loadFont(config.getPRIMARY_FONT(), 36));
        pausedLabel.setTextFill(Paint.valueOf(config.getREGULAR_FONT_COLOR()));
        pausedLabel.setTranslateY(100);

        pausedLabel.setAlignment(Pos.CENTER);

        bgLayer.setCenter(backgroundBox);


        pane.getChildren().addAll(bgLayer,buttonLayer, pausedLabel);
        StackPane.setAlignment(pausedLabel, Pos.TOP_CENTER);



    }

    public Node getLayer()
    {
        return pane;
    }
}
