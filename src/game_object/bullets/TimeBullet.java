package game_object.bullets;

import game_object.PlayerShip;

public class TimeBullet extends Bullet
{
    public TimeBullet(PlayerShip ship)
    {
        super(ship);
        setImage("file:res/img/powerups/time_bullet_powerup.png");
        setImageScaleFactor(0.16);
    }


}
