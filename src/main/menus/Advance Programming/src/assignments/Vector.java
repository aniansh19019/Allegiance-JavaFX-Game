package assignments;

public class Vector
{
    private double x;
    private double y;

    @Override
    public String toString()
    {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector(Vector other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    public void scale(double k)
    {
        x*=k;
        y*=k;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y)
    {
        this.x+=x;
        this.y+=y;
    }

    public void add(Vector other)
    {
        add(other.x, other.y);
    }

    public void set(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public double distanceTo(Vector other)
    {
        double dist = Math.pow(this.x-other.x, 2) + Math.pow(this.y-other.y, 2);
        dist=Math.sqrt(dist);
        return dist;
    }
}

