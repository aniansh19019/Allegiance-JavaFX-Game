package game_object.bullets;

import game_object.PlayerShip;
import javafx.scene.media.AudioClip;
import main.GlobalConfig;
import util.Sprite;

public abstract class Bullet extends Sprite
{
    private PlayerShip ship;
    private static GlobalConfig config;
    private boolean isFired;
    private static AudioClip fireSound;

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
        fireSound.play();

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
