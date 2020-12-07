package game_object.powerups;

public class ProtectionPowerUp extends PowerUp
{
    public ProtectionPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/protection_powerup.png");
        setImageScaleFactor(0.3);
    }
}
