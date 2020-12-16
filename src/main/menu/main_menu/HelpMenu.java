package main.menu.main_menu;

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
import widget.MenuButton;

public class HelpMenu {
    private StackPane pane;
//    private static final double scale=0.13;

    public HelpMenu(EventHandler<? super MouseEvent> backHandler)
    {
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


        //init heading
        Image instructionsHeading = new Image("file:res/img/ui_elements/instructions_label.png");
        ImageView instructionsLabel = new ImageView(instructionsHeading);

        //init Controls
        Image controlsImage = new Image("file:res/img/ui_elements/instructions_box.png");
        ImageView controlsBox = new ImageView(controlsImage);


        //add elements to layout
        pane =new StackPane();

        // init vbox
        VBox box = new VBox(40);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(instructionsLabel, controlsBox);

        //add elements to pane

        pane.getChildren().addAll(box, backButton.getButton());

        StackPane.setAlignment(backButton.getButton(), Pos.TOP_LEFT);



    }
    public Node getlayer(){
        return pane;
    }

}
