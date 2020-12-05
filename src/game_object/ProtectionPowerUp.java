package game_object;

public class ProtectionPowerUp extends PowerUp
{
    public ProtectionPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/protection_powerup.png"); //TODO image too dark
        setImageScaleFactor(0.3);
    }
}
