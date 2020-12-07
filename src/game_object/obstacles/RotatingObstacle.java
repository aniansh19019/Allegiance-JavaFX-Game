package game_object.obstacles;

public abstract class RotatingObstacle extends Obstacle
{
    private double angle;
    private double angleDelta;
    private boolean isCW;

    public boolean isCW()
    {
        return isCW;
    }

    @Override
    public void setCW(boolean CW)
    {
        isCW = CW;
        setAngleDelta();
    }

    public RotatingObstacle(int level)
    {
        super(level);
        angle = 0;
        setCW(true);


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

    public void setAngleDelta()
    {
        if(isCW)
        {
            this.angleDelta = getSpeed();
//            System.out.println("AngleDelta set to " + getSpeed());
        }
        else
        {
            this.angleDelta = -getSpeed();
//            System.out.println("AngleDelta set to " + -getSpeed());
        }

    }

    @Override
    public void setSpeed(double speed)
    {
        super.setSpeed(speed);
        setAngleDelta();
    }

    @Override
    public void update()
    {
//        System.out.println("AngleDelta: "+ this.angleDelta);
        angle = (angle+this.angleDelta)%360;
        for(int i=0; i<4; i++)
        {
            getSegments()[i].update();
        }
    }


}
