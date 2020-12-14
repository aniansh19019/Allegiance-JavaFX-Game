package main.menu.main_menu;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import widget.MenuButton;

public class HelpMenu {
    private BorderPane pane;
    private static final double scale=0.1;

    public HelpMenu(EventHandler<? super MouseEvent> BackHandler){
        MenuButton backButton=new MenuButton(BackHandler);
            backButton.setNormalImageString("file:res/img/ui_elements/back_blue.png");
            backButton.setHoverImageString("file:res/img/ui_elements/back_yellow.png");
            backButton.setClickImageString("file:res/img/ui_elements/back_orange.png");
            backButton.loadImages();
            backButton.getButton().setScaleX(scale);
            backButton.getButton().setScaleY(scale);
            backButton.getButton().setTranslateX(10);
            backButton.getButton().setTranslateY(10);

        UserInterfaceElements help=new UserInterfaceElements();
        VBox box =new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(help.getInstruction(),help.getHelpInfo());

        pane =new BorderPane();
        pane.setLeft(backButton.getButton());
        pane.setCenter(box);
        pane.setRight(new Label("          "));



    }
    public Node getlayer(){
        return pane;
    }

}
