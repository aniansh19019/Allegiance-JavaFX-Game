package main.menu.main_menu;


import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class CheckBox {

    private String normalImageString;
    private String hoverImageString;
    private String clickImageString;
    private Image normalImage;
    private Image hoverImage;
    private Image clickImage;

    private Sound sound;
    private static final double scale=0.5;
    private ImageView button;

    public void loadImages()
    {
        normalImage = new Image(normalImageString);
        hoverImage = new Image(hoverImageString);
        clickImage = new Image(clickImageString);
        button.setImage(clickImage);
        button.setScaleX(scale);
        button.setScaleY(scale);
    }

    public CheckBox()
    {
        normalImageString="file:res/img/ui_elements/checkbox_unchecked_blue.png";
        hoverImageString="file:res/img/ui_elements/checkbox_unchecked_orange.png";
        clickImageString="file:res/img/ui_elements/checkbox_checked_yellow.png";
        button = new ImageView();
        button.setOnMouseEntered(e -> hover());
        button.setOnMouseExited(e-> button.setImage(normalImage));
        button.setOnMousePressed(e -> press());

    }

    private void hover()
    {
        button.setImage(hoverImage);
        sound.hover().play();
    }

    private void press()
    {

        if(button.getImage().equals(clickImage)){
            button.setImage(normalImage);
            sound.music().stop();
        }else if(button.getImage().equals(normalImage)){
            button.setImage(clickImage);
            sound.music().play();
        }
        sound.click().play();


    }

    public Node getButton()
    {
        return button;
    }
}
