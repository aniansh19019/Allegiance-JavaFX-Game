package game_object;

import util.Vector;

public abstract class RotatingObstacle extends Obstacle
{
    private double angle;
    private double angleDelta = 0.8;

    public RotatingObstacle()
    {
        angle = 0;


    }

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getAngleDelta()
    {
        return angleDelta;
    }

    public void setAngleDelta(double angleDelta)
    {
        this.angleDelta = angleDelta;
    }

    @Override
    public void setSpeed(double speed)
    {
        super.setSpeed(speed);
        setAngleDelta(speed);
    }

    @Override
    public void update()
    {
        angle = (angle+angleDelta)%360;
        for(int i=0; i<4; i++)
        {
            getSegments()[i].update();
        }
    }


}
