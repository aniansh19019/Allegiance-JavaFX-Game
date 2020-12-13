package game_object;

import animations.*;
import game_object.bullets.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import main.GlobalConfig;
import util.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PlayerShip extends Sprite implements Serializable
{

    //sounds
    private static AudioClip destroySound;
    private static AudioClip protectionOnSound;
    private static AudioClip protectionOffSound;
    private static AudioClip protectionSound;
    private static AudioClip thrustSound;
    private static AudioClip reviveSound;

    //sounds
    private static int PROTECTION_MAX_LIMIT = 1000;
    private static Random rand;

    private int shipType;
    private boolean isBoosted;
    transient private Image normalImage;
    transient private Image thrustImage;
//    private static Media thrustSound;
    private static Vector gravity;
    private static Vector boostVelocity;
    private static final int maxBulletNum = 5;
    private ArrayList<Bullet> bulletsFired;
    private ArrayList<Bullet> bulletsAmmo;
    private final static GlobalConfig config;
    private AnimatedEffect protectionPowerUpEffect;
    private boolean isProtected;
    private int protectionCounter;
    private int score;

    public void addScore(int increment)
    {
        score+=increment;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public boolean isProtected()
    {
        return isProtected;
    }

    public void setProtected(boolean aProtected)
    {
        isProtected = aProtected;
    }

    public ArrayList<Bullet> getBulletsFired()
    {
        return bulletsFired;
    }

    public ArrayList<Bullet> getBulletsAmmo()
    {
        return bulletsAmmo;
    }

    public void addAmmo(Bullet bullet)
    {
//        addCollectEffect();
        if(bulletsAmmo.size()<=maxBulletNum)
        {
            bulletsAmmo.add(bullet);
        }
    }

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

    public void addCollectEffect()
    {
        addEffect(new PurpleExplosionEffect(getPosition()));
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
        rand = new Random();
        //init sound
        //init sounds
        destroySound = new AudioClip("file:res/sound/ship_explode.mp3");
        thrustSound = new AudioClip("file:res/sound/thrust_sound.mp3");
        thrustSound.setCycleCount(AudioClip.INDEFINITE);
        protectionOnSound = new AudioClip("file:res/sound/protection_on.mp3");
        protectionOffSound = new AudioClip("file:res/sound/protection_off.mp3");
        protectionSound = new AudioClip("file:res/sound/protection_shield_effect.mp3");
        protectionSound.setCycleCount(AudioClip.INDEFINITE);

        reviveSound = new AudioClip("file:res/sound/revive_sound.mp3");
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

        if(this.isBoosted)
        {
            switchImage(thrustImage);
        }

//        System.out.println("file:res/img/ships/ship_"+shipType+"_"+colorStr+".png");

        //TODO set appropriate scale factors
    }


    public void protectionOn()
    {
        protectionOnSound.play();
        protectionSound.play();
        //sound on
        protectionCounter=0;
        addEffect(new ReviveEffect(getPosition()));
        isProtected=true;
        if(!getEffects().contains(protectionPowerUpEffect))
            addEffect(protectionPowerUpEffect);
    }

    public void protectionOff()
    {
        protectionOffSound.play();
        protectionSound.stop();
        //sound off
        addEffect(new ReverseReviveEffect(getPosition()));
        isProtected=false;
        if(getEffects().contains(protectionPowerUpEffect))
        {
            getEffects().remove(protectionPowerUpEffect);
        }
    }

//    @Override
//    public CollisionRectangle getBoundary()
//    {
//        if(isProtected)
//        {
//            protectionPowerUpEffect.getBoundary();
//        }
//        else
//        {
//            return super.getBoundary();
//        }
//
//    }

    public PlayerShip(GameColor color, int shipType)
    {

        this.shipType=shipType;

        this.setColor(color);

        setPosition(config.getSCREEN_WIDTH()/2, 0.8* config.getSCREEN_HEIGHT()); // set ship initial position
        setImageScaleFactor(0.6);
        //TODO set this in setShip

        setAcceleration(gravity);// gravity
        bulletsFired = new ArrayList<Bullet>();
        bulletsAmmo = new ArrayList<Bullet>();
        isBoosted = false;
        protectionPowerUpEffect= new ShipProtectEffect(getPosition());
        isProtected=false;
        protectionCounter=0;
        score=0;


    }

    public void boost()
    {
        isBoosted = true;
        setVelocity(boostVelocity);
        setAcceleration(0,0); // switch off acceleration
        switchImage(thrustImage);
        //play sound
        thrustSound.play();
    }

    public void unBoost()
    {
        switchImage(normalImage);
        setAcceleration(gravity); // switch on gravity
        isBoosted=false;
        //stop sound
        thrustSound.stop();
    }

    public void fire()
    {
        if(!bulletsAmmo.isEmpty())
        {
            Bullet fired = bulletsAmmo.remove(0);
            fired.fire();
            bulletsFired.add(fired);
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
        //update effects
        protectionPowerUpEffect.setPosition(getPosition());
        for(int i=0; i<getEffects().size(); i++)
        {
            if(getEffects().get(i).getClass() == ReviveEffect.class || getEffects().get(i).getClass() == ReverseReviveEffect.class)
            {
                getEffects().get(i).setPosition(getPosition());
            }
        }

        //check protection

        if(isProtected)
        {
            protectionCounter++;
            if(protectionCounter>=PROTECTION_MAX_LIMIT)
            {
                protectionOff();
                protectionCounter=0;
            }
        }

        //update bullets
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
        setVisible(false);
        setActive(false);
        AnimatedEffect effect = new ShipExplosionEffect(getPosition());
//        effect.setImageScaleFactor(2);
        addEffect(effect);

//        AudioClip
        destroySound.play();
        //turn off all other sounds
        thrustSound.stop();


    }

    public void revive()
    {
        //effect
        //TODO play sound
        //init positions
        reviveSound.play();


        setVisible(true);
        setActive(true);
        setPosition(config.getSCREEN_WIDTH()/2, 0.8* config.getSCREEN_HEIGHT()); // set ship initial position
        setVelocity(boostVelocity);
        addEffect(new ReviveEffect(getPosition()));
    }


    public void switchColor()
    {
        GameColor currentColor = getColor();
        int choice=0;
        do
        {
            choice = rand.nextInt(4);
        }while(choice== currentColor.ordinal());

        setColor(GameColor.values()[choice]);
//        addCollectEffect();
        //TODO change this effect
    }


    @Override
    public String toString()
    {
        return super.toString();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        setShip();
        //set ship reference to bullets

        for(int i=0; i<bulletsFired.size(); i++)
        {
            bulletsFired.get(i).setShip(this);
        }

        for(int i=0; i<bulletsAmmo.size(); i++)
        {
            bulletsAmmo.get(i).setShip(this);
        }

    }
}
