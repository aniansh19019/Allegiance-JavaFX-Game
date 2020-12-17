package game_object.powerups;

import animations.ColorSwitchEffect;
import javafx.scene.media.AudioClip;
import main.GlobalConfig;

public class ColorSwitcher extends PowerUp
{

    private static final AudioClip collectSound;
    static
    {
        collectSound = new AudioClip("file:res/sound/color_switcher_effect.mp3");
    }

    public ColorSwitcher(double y)
    {
        super(y);
        setImage("file:res/img/powerups/color_switcher.png");
        setImageScaleFactor(0.3);
    }

    @Override
    public void destroy()
    {
        //play sound
        if(GlobalConfig.isSoundOn())
        collectSound.play();

        super.destroy();
        addEffect(new ColorSwitchEffect(getPosition()));
    }





}
