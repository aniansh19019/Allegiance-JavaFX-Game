package game_object.obstacles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import main.GlobalConfig;
import util.GameColor;
import util.ObstaclePosition;

//TODO add rotation direction switch
public class RotatingArmsObstacle extends RotatingObstacle
{
    private final static String redArm = "file:res/img/obstacles/rotating_arm_red.png";
    private final static String greenArm = "file:res/img/obstacles/rotating_arm_green.png";
    private final static String blueArm = "file:res/img/obstacles/rotating_arm_blue.png";
    private final static String yellowArm = "file:res/img/obstacles/rotating_arm_yellow.png";
    private final static double xOffset = 60;

    private static final GlobalConfig config;

    static
    {
        config = new GlobalConfig();
    }
    public RotatingArmsObstacle(double y, ObstaclePosition pos, int level)
    {
        super(level);
        //set sprite images
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
            segment.setImageScaleFactor(0.3);
        }
        //after init segments, set position

        if(pos == ObstaclePosition.LEFT)
        {
            setPosition(xOffset,y);
            setCW(false);
        }
        else if(pos == ObstaclePosition.RIGHT)
        {
            setPosition(config.getSCREEN_WIDTH() - xOffset, y);
            setCW(true);
        }
        else
        {
            setPosition(config.getSCREEN_WIDTH()/2, y);
        }


    }

//    @Override



//TODO add connector
    @Override
    public void render(GraphicsContext context)
    {
//        System.out.println("Angle delta in rotating arms obstacle: " + getAngleDelta());
        double tempAngle = getAngle();
        for(int i=0; i<4; i++)
        {
            ObstacleSegment segment = getSegments()[i];
            Rotate r = new Rotate(tempAngle, segment.getPosition().getX(), segment.getPosition().getY()+20+segment.getBoundary().getH()/2);
            context.save();
            context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            segment.render(context);
            context.restore();

            //rotate collision polygon
            segment.getBoundary().setAngle(tempAngle);
            segment.getBoundary().setPivotX(segment.getPosition().getX());
            segment.getBoundary().setPivotY(segment.getPosition().getY()+20+segment.getBoundary().getH()/2);
            //render bounds
            if(config.getSHOW_COLLISION_BOUNDS())
            {
                segment.getBoundary().render(context);
            }
            //render effects
            segment.renderEffects(context);


            tempAngle+=90; // rotate arms 90 degree out of phase with each other
        }
    }



    @Override
    public boolean isInFrame()
    {
//        System.out.println(getPosition().getY() - getSegments()[0].getBoundary().getH());
        if(getPosition().getY() - getSegments()[0].getBoundary().getH() > config.getSCREEN_HEIGHT())
        {
            return false;
        }
        return true;
    }
}
