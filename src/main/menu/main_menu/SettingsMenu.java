package main.menu.main_menu;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import widget.MenuButton;

public class SettingsMenu {
    private BorderPane pane;
    private static final double scale =0.6;
    private UserInterfaceElements elements;
    public SettingsMenu(EventHandler<? super MouseEvent> BackHandler){
        pane=new BorderPane();
        MenuButton backButton=new MenuButton(BackHandler);
        backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
        backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
        backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
        backButton.loadImages();
        backButton.getButton().setScaleX(scale);
        backButton.getButton().setTranslateX(10);
        backButton.getButton().setTranslateY(10);
        elements=new UserInterfaceElements();
        CheckBox checkBox=new CheckBox();
        HBox box=new HBox(20);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(elements.getSound(),checkBox.getButton());
        pane.setLeft(backButton.getButton());
        pane.setCenter(box);

    }
    public Node getLayer(){
        return pane;
    }

}
