package game_object.obstacles;

import game_object.PlayerShip;
import javafx.scene.canvas.GraphicsContext;
import main.GlobalConfig;
import util.ObstaclePosition;
import util.Vector;

public class DoubleRotatingArmsObstacle extends Obstacle
{
    private RotatingArmsObstacle rightObstacle;
    private RotatingArmsObstacle leftObstacle;
    private static GlobalConfig config;

    public RotatingArmsObstacle getRightObstacle()
    {
        return rightObstacle;
    }

    public void setRightObstacle(RotatingArmsObstacle rightObstacle)
    {
        this.rightObstacle = rightObstacle;
    }

    public RotatingArmsObstacle getLeftObstacle()
    {
        return leftObstacle;
    }

    public void setLeftObstacle(RotatingArmsObstacle leftObstacle)
    {
        this.leftObstacle = leftObstacle;
    }

    static
    {
        config = new GlobalConfig();
    }

    private void flipRightArms()
    {
        ObstacleSegment[] oldSegments = rightObstacle.getSegments();
        ObstacleSegment[] newSegments = new ObstacleSegment[4];
        for(int i=0; i<4; i++)
        {
            newSegments[3-i] = oldSegments[i]; // flip order
        }

        rightObstacle.setSegments(newSegments);
    }

    public DoubleRotatingArmsObstacle(double y, int level)
    {
        super(level);

        //init right arm
        rightObstacle = new RotatingArmsObstacle(y, ObstaclePosition.RIGHT, level);
        rightObstacle.setCW(true);
        flipRightArms();


        rightObstacle.setAngle(90); // shift phase


        // init left arm
        leftObstacle = new RotatingArmsObstacle(y, ObstaclePosition.LEFT, level);
        leftObstacle.setCW(false);

        setPosition(config.getSCREEN_WIDTH()/2, y);
    }

    @Override
    public void update()
    {
        rightObstacle.update();
        leftObstacle.update();
    }

    @Override
    public boolean isInFrame()
    {
        if(rightObstacle.isInFrame() && leftObstacle.isInFrame())
        {
            return true;
        }
        return false;
    }

    @Override
    public void render(GraphicsContext context)
    {
        rightObstacle.render(context);
        leftObstacle.render(context);
    }

    @Override
    public boolean didCollisionWithShip(PlayerShip ship)
    {
        return rightObstacle.didCollisionWithShip(ship) || leftObstacle.didCollisionWithShip(ship);
    }

    @Override
    public void shiftPosition(Vector delta)
    {
        super.shiftPosition(delta);
        rightObstacle.shiftPosition(delta);
        leftObstacle.shiftPosition(delta);
    }
}
