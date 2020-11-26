package game_object;

import main.GlobalConfig;
import util.Sprite;

public class Bullet extends Sprite
{
    private PlayerShip ship;
    private static GlobalConfig config;

    static
    {
        config= new GlobalConfig();
    }

    private static final double bulletVelocity = -4;

    public Bullet(PlayerShip ship)
    {
        super();
        setImage("file:res/img/spaceMissiles_001.png");
        setImageScaleFactor(0.3);
        setPosition(ship.getPosition());
        setVelocity(0, bulletVelocity);
    }


}
