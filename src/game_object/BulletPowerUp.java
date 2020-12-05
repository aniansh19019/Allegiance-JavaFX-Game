package game_object;

public class BulletPowerUp extends PowerUp
{
    public BulletPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/bullet_powerup.png");
        setImageScaleFactor(0.16);
    }
}
