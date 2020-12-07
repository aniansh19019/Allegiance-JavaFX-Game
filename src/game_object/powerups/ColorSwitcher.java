package game_object.powerups;

import animations.ColorSwitchEffect;
import game_object.powerups.PowerUp;
import javafx.scene.media.AudioClip;

public class ColorSwitcher extends PowerUp
{

    private static AudioClip collectSound;
    static
    {
        collectSound = new AudioClip("file:res/sound/color_switcher_effect.mp3");
    }

    public ColorSwitcher(double y)
    {
        super(y);
        setImage("file:res/img/powerups/color_switcher.png");
        setImageScaleFactor(0.3);
        //TODO better graphics for this
    }

    @Override
    public void destroy()
    {
        //play sound
        collectSound.play();

        super.destroy();
        addEffect(new ColorSwitchEffect(getPosition()));
    }





}
