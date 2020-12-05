package game_object;

public class StarPowerUp extends PowerUp
{

    public StarPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/star.png");
        setImageScaleFactor(0.3);
    }
}
