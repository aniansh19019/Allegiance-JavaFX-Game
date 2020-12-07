package game_object.powerups;

import animations.YellowExplosionEffect;

public class BulletPowerUp extends PowerUp
{
    public BulletPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/bullet_powerup.png");
        setImageScaleFactor(0.16);
    }

    @Override
    public void destroy()
    {
        super.destroy();
        addEffect(new YellowExplosionEffect(getPosition()));
    }
}
