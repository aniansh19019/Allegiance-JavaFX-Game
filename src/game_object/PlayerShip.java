package game_object;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import util.GameColor;
import util.Sprite;
import util.Vector;

public class PlayerShip extends Sprite
{
    private Image image;
    private Image thrustImage;
    private static Media thrustSound;
    private static Vector gravity;
    private static Vector boostVelocity;


    static
    {
        gravity = new Vector(0,0.1);
        boostVelocity = new Vector(0,-3);
        //init sound
    }

    public PlayerShip()
    {
        setAcceleration(gravity);// gravity
        setColor(GameColor.RED);
    }

    public void boost()
    {
        setVelocity(boostVelocity);
        //play sound
    }
    public void fire()
    {

    }

    public void destroy()
    {
        //sound and explosion animation
    }





    @Override
    public String toString()
    {
        return super.toString();
    }
}
