package game_object;

public class TimeBulletPowerUp extends PowerUp
{
    public TimeBulletPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/time_bullet_powerup.png");
        setImageScaleFactor(0.16);
    }
}
