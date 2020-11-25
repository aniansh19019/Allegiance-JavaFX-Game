package util;

public class CollisionRectangle
{
    private double x;
    private double y;
    private double w;
    private double h;


    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getW()
    {
        return w;
    }

    public void setW(double w)
    {
        this.w = w;
    }

    public double getH()
    {
        return h;
    }

    public void setH(double h)
    {
        this.h = h;
    }

    public void setPos(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public CollisionRectangle(double x, double y, double w, double h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean didCollide(CollisionRectangle other)
    {
        boolean didNotCollide =
                this.x + this.w < other.x ||
                        other.x + other.w <this.x ||
                        this.y + this.h < other.y ||
                        other.y + other.h < this.y;
        return !didNotCollide;
    }
}
