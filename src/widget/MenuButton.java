package widget;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class MenuButton {
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
        button.setImage(normalImage);
    }

    public MenuButton(EventHandler<? super javafx.scene.input.MouseEvent> handler )
    {
        button = new ImageView();
        button.setOnMouseEntered(e -> hover());
        button.setOnMouseExited(e-> button.setImage(normalImage));
        button.setOnMousePressed(e -> press());
        button.setOnMouseReleased(handler);
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
