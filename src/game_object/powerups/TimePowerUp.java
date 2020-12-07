package game_object.powerups;

import animations.PurpleExplosionEffect;
import animations.TimeEffect;

public class TimePowerUp extends PowerUp
{
    public TimePowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/time_powerup.png");
        setImageScaleFactor(0.3);
    }

    @Override
    public void destroy()
    {
        super.destroy();
        addEffect(new PurpleExplosionEffect(getPosition()));
        addEffect(new TimeEffect(getPosition()));
    }
}
