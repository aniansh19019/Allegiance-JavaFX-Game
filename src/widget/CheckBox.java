package widget;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class CheckBox
{
    private static final double scale = 0.2;
    private boolean isChecked;
    private ImageView finalButton;

    private static AudioClip hoverSound;
    private static AudioClip clickSound;

    static
    {
        hoverSound = new AudioClip("file:res/sound/hover.mp3");
        clickSound = new AudioClip("file:res/sound/click.mp3");
    }

    public boolean isChecked()
    {
        return isChecked;
    }

    private void setScale()
    {
        if(isChecked)
        {
            finalButton.setScaleX(scale);
            finalButton.setScaleY(scale);
        }
        else
        {
            finalButton.setScaleX(0.8*scale);
            finalButton.setScaleY(0.8*scale);
        }
    }

    public CheckBox(EventHandler<? super MouseEvent> handler)
    {
        this.isChecked = true;
        //init images

        Image checkedBoxNormal = new Image("file:res/img/ui_elements/checkbox_checked_blue.png");
        Image checkedBoxHover = new Image("file:res/img/ui_elements/checkbox_checked_yellow.png");
        Image checkedBoxClicked = new Image("file:res/img/ui_elements/checkbox_checked_orange.png");
        Image uncheckedBoxNormal = new Image("file:res/img/ui_elements/checkbox_unchecked_blue.png");
        Image uncheckedBoxHover = new Image("file:res/img/ui_elements/checkbox_unchecked_yellow.png");
        Image uncheckedBoxClicked = new Image("file:res/img/ui_elements/checkbox_unchecked_orange.png");

        //init button

        finalButton = new ImageView();
        finalButton.setImage(checkedBoxNormal);
        finalButton.setScaleX(scale);
        finalButton.setScaleY(scale);


        //init actions

        finalButton.setOnMouseEntered(e->{
            if(isChecked)
            {
                finalButton.setImage(checkedBoxHover);
            }
            else
            {
                finalButton.setImage(uncheckedBoxHover);
            }
            setScale();
            hoverSound.play();
        });


        finalButton.setOnMouseExited(e->{
            if(isChecked)
            {
                finalButton.setImage(checkedBoxNormal);
            }
            else
            {
                finalButton.setImage(uncheckedBoxNormal);
            }
            setScale();
        });


        finalButton.setOnMousePressed(e->{
            if(isChecked)
            {
                finalButton.setImage(checkedBoxClicked);
            }
            else
            {
                finalButton.setImage(uncheckedBoxClicked);
            }
            setScale();
            clickSound.play();
        });

        finalButton.setOnMouseReleased(e->{isChecked=!isChecked; handler.handle(e);
            if(isChecked)
            {
                finalButton.setImage(checkedBoxHover);
            }
            else
            {
                finalButton.setImage(uncheckedBoxHover);
            }
            setScale();
        });

    }

    public ImageView getButton()
    {
        return finalButton;
    }
}
