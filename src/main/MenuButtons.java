package main;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuButtons
{
    private String normalImageString;
    private String hoverImageString;
    private String clickImageString;
    private Image normalImage;
    private Image hoverImage;
    private Image clickImage;

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

    public MenuButtons()
    {
        button = new ImageView();

        button.setOnMouseEntered(e -> button.setImage(hoverImage));
        button.setOnMouseExited(e-> button.setImage(normalImage));
        button.setOnMousePressed(e -> button.setImage(clickImage));
        button.setOnMouseReleased(e->button.setImage(normalImage));
    }

    public Node getLayout()
    {
        return null;
    }
}
