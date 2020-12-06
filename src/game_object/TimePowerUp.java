package game_object;

import util.PurpleExplosionEffect;
import util.TimeEffect;
import util.YellowExplosionEffect;

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
