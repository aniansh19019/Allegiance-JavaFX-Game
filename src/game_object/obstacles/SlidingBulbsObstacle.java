package game_object.obstacles;

import javafx.scene.canvas.GraphicsContext;
import main.GlobalConfig;
import util.GameColor;


public class SlidingBulbsObstacle extends Obstacle
{

    private final static String redArm = "file:res/img/obstacles/ufo_red.png";
    private final static String greenArm = "file:res/img/obstacles/ufo_green.png";
    private final static String blueArm = "file:res/img/obstacles/ufo_blue.png";
    private final static String yellowArm = "file:res/img/obstacles/ufo_yellow.png";
    private static final GlobalConfig config;

    static
    {
        config = new GlobalConfig();
    }


    public SlidingBulbsObstacle(double y, int level)
    {
        super(level);

        //init vars


        getSegments()[0].setImage(redArm);
        getSegments()[1].setImage(greenArm);
        getSegments()[2].setImage(blueArm);
        getSegments()[3].setImage(yellowArm);

        //set sprite colors

        getSegments()[0].setColor(GameColor.RED);
        getSegments()[1].setColor(GameColor.GREEN);
        getSegments()[2].setColor(GameColor.BLUE);
        getSegments()[3].setColor(GameColor.YELLOW);

        for(int i=0; i<4; i++)
        {
            ObstacleSegment segment = getSegments()[i];
            //set scale
            segment.setImageScaleFactor(0.5);
        }
        setPosition(config.getSCREEN_WIDTH()/2, y);

        //separate sprites
        double initDistance=0;
        for(int i=0; i<4; i++)
        {
            ObstacleSegment current = getSegments()[i];
            double currentX = current.getPosition().getX();
            double currentY = current.getPosition().getY();

            current.setPosition(currentX+initDistance, currentY);
            initDistance+=120;

        }


    }

    @Override
    public void setSpeed(double speed)
    {
        super.setSpeed(speed);
        for(int i=0; i<4; i++)
        {
            getSegments()[i].setVelocity(getSpeed(), 0);
        }
    }

    @Override
    public void update()
    {
        if(!isDestroyed())
            if(getSegments()[0].getPosition().getX()> 0.55*config.getSCREEN_WIDTH() || getSegments()[3].getPosition().getX() < 0.45*config.getSCREEN_WIDTH())
            {
                for(int i=0; i<4; i++)
                {
                    getSegments()[i].getVelocity().scale(-1); // reverse velocity
                }
            }

        for(int i=0; i<4; i++)
        {
            getSegments()[i].update();
        }
    }

    @Override
    public boolean isInFrame()
    {
        if(getPosition().getY() > config.getSCREEN_HEIGHT() + getSegments()[0].getBoundary().getH())
        {
            return false;
        }
        return true;
    }

    @Override
    public void render(GraphicsContext context)
    {
        for(int i=0; i<4; i++)
        {
            getSegments()[i].render(context);
        }
    }
}
