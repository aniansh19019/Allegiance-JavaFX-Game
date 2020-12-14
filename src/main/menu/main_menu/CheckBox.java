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

    private static AudioClip hoverSound;
    private static AudioClip clickSound;

    static
    {
        hoverSound = new AudioClip("file:res/sound/hover.mp3");
        clickSound = new AudioClip("file:res/sound/click.mp3");
    }

    private ImageView button;

    public void setNormalImageString(String normalImageString)
    {
        this.normalImageString = normalImageString;
    }

    public void setHoverImageString(String hoverImageString)
    {
        this.hoverImageString = hoverImageString;
    }

    public void setClickImageString(String clickImageString)
    {
        this.clickImageString = clickImageString;
    }


    public void loadImages()
    {
        normalImage = new Image(normalImageString);
        hoverImage = new Image(hoverImageString);
        clickImage = new Image(clickImageString);
        button.setImage(clickImage);
    }

    public CheckBox()
    {
        button = new ImageView();
        button.setOnMouseEntered(e -> hover());
        button.setOnMouseExited(e-> button.setImage(normalImage));
        button.setOnMousePressed(e -> press());

    }

    private void hover()
    {
        button.setImage(hoverImage);
        hoverSound.play();
    }

    private void press()
    {
        button.setImage(clickImage);
        clickSound.play();
    }

    public Node getButton()
    {
        return button;
    }
}
