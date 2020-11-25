package game_object;

abstract public class Obstacle
{
    private int level;
    private double speed;

    private ObstacleSegment[] segments;

    public Obstacle()
    {
        segments = new ObstacleSegment[4];
    }
}
