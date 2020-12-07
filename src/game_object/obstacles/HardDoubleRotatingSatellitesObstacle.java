package game_object.obstacles;

public class HardDoubleRotatingSatellitesObstacle extends DoubleRotatingSatellitesObstacle
{

    public HardDoubleRotatingSatellitesObstacle(double y, int level)
    {
        super(y, level);
        getRightObstacle().setCW(false);
        getLeftObstacle().setCW(true);
    }
}
