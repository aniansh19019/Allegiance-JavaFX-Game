package util;

public class Vector
{
    private double x;
    private double y;

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

    public void set(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(Vector other)
    {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector(double x, double y)
    {
        set(x,y);
    }

    public Vector(Vector other)
    {
        set(other);
    }

    public double distanceTo(Vector other)
    {
        double retval = Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2);
        retval = Math.sqrt(retval);
        return retval;
    }

    public void scale(double k)
    {
        this.x*=k;
        this.y*=k;
    }

    public void add(Vector other)
    {
        this.x+=other.x;
        this.y+=other.y;
    }

    public void subtract(Vector other)
    {
        this.x-=other.x;
        this.y-=other.y;
    }

    public double dot(Vector other)
    {
        return this.x*other.x + this.y*other.y;
    }

    @Override
    public String toString()
    {
        return "("+ this.x + ", " + this.y+ ")";
    }
}
