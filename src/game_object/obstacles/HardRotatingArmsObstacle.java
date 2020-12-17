package game_object.obstacles;

import util.ObstaclePosition;

public class HardRotatingArmsObstacle extends RotatingArmsObstacle
{
    public HardRotatingArmsObstacle(double y, ObstaclePosition pos, int level)
    {
        super(y, pos, level);
        if(pos == ObstaclePosition.LEFT)
        {
            setCW(true);
        }
        else if(pos == ObstaclePosition.RIGHT)
        {
            setCW(false);
        }
    }
}
