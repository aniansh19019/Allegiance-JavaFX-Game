package main.menu.main_menu;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import widget.MenuButton;

public class InstructionMenu {
    private VBox pane;
//    private UserInterfaceElements elements;
    private static final double scale=0.6;
    public InstructionMenu(EventHandler<? super MouseEvent > Handler){
        pane=new VBox(20);
//        elements=new UserInterfaceElements();
        MenuButton continueButton=new MenuButton(Handler);
        continueButton.setNormalImageString("file:res/img/ui_elements/continue_blue.png");
        continueButton.setHoverImageString("file:res/img/ui_elements/continue_yellow.png");
        continueButton.setClickImageString("file:res/img/ui_elements/continue_orange.png");
        continueButton.loadImages();
        continueButton.getButton().setScaleX(scale);
//        pane.getChildren().addAll(elements.getInstruction(),continueButton.getButton());
        pane.setAlignment(Pos.CENTER);

    }
    public Node getLayer(){
        return pane;
    }

}
