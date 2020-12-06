package game_object;

import util.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//TODO another interface needed for sprite and Obstacle
abstract public class Obstacle implements Drawable
{
    private static double MAX_EXPLOSION_VELOCITY_X = 18;
    private static double MAX_EXPLOSION_VELOCITY_Y = 10;
    private static Random rand;
    private int level;
    private double speed;
    private Vector position;
    private boolean isDestroyed;

    public boolean isDestroyed()
    {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed)
    {
        isDestroyed = destroyed;
    }

    private ObstacleSegment[] segments;

    static{
        rand = new Random();
    }

    public Vector getPosition()
    {
        return position;
    }

    abstract public void update();

    public void shiftPosition(Vector delta)
    {
        getPosition().subtract(delta);
        for(int i=0 ;i<4; i++)
        {
            getSegments()[i].shiftPosition(delta);
        }
    }

    abstract public boolean isInFrame();


    public void setPosition(double x, double y)
    {
        position.set(x,y);
        for(int i=0; i<4; i++)
        {
            ObstacleSegment segment = getSegments()[i];
            segment.setPosition(this.getPosition());
        }
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public ObstacleSegment[] getSegments()
    {
        return segments;
    }

    public void setSegments(ObstacleSegment[] segments)
    {
        this.segments = segments;
    }

    public Obstacle()
    {
        isDestroyed=false;
        position = new Vector(0,0);
        segments = new ObstacleSegment[4];
        for(int i=0; i<4; i++)
        {
            //init Obstacle Segment objects
            segments[i] = new ObstacleSegment();
        }
        speed=1;

    }

    public boolean didCollisionWithShip(PlayerShip ship)
    {

        //check for ship collision
        GameColor collisionColor = didCollide(ship);
        if(collisionColor == ship.getColor()) // passing through
        {
            //can add effects here

        }
        else if(collisionColor == GameColor.NONE) // not interacting with the obstacle
        {
            //do nothing
        }
        else // collided with obstacle
        {
            return true;
        }
        //check missiles

        ArrayList<Bullet> bullets = ship.getBulletsFired();

        if(!bullets.isEmpty())
        {
            Iterator itr = bullets.iterator();

            while (itr.hasNext())
            {
                Bullet current = (Bullet)itr.next();
                if(didCollide(current)!=GameColor.NONE) // collided
                {
                    if(current.getClass() == GrenadeBullet.class) // grenade bullet
                    {
                        destroy(current.getPosition());
                    }
                    else // ice bullet
                    {
                        //slow down obstacle
                        this.setSpeed(this.getSpeed()/2);
                        this.segments[0].addEffect(new TimeEffect(current.getPosition()));
                        //TODO implement this and use speed in rotating obstacle to get delta angle
                    }

                    current.destroy();
                    itr.remove();
                }
            }
        }




        return false;
    }

    private GameColor didCollide(Sprite other)
    {
        if(!isDestroyed())
        {
            for (int i = 0; i < 4; i++)
            {
                if (segments[i].didCollide(other))
                {
                    return segments[i].getColor();
                }
            }
        }
        return GameColor.NONE; // if not colliding
    }

    public void destroy(Vector hitPosition)
    {
        //explosion at hitPosition
        //TODO add more explosions

        segments[0].addEffect(new ExplosionEffect(hitPosition));

        //sounds


        //set destroyed flag
        isDestroyed=true;
        //turn on gravity for segments and set random velocities


        for(int i=0; i<4; i++)
        {
            segments[i].setActive(false);//avoid collision

            //TODO testing
            //segments[i].addEffect(new ExplosionEffect(segments[i].getPosition()));
            //TODO testing

            segments[i].setAcceleration(new Vector(0,0.1));
            segments[i].setVelocity(new Vector((rand.nextDouble()-0.5)*MAX_EXPLOSION_VELOCITY_X, (rand.nextDouble()-0.5)*MAX_EXPLOSION_VELOCITY_Y));
        }
    }
}
