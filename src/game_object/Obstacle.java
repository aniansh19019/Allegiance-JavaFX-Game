package game_object;

import util.Drawable;
import util.Sprite;
import util.Vector;
//TODO another interface needed for sprite and Obstacle
abstract public class Obstacle implements Drawable
{
    private int level;
    private double speed;
    private Vector position;

    private ObstacleSegment[] segments;

    public Vector getPosition()
    {
        return position;
    }

    abstract public void update();

    abstract public void shiftPosition(Vector delta);

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
        position = new Vector(0,0);
        segments = new ObstacleSegment[4];
        for(int i=0; i<4; i++)
        {
            //init Obstacle Segment objects
            segments[i] = new ObstacleSegment();
        }
    }
}
