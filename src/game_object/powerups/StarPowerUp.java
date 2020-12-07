package game_object.powerups;

import animations.YellowExplosionEffect;
import javafx.scene.media.AudioClip;

public class StarPowerUp extends PowerUp
{
    private static AudioClip collectSound;
    static
    {
        collectSound = new AudioClip("file:res/sound/star_collect.mp3");
    }

    public StarPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/star.png");
        setImageScaleFactor(0.3);
    }

    @Override
    public void destroy()
    {
        //play sound
        collectSound.play();

        super.destroy();
        addEffect(new YellowExplosionEffect(getPosition()));
    }
}
