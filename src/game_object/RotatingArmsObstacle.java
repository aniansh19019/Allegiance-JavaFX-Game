package game_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import main.GlobalConfig;
import util.Vector;

public class RotatingArmsObstacle extends Obstacle
{
    private final static String redArm = "file:res/img/obstacles/rotating_arm_red.png";
    private final static String greenArm = "file:res/img/obstacles/rotating_arm_green.png";
    private final static String blueArm = "file:res/img/obstacles/rotating_arm_blue.png";
    private final static String yellowArm = "file:res/img/obstacles/rotating_arm_yellow.png";
    private Rotate rotate;
    private double angle;
    private static final double angleDelta = 0.8;
    private static final GlobalConfig config;

    static
    {
        config = new GlobalConfig();
    }
    public RotatingArmsObstacle()
    {
        super();

        //set sprite images
        getSegments()[0].setImage(redArm);
        getSegments()[1].setImage(greenArm);
        getSegments()[2].setImage(blueArm);
        getSegments()[3].setImage(yellowArm);

        for(int i=0; i<4; i++)
        {
            ObstacleSegment segment = getSegments()[i];
            //set scale
            segment.setImageScaleFactor(0.3);

            //set initial position

        }

        //TODO apply transform to collision rectangle
        // TODO apply transform to shape
    }

//    @Override


//    @Override
    public void update()
    {
        angle = (angle+angleDelta)%360;
        for(int i=0; i<4; i++)
        {
            getSegments()[i].update();
        }
    }

//    @Override
    public void render(GraphicsContext context)
    {
        double tempAngle = angle;
        for(int i=0; i<4; i++)
        {
            ObstacleSegment segment = getSegments()[i];
            Rotate r = new Rotate(tempAngle, segment.getPosition().getX(), segment.getPosition().getY()+20+segment.getBoundary().getH()/2);
            context.save();
            context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            segment.render(context);
            context.restore();
            tempAngle+=90; // rotate arms 90 degree out of phase with each other
        }
    }

//    @Override
    public void shiftPosition(Vector delta)
    {
        for(int i=0 ;i<4; i++)
        {
            getSegments()[i].shiftPosition(delta);
        }
    }


}
