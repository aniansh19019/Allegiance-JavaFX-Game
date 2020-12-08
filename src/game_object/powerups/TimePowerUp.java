package game_object.powerups;

import animations.PurpleExplosionEffect;
import animations.TimeEffect;
import javafx.scene.media.AudioClip;

public class TimePowerUp extends PowerUp
{
    private static AudioClip collectSound;
    static
    {
        collectSound = new AudioClip("file:res/sound/time_effect.mp3");
    }

    public TimePowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/time_powerup.png");
        setImageScaleFactor(0.3);
    }

    @Override
    public void destroy()
    {
        //play sound
        collectSound.play();

        super.destroy();
        addEffect(new PurpleExplosionEffect(getPosition()));
        addEffect(new TimeEffect(getPosition()));
    }
}
