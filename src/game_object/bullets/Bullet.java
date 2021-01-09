package game_object.bullets;

import game_object.PlayerShip;
import javafx.scene.media.AudioClip;
import main.GlobalConfig;
import util.Sprite;

//parent cass for all bullets

public abstract class Bullet extends Sprite
{
    private transient PlayerShip ship;
    private static final GlobalConfig config;
    private boolean isFired;
    private static final AudioClip fireSound;

    public void setShip(PlayerShip ship)
    {
        this.ship = ship;
    }

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
        fireSound = new AudioClip("file:res/sound/missile_fire.mp3");
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
        //play sound
        if(GlobalConfig.isSoundOn())
        fireSound.play();

        setPosition(ship.getPosition());
        setVelocity(0, bulletVelocity);
        isFired=true;
    }

}
