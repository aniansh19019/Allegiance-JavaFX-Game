package widget;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class MenuItem
{
    private String text;
    private Image hoverImage;
    private Image image;
    private Image clickedImage;
    private Button button;

    public Button getButton()
    {
        return button;
    }

    public void hover()
    {
        //button.setContent(hoverImage);
        //play sound
    }
    public void pressed()
    {
        //button.setContent(clickedImage);
        //play sound
    }
    public void normal()
    {

    }


}
