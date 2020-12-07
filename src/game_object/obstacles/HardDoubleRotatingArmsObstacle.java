package game_object.obstacles;

public class HardDoubleRotatingArmsObstacle extends DoubleRotatingArmsObstacle
{
    public HardDoubleRotatingArmsObstacle(double y, int level)
    {
        super(y, level);
        getRightObstacle().setCW(false);
        getLeftObstacle().setCW(true);
    }
}
