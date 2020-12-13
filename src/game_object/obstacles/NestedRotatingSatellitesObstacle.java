package game_object.obstacles;

import game_object.PlayerShip;
import javafx.scene.canvas.GraphicsContext;
import main.GlobalConfig;
import util.ObstaclePosition;
import util.Vector;

public class NestedRotatingSatellitesObstacle extends Obstacle
{
    private RotatingSatellitesObstacle innerObstacle;
    private RotatingSatellitesObstacle outerObstacle;
    private static GlobalConfig config;

    public RotatingSatellitesObstacle getInnerObstacle()
    {
        return innerObstacle;
    }

    public void setInnerObstacle(RotatingSatellitesObstacle innerObstacle)
    {
        this.innerObstacle = innerObstacle;
    }

    public RotatingSatellitesObstacle getOuterObstacle()
    {
        return outerObstacle;
    }

    public void setOuterObstacle(RotatingSatellitesObstacle outerObstacle)
    {
        this.outerObstacle = outerObstacle;
    }

    static
    {
        config = new GlobalConfig();
    }

    private void flipInnerObstacle()
    {
        ObstacleSegment[] oldSegments = innerObstacle.getSegments();
        ObstacleSegment[] newSegments = new ObstacleSegment[4];
        for(int i=0; i<4; i++)
        {
            newSegments[3-i] = oldSegments[i]; // flip order
        }

        innerObstacle.setSegments(newSegments);
    }

    public NestedRotatingSatellitesObstacle(double y, int level)
    {
        super(level);

        //init inner obstacle
        innerObstacle = new RotatingSatellitesObstacle(y, ObstaclePosition.CENTER, level);
        innerObstacle.setCW(true);
//        shrink inner obstacle
        innerObstacle.setScale(0.5);
        innerObstacle.setAxisLength(90);

        //minor adjustments
        innerObstacle.setPosition(innerObstacle.getPosition().getX(), innerObstacle.getPosition().getY()+40);


        flipInnerObstacle();


        innerObstacle.setAngle(90); // shift phase


        // init left arm
        outerObstacle = new RotatingSatellitesObstacle(y, ObstaclePosition.CENTER, level);
        outerObstacle.setCW(false);

        //expand outer obstacle
        outerObstacle.setScale(0.8);
        outerObstacle.setAxisLength(120);

        setPosition(config.getSCREEN_WIDTH()/2, y);

//        outerObstacle.setDouble(true);
    }

    @Override
    public void update()
    {
        innerObstacle.update();
        outerObstacle.update();
    }

    @Override
    public boolean isInFrame()
    {
        if(innerObstacle.isInFrame() && outerObstacle.isInFrame())
        {
            return true;
        }
        return false;
    }

    @Override
    public void render(GraphicsContext context)
    {
        innerObstacle.render(context);
        outerObstacle.render(context);
    }

    @Override
    public boolean didCollisionWithShip(PlayerShip ship)
    {
        return outerObstacle.didCollisionWithShip(ship) || innerObstacle.didCollisionWithShip(ship);
    }

    @Override
    public void shiftPosition(Vector delta)
    {
        super.shiftPosition(delta);
        innerObstacle.shiftPosition(delta);
        outerObstacle.shiftPosition(delta);
    }

    @Override
    public void slow()
    {
        innerObstacle.slow();
        outerObstacle.slow();
    }

    @Override
    public void destroy()
    {
        innerObstacle.destroy();
        outerObstacle.destroy();
    }
}
