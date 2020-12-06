package game_object;

import util.ExplosionEffect;
import util.PurpleExplosionEffect;
import util.YellowExplosionEffect;

public class GrenadeBullet extends Bullet
{
    public GrenadeBullet(PlayerShip ship)
    {
        super(ship);
        setImage("file:res/img/powerups/bullet_powerup.png");
        setImageScaleFactor(0.16);
    }


}
