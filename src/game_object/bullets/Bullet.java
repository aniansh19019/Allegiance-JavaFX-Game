package game_object.bullets;

import game_object.PlayerShip;
import main.GlobalConfig;
import util.Sprite;

public class Bullet extends Sprite
{
    private PlayerShip ship;
    private static GlobalConfig config;
    private boolean isFired;

    public void destroy()
    {
        setVisible(false);
        setActive(false);
    }

    public boolean isFired()
    {
        return isFired;
    }

    public void setFired(boolean fired)
    {
        isFired = fired;
    }

    static
    {
        config= new GlobalConfig();
    }

    private static final double bulletVelocity = -4;

    public Bullet(PlayerShip ship)
    {
        super();
        isFired=false;
        this.ship = ship;
    }

    public void fire()
    {
        setPosition(ship.getPosition());
        setVelocity(0, bulletVelocity);
        isFired=true;
    }

//    @Override
//    public void render(GraphicsContext context)
//    {
//        if(isFired)
//            super.render(context);
//    }
}
