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

public class SaveSuccessMenu
{
    private final StackPane pane;
    private static final GlobalConfig config;
    private static final double scale = 0.65;

    static
    {
        config = new GlobalConfig();
    }

    public SaveSuccessMenu(EventHandler<? super MouseEvent> continueHandler)
    {
        pane = new StackPane();

        VBox buttonLayer = new VBox(-30);

        //init resume button
        MenuButton continueButton = new MenuButton(continueHandler);
        continueButton.setNormalImageString("file:res/img/ui_elements/continue_blue.png");
        continueButton.setHoverImageString("file:res/img/ui_elements/continue_yellow.png");
        continueButton.setClickImageString("file:res/img/ui_elements/continue_orange.png");
        continueButton.loadImages();
        continueButton.getButton().setScaleX(scale);
        continueButton.getButton().setScaleY(scale);
        continueButton.getButton().setTranslateY(-40);

        //init save game floppy icon

        Image floppyImage = new Image("file:res/img/ui_elements/floppy_blue.png");
        ImageView saveImage = new ImageView(floppyImage);
        saveImage.setScaleX(0.3);
        saveImage.setScaleY(0.3);



        //add buttons

        buttonLayer.getChildren().addAll(saveImage, continueButton.getButton());
        buttonLayer.setAlignment(Pos.CENTER);
//        buttonLayer.setSpacing(2);

        Image background = new Image("file:res/img/ui_elements/menu_background.png");
        ImageView backgroundBox = new ImageView(background);
        BorderPane bgLayer = new BorderPane();
        Label pausedLabel = new Label("Game Saved!");
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
