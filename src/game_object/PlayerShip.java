package game_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import util.GameColor;
import util.Sprite;
import util.Vector;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerShip extends Sprite
{
    private Image normalImage;
    private Image thrustImage;
    private static Media thrustSound;
    private static Vector gravity;
    private static Vector boostVelocity;
    private static final int maxBulletNum;
    private ArrayList<Bullet> bulletsFired;
    private ArrayList<Bullet> bulletsAmmo;

    public Image getThrustImage()
    {
        return thrustImage;
    }

    public void setThrustImage(Image thrustImage)
    {
        this.thrustImage = thrustImage;
    }

    public void setThrustImage(String file)
    {
        setThrustImage(new Image(file));
    }

    public Image getNormalImage()
    {
        return normalImage;
    }

    public void setNormalImage(Image normalImage)
    {
        this.normalImage = normalImage;
        setImage(normalImage);
    }

    public void setNormalImage(String file)
    {
        setNormalImage(new Image(file));
    }

    static
    {
        gravity = new Vector(0,0.1);
        boostVelocity = new Vector(0,-3);
        maxBulletNum = 5;
        //init sound
    }

    public PlayerShip()
    {
        setAcceleration(gravity);// gravity
        setColor(GameColor.RED);
        bulletsFired = new ArrayList<Bullet>();
        bulletsAmmo = new ArrayList<Bullet>();
    }

    public void boost()
    {
        setVelocity(boostVelocity);
        switchImage(thrustImage);
        //play sound
    }

    public void unBoost()
    {
        switchImage(normalImage);
        //stop sound
    }
    public void fire()
    {
        //TODO update
        //TODO check for bullet out of bound
        // Obstacle handled first then bullet
        // Fire one at a time

        if(bulletsFired.size()<maxBulletNum)
        {
            bulletsFired.add(new Bullet(this));
        }
    }

    @Override
    public void shiftPosition(Vector delta)
    {
        super.shiftPosition(delta);
        if(!bulletsFired.isEmpty())
        {
            for(int i = 0; i< bulletsFired.size(); i++)
            {
                bulletsFired.get(i).shiftPosition(delta);
            }
        }
    }



    @Override
    public void update()
    {
        super.update();
        if(!bulletsFired.isEmpty())
        {
            Iterator bulletsIterator = bulletsFired.iterator();
            while(bulletsIterator.hasNext())
            {
                Bullet currentBullet = (Bullet) bulletsIterator.next();
                currentBullet.update();
                if(!currentBullet.isInFrame())
                {
                    bulletsIterator.remove(); // remove if outside the screen
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext context)
    {
        //do not center ship sprite at the center
        super.render(context);

        if(!bulletsFired.isEmpty())
        {
            for(int i = 0; i< bulletsFired.size(); i++)
            {
                bulletsFired.get(i).render(context);
            }
        }
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
