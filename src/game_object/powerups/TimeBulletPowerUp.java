package game_object.powerups;

import animations.PurpleExplosionEffect;
import javafx.scene.media.AudioClip;
import main.GlobalConfig;

public class TimeBulletPowerUp extends PowerUp
{
    private static final AudioClip collectSound;
    static
    {
        collectSound = new AudioClip("file:res/sound/bullet_pickup_sound.mp3");
    }

    public TimeBulletPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/time_bullet_powerup.png");
        setImageScaleFactor(0.16);
    }
    @Override
    public void destroy()
    {
        //play sound
        if(GlobalConfig.isSoundOn())
        collectSound.play();

        super.destroy();
        addEffect(new PurpleExplosionEffect(getPosition()));
    }

}
