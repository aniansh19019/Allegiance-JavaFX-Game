package main.menu.main_menu;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import widget.MenuButton;

public class SettingsMenu {
    private static final double scale =0.6;
    public SettingsMenu(EventHandler<? super MouseEvent> SoundHandler,EventHandler<? super MouseEvent> BackHandler){
        MenuButton backButton=new MenuButton(BackHandler);
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(scale);
        backButton.getButton().setTranslateX(10);
        backButton.getButton().setTranslateY(10);
        MenuButton CheckBox=new MenuButton(SoundHandler);


    }
}
