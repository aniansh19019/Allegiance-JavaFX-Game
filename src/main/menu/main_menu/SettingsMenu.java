package main.menu.main_menu;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import widget.CheckBox;
import widget.MenuButton;

public class SettingsMenu
{
    private StackPane pane;


    public SettingsMenu(EventHandler<? super MouseEvent> BackHandler, EventHandler<? super javafx.scene.input.MouseEvent> checkToggleHandler )
    {

        //init back button
        MenuButton backButton=new MenuButton(BackHandler);
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(0.17);
        backButton.getButton().setScaleY(0.17);
        backButton.getButton().setTranslateX(-110);
        backButton.getButton().setTranslateY(-100);

        //init checkbox
        CheckBox checkBox = new CheckBox(checkToggleHandler);

        //init sound setting label
        Image soundSettingImage = new Image("file:res/img/ui_elements/sound_setting.png");
        ImageView soundSettingLabel = new ImageView(soundSettingImage);
        soundSettingLabel.setTranslateY(120);

        //init sound label
        Image soundImage = new Image("file:res/img/ui_elements/sound_label.png");
        ImageView soundLabel = new ImageView(soundImage);
        soundLabel.setScaleX(0.65);
        soundLabel.setScaleY(0.65);
        soundLabel.setTranslateX(-16);
//        soundSettingLabel.setTranslateY(200);



        pane=new StackPane();
        pane.getChildren().addAll(backButton.getButton(), checkBox.getButton(), soundSettingLabel, soundLabel);
        StackPane.setAlignment(backButton.getButton(), Pos.TOP_LEFT);
        StackPane.setAlignment(checkBox.getButton(), Pos.CENTER_RIGHT);
        StackPane.setAlignment(soundSettingLabel, Pos.TOP_CENTER);
        StackPane.setAlignment(soundLabel, Pos.CENTER);





    }
    public Node getLayer(){
        return pane;
    }

}
