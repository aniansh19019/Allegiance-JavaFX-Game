package game_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import main.GlobalConfig;
import util.GameColor;
import util.Sprite;
import util.Vector;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerShip extends Sprite
{
    private int shipType;
    private boolean isBoosted;
    private Image normalImage;
    private Image thrustImage;
    private static Media thrustSound;
    private static Vector gravity;
    private static Vector boostVelocity;
    private static final int maxBulletNum;
    private ArrayList<Bullet> bulletsFired;
    private ArrayList<Bullet> bulletsAmmo;
    private final static GlobalConfig config;

    public boolean isBoosted()
    {
        return isBoosted;
    }

    public void setBoosted(boolean boosted)
    {
        isBoosted = boosted;
    }

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
        config = new GlobalConfig();
        gravity = new Vector(0,0.1);
        boostVelocity = new Vector(0,-3);
        maxBulletNum = 5;
        //init sound
    }

    @Override
    public void setColor(GameColor color)
    {
        super.setColor(color);
        setShip();
    }

    public void setShipType(int shipType)
    {
        this.shipType = shipType;
        setShip();
    }

    private void setShip()
    {
        String colorStr="";
        switch (getColor())
        {
            case RED:
                colorStr = "red";
                break;
            case GREEN:
                colorStr = "green";
                break;
            case BLUE:
                colorStr = "blue";
                break;
            case YELLOW:
                colorStr = "yellow";
                break;
            case ALL:
                colorStr = "pink";
                break;
            case NONE:
                colorStr = "none";
                break;
        }
        setNormalImage("file:res/img/ships/ship_"+shipType+"_"+colorStr+".png");
        setThrustImage("file:res/img/ships/ship_"+shipType+"_"+colorStr+"_fire.png");

        System.out.println("file:res/img/ships/ship_"+shipType+"_"+colorStr+".png");

        //TODO set appropriate scale factors
    }

    public PlayerShip(GameColor color, int shipType)
    {

        this.shipType=shipType;

        this.setColor(color);

        setPosition(config.getSCREEN_WIDTH()/2, 0.5* config.getSCREEN_HEIGHT()); // set ship initial position
        setImageScaleFactor(0.6);
        //TODO set this in setShip

        setAcceleration(gravity);// gravity
        bulletsFired = new ArrayList<Bullet>();
        bulletsAmmo = new ArrayList<Bullet>();
        isBoosted = false;
    }

    public void boost()
    {
        isBoosted = true;
        setVelocity(boostVelocity);
        setAcceleration(0,0); // switch off acceleration
        switchImage(thrustImage);
        //play sound
    }

    public void unBoost()
    {
        switchImage(normalImage);
        setAcceleration(gravity); // switch on gravity
        isBoosted=false;
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
            bulletsFired.add(new GrenadeBullet(this));
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


        if(!bulletsFired.isEmpty())
        {
            for(int i = 0; i< bulletsFired.size(); i++)
            {
                bulletsFired.get(i).render(context);
            }
        }
        //render bullet first and then the ship
        super.render(context);
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
