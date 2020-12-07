package game_object.powerups;

import animations.PurpleExplosionEffect;

public class TimeBulletPowerUp extends PowerUp
{
    public TimeBulletPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/time_bullet_powerup.png");
        setImageScaleFactor(0.16);
    }
    @Override
    public void destroy()
    {
        super.destroy();
        addEffect(new PurpleExplosionEffect(getPosition()));
    }

}
